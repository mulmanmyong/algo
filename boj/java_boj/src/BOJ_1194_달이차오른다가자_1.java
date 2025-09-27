import java.io.*;
import java.util.*;

public class BOJ_1194_달이차오른다가자_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [문제설명]
 * 미로 속에 있음. 미로는 직사각형 모양. 여행길을 떠나기 위해 미로 탈출하려고 함
 * 미로의 구성
 * . : 빈칸이고, 언제나 이동할 수 있음
 * # : 벽이고, 절대 이동할 수 없음
 * a, b, c, d, e, f : 열쇠이고, 이 곳에 **처음**들어가면 열쇠를 집느다
 * A, B, C, D, E, F : 문이고, 대응하는 열쇠가 있을 때만 이동할 수 있음
 * 0 : 민식이의 현재 위치이고, 빈 곳이다.
 * 1 : 출구이고, 민식이가 가야하는 곳이다.
 * 
 * 이를 기반으로 하여 민식이라 미로를 탈출하는데 걸리는 이동 횟수의 최솟값을 구하는 프로그램을 작성하기
 * 
 * [입력]
 * 첫째 줄에 미로의 세로 크기 N, 가로 크기 M 주어짐
 * 둘째 줄부터 N개의 줄에 미로의 모양이 주어짐
 * 같은 타입의 열쇠가 여러 개 있을 수 있고, 문도 그러함 
 * 문에 대응하는 열쇠가 없을 수도 있음
 * 민식이의 위치는 하나이고, 1은 최소 1개이다. 열쇠는 다회용이다 
 * 
 * [출력]
 * 민식이가 미로를 탈출하는데 드는 이동 횟수의 최솟값 출력. 탈출 불가하면 -1 출력
 * 
 * [어캄]
 * 민식이가 위치한 곳을 기반으로 BFS를 진행
 * 만약 열쇠를 마주하면 해당 문은 뚫을 수 있다는 의미로 	A,B,C,D,E,F를 0 1 2 3 4 5에 대응하여 true로 하기 
 * true면 해당 문은 통과할 수 있음
 * 
 * 방문배열은 이동하고 있는 방향과 함께해서 방문했는 지 안했는 지 처리, 똑같은 곳으로 가게 되면 이것은,, 무한루프니깐 해당 길 못간다고 처리 
 * -> 다시 계산 해보니 방향으로는 이동이 가능함
 * -> 따라서 방문배열은 현재 열쇠상태로 동일한 곳으로 이동하지 않게 하면 됨. 
 * 열쇠를 가지고 있는 것에 대한 상태는 row, col, 비트마스킹(배열)을 queue에 담아서 진행하기?
 * 
 * 일단 해보면 되지 뭐
 */
	
	// 미로의 세로 크기 N, 가로 크기 M
	static int N, M;
	static char[][] initMap;
	
	// 미로의 시작지점
	static int startRow;
	static int startCol;
	
	// 방향 배열
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaCol = {0, 0, -1, 1};
	
	// 상태를 저장할 것
	static class State {
		int row, col, cnt, key;

		public State(int row, int col, int cnt, int key) {
			super();
			this.row = row;
			this.col = col;
			this.cnt = cnt;
			this.key = key;
		}
	}
	
	public static boolean isKey(char c) {
		if ('a' <= c && c <= 'f') {
			return true;
		}
		return false;
	}
	
	public static boolean isDoor(char c) {
		if ('A' <= c && c <='F') {
			return true;
		}
		return false;
	}
	
	public static int bfs() {
		// BFS를 위한 queue를 생성
		Queue<State> q = new LinkedList<>();
		// q의 초기값으로 시작 좌표, 움직인 회수, 열쇠 습득 여부를 기록하기
		q.add(new State(startRow, startCol, 0, 0));
		
		// 방문처리를 통해 무한루프 빠지지 않게 하기
		// 좌표와 열쇠상태로 저장 열쇠는 6개있으니깐 2^6개 크기
		boolean[][][] visited = new boolean[N][M][1 << 6];
		
		while (!q.isEmpty()) {
			// 현재 상태
			State cur = q.poll();
			
			// 현위치가 1이다? 바로 탈툴
			if (initMap[cur.row][cur.col] == '1') {
				return cur.cnt;
			}
			
			// 움직이는 방향 
			for (int dir = 0; dir < 4; dir++) {
				int newRow = cur.row + deltaRow[dir];
				int newCol = cur.col + deltaCol[dir];
				
				// 범위를 벗어나면 안됨
				if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= M) continue;
				// 움직이려는 방향이 벽이면 안됨 == '#'
				if (initMap[newRow][newCol] == '#') continue;
				// 동일한 상태로 움직였던 곳이라면 안됨
				if (visited[newRow][newCol][cur.key]) continue;
				
				// 이 모든게 아니라면 이동은 가능함
				
				// 우선적으로 이동하려는 방향이 문이라면 열쇠를 가지고 있는 지 확인
				if (isDoor(initMap[newRow][newCol])) {
					int door = initMap[newRow][newCol] - 'A';
					// 1이 아니라면 열쇠를 가지고 있지 않기에 이동할 수 없다
					if ((cur.key & (1 << door)) == 0) continue;
				}
				
				// 열쇠를 가지고 있으면 이동을 했음
				// 방문처리하기
				visited[newRow][newCol][cur.key] = true;
				
				// 이동했는데 출구다? 바로 탈춝
				if (initMap[newRow][newCol] == '1') {
					return cur.cnt+1;
				}
				
				// 이동했는데 열쇠자리다? 열쇠를 습득하기
				int newKey = cur.key;
				if (isKey(initMap[newRow][newCol])) {
					int key = initMap[newRow][newCol] - 'a';
					// 습득하고 이동을 합니다
					newKey |= (1 << key);
				}
				
				// 조건에 아무것도 안걸려있으면 언제나 이동할 수 있는 공간
				q.add(new State(newRow, newCol, cur.cnt+1, newKey));
			}
		}
		
		// while을 나왔다는 것은 종료조건에 도달하지 못하고 나왔다는 것.. 탈출 실패지뭐
		return -1;
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 미로의 세로크기 N, 가로크기 M 입력
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 미로의 상태를 입력받음
		initMap = new char[N][M];
		for (int row = 0; row < N; row++) {
			String str = br.readLine().trim();
			for (int col = 0; col < M; col++) {
				initMap[row][col] = str.charAt(col);
				
				// 시작지점을 기록
				if (initMap[row][col] == '0') {
					startRow = row;
					startCol = col;
				}
			}
		}
		
		// 기록해둔 시작지점을 기점으로 BFS 시작
		int ans = bfs();

		sb.append(ans);
		System.out.println(sb);
	}
}
