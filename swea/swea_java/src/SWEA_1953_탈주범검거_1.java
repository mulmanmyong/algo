import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1953_탈주범검거_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

/*
 * 교도소로 이송 중이던 흉악범이 탈출하는 사건 발생
 * 맨홀 뚜껑을 통해 지하터널의 어느 한 지점으로 들어감
 * 지하 터널 어딘가에 은신 중 
 * 터널끼리 연결되어 있는 경우 이동 가능, 탈주범이 있을 수 있는 위치의 개수 계산
 * 
 * 시간당 1의 거리 움직일 수 있음 
 * 터널 7종류
 * 1~7의 타입
 * 1 : 상하좌우 연결
 * 2 : 상하 연결
 * 3 : 좌우 연결
 * 4 : 상우 연결
 * 5 : 하우 연결
 * 6 : 하좌 연결
 * 7 : 상좌 연결
 * 
 * 멘홀뚜겅을 들어가면 1시간, 그 통로 기반으로 이동가능한 위치로 이동하면 2시간 소요 이런 느낌
 * 
 * [입력]
 * 테스트케이스의 개수 입력
 * 	세로크기 N, 가로크기 M, 맨홀뚜껑 세로위치 R, ㅁ벤홀뚜껑 가로위치 C, 탈출 후 소요된 시간 L 입력
 * 	지하터널 지도 입력 
 */
	
	// 테스트케이스
	static int testCase;
	// 세로크기 N, M,
	static int N, M;
	// 맨홀뚜껑 세로위치, 가로위치
	static int hallRow, hallCol;
	// 소요된 시간 L
	static int L;
	
	// 지도의 정보를 입력받음 
	static int[][] initMap;
	// 방문처리
	static boolean[][] visited;
	// 위치할 수 있는 공간 새구
	static int locateCount;
	
	// 이동가능한 방향, 방향 배열을 return 하는 방식으로 진행
	public static int[][] canMove(int currentRow, int currentCol) {
		int[][] delta = new int[2][4];
		switch (initMap[currentRow][currentCol]) {
			case 1:
				// 상하좌우 연결
				delta = new int[][] {{-1, 1, 0, 0}, {0, 0, -1, 1}};
				return delta;
			case 2:
				// 상하 연결
				delta = new int[][] {{-1, 1, 0, 0}, {0, 0, 0, 0}};
				return delta;
			case 3:
				// 좌우 연결
				delta = new int[][] {{0, 0, 0, 0}, {0, 0, -1, 1}};
				return delta;
			case 4:
				// 상우 연결
				delta = new int[][] {{-1, 0, 0, 0}, {0, 0, 0, 1}};
				return delta;
			case 5:
				// 하우 연결
				delta = new int[][] {{0, 1, 0, 0}, {0, 0, 0, 1}};
				return delta;
			case 6:
				// 하좌 연결
				delta = new int[][] {{0, 1, 0, 0}, {0, 0, -1, 0}};
				return delta;
			case 7:
				// 상좌 연결
				delta = new int[][] {{-1, 0, 0, 0}, {0, 0, -1, 0}};
				return delta;
			default :
				delta = new int[][] {{0, 0, 0, 0}, {0, 0, 0, 0}};
				return delta;
		}
	}
	
	// 연결되어있는 지 확인
	public static boolean isConnected(int newRow, int newCol, int[] direction) {
		
		// 이동가능한 방향 배열 찾아오기 
		int[][] delta = new int[2][4];
		// delta[0] row, delta[1] col
		delta = canMove(newRow, newCol);
		
		for (int dir = 0; dir < 4; dir++) {
			
			if (direction[0] == delta[0][dir] && direction[1] == delta[1][dir]) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void diveUnderWorld() {
		// 현재 위치를 기반으로 하여 퍼져나가기
		// row, col, 현재 소요시간
		Queue<int[]> q = new LinkedList<>();
		// 맨홀에 들어간 순간부터 1시간 소요 
		q.add(new int[] {hallRow, hallCol, 1});
		
		// 방문처리
		visited = new boolean[N][M];
		visited[hallRow][hallCol] = true;
		
		// 초기값 
		locateCount = 0;
		
		// 탐색 시작
		while (!q.isEmpty()) {
			int currentRow = q.peek()[0];
			int currentCol = q.peek()[1];
			int currentTime = q.peek()[2];
			q.poll();
			
			// 현재 소요된 시간이 L이하이면 개수 증가 
			if (currentTime <= L) {
				locateCount++;
			}
			
			// 이동가능한 방향 배열 찾아오기 
			int[][] delta = new int[2][4];
			// delta[0] row, delta[1] col
			delta = canMove(currentRow, currentCol);
			for (int dir = 0; dir < 4; dir++) {
				int newRow = currentRow + delta[0][dir];
				int newCol = currentCol + delta[1][dir];
				
				// 범위 벗어나면 안됨
				if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= M)	continue;
				// 제자리면 할필요 없음 패스
				if (newRow == currentRow && newCol == currentCol)	continue;
				// 방문했음 또 필요 없음
				if (visited[newRow][newCol])	continue;
				// 0인 구간으로는 이동할 수 없음
				if (initMap[newRow][newCol] == 0)	continue;
				
				// 현재의 홀과 이동하려는 홀이 연결이 되어있는지 판단
				if (isConnected(newRow, newCol, new int[] {-delta[0][dir], -delta[1][dir]})) {
					// 연결되어있음!!
					// 다 괜찮다면 이동을 합시다
					visited[newRow][newCol] = true;
					q.add(new int[] {newRow, newCol, currentTime+1});
				}
			}
			
		}
		
	}
	
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			hallRow = Integer.parseInt(st.nextToken());
			hallCol = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			
			// 지도정보 입력
			initMap = new int[N][M];
			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < M; col++) {
					initMap[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 맨홀뚜껑으로 들어가벌미
			diveUnderWorld();
			
			sb.append('#').append(test_case).append(' ').append(locateCount).append('\n');
		}
		System.out.println(sb);
	}
}
