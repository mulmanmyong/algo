import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_7793_오나의여신님_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * 지구의 종말이 다가오고,, 이 세상에 악마들이 강림하기 시작했다,,,
 * 
 * 악마의 손아귀는 상하좌우 인접해 있는 영역들을 부식시키며 확장되어 감
 * N행 M열로 이루어진 지도 내에서 우연이는 말을 타고 1초에 동, 서, 남, 북으로 인접한 칸으로 이동할 수 있음
 * 돌이 있는 위치로는 이동 불가, 악마의 손아귀는 돌이 있는 곳에도 확장 안됨
 * 
 * 악마의 손아귀를 피해서 여신이 있는 공간에 도달하고 싶음
 * 여신님께 도달하는 최소 시간을 구하여라
 * 
 * [입력]
 * 1. 테스트 케이스 입력
 * 	1-1. N(행), M(열) 입력
 * 	1-2. 초기 지도 주어짐
 * 수연이의 위치는 'S', 여신의 공간은 'D', 돌의 위치는 'X', 악마는 '*', 평범한 지역 '.'
 * 전체 지도에서 ‘S’와 ‘D’는 정확히 한 번 나타남 -> 악마는 여러번 나올 수도 있음
 * 여신이 있는 공간은 “악마의 손아귀” 스킬로부터 자유로울 수 있다. -> 여신이 있는 공간은 전파되지 않음
 * 
 * [출력]
 * 도달가능하면 최소시간, 도달 못하면 GAME OVER 출력
 */
	
	static int testCase;
	static int N, M;
	static char[][] initMap;
	
	// 악마의 손아귀 확장 시간 
	static int[][] demonTime;
	static Queue<int[]> qDemon;
	
	// 수연이가 이동하는 시간
	static int[][] moveTime;
	static Queue<int[]> qMove;
	
	// 여신님이 계신곳
	static int goddessRow;
	static int goddessCol;
	
	// 여신님께 도달하는 시간....
	static int time;
	
	// 방향 배열 - 상하좌우
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaCol = {0, 0, -1, 1};
	
	public static void useSkill() {
		// 악마의 손아귀 사용...
		
		// 스킬 전파 시작
		while (!qDemon.isEmpty()) {
			int currentRow = qDemon.peek()[0];
			int currentCol = qDemon.peek()[1];
			qDemon.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int newRow = currentRow + deltaRow[dir];
				int newCol = currentCol + deltaCol[dir];
				
				// 범위 벗어남
				if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= M)	continue;
				// 이미 전파된 위치거나 돌맹이가 박혀있음
				if (demonTime[newRow][newCol] != 0 || initMap[newRow][newCol] == 'X' || initMap[newRow][newCol] == 'D')	continue;
				
				// 그렇지 않으면 악마의 손아귀 전파
				demonTime[newRow][newCol] = demonTime[currentRow][currentCol] + 1;
				qDemon.add(new int[] {newRow, newCol});
			}
		}	
	}
	
	public static void trip() {
		// 여신님이 있는 곳은 안전해!! 얼른 그곳으로 가야해
		time = -1;
		
		// 여행 시작!
		while (!qMove.isEmpty()) {
			int currentRow = qMove.peek()[0];
			int currentCol = qMove.peek()[1];
			qMove.poll();
			
			// 여신님이 있는 곳에 도달했어.. 난 살았어!
			if (currentRow == goddessRow && currentCol == goddessCol) {
				time = moveTime[currentRow][currentCol] - 1;
				break;
			}
			
			for (int dir = 0; dir < 4; dir++) {
				int newRow = currentRow + deltaRow[dir];
				int newCol = currentCol + deltaCol[dir];
				
				// 범위를 벗어남,,
				if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= M)		continue;
				// 이미 움직였던 위치거나, 돌맹이가 있거나
				if (moveTime[newRow][newCol] != 0 || initMap[newRow][newCol] == 'X')	continue;
				// 악마의 손아귀가 전파되었거나 전파될 예정이면..
				if (demonTime[newRow][newCol] != 0 && demonTime[newRow][newCol] <= moveTime[currentRow][currentCol] + 1)	continue;
				
				// 갈 수 있는 곳이구나!!
				moveTime[newRow][newCol] = moveTime[currentRow][currentCol] + 1;
				qMove.add(new int[] {newRow, newCol});
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			// N과 M 입력
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// 초기 맵 생성 
			initMap = new char[N][M];
			
			// 악마의 손아귀 시전 범위
			qDemon = new LinkedList<>();
			demonTime = new int[N][M];
			
			// 움직이는 시간
			qMove = new LinkedList<>();
			moveTime = new int[N][M];
			
			for (int row = 0; row < N; row++) {
				String str = br.readLine().trim();
				for (int col = 0; col < M; col++) {
					initMap[row][col] = str.charAt(col);
					
					// 여신님이 계신곳
					if (initMap[row][col] == 'D') {
						goddessRow = row;
						goddessCol = col;
					}
					// 수연이가 출발한다
					else if (initMap[row][col] == 'S') {
						qMove.add(new int[] {row, col});
						// 시작 지점을 1로 지정하기
						moveTime[row][col] = 1;
					}
					// 악마가 곧 여기서부터 스킬을 쓴다.
					else if (initMap[row][col] == '*') {
						qDemon.add(new int[] {row, col});
						// 시작 지점을 1로 지정하기
						demonTime[row][col] = 1;
					}
				}
			}
			
			// 악마의 손아귀 전파되는 시간 미리 확인 
			useSkill();
			
			// 여신님이 있는 곳은 안전해,.. 그곳으로 떠나자!
			trip();
			
			sb.append('#').append(test_case).append(' ');
			// 여신님을 구하지 못했어 ㅜㅜ
			if (time == -1) {
				sb.append('G').append('A').append('M').append('E').append(' ').append('O').append('V').append('E').append('R');
			}
			// 여신님을 구했어!!
			else {
				sb.append(time);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
