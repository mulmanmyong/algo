import java.io.*;
import java.util.*;

public class BOJ_3055_탈출_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [설명]
 * 따봉도치가 행운을 주기 위해 비버의 집으로 가서 홍수를 피하려고 함
 * 
 * 숲의 지도는 R행 C열로 이루어져 있음. 
 * '.' : 비어있는 곳
 * '*' : 물이 차있는 곳
 * 'X' : 돌맹이
 * 'D' : 비버의 집
 * 'S' : 따봉도치 위치 
 * 
 * 따봉도치는 인접한 상하좌우로 이동 가능
 * 물도 매 분마다 비어있는 칸으로 확장
 * 그래서 물이 찰 예정인 공간으로도 이동 불가능
 * 
 * 이동하기 위해 필요한 최소 시간을 구하기 
 * 
 * [입력]
 * 첫째 줄에 행 R과 열 C 주어짐
 * 이후 지도 주어짐
 * 
 * [출력]
 * 따봉도치가 비버의 집으로 이동할 수 있는 가장 빠른 시간 출력
 * 이동할 수 없으면 "KAKTUS" 출력
 * 
 * [엌망]
 * 그냥 BFS인듯 합니다.
 * 물전파 따봉도치 이동 따로 처리해도 되겠지만
 * 이번에는 동시에 처리해보겠습니다.
 */
	
	// 행의 크기 R, 열의 크기 C
	static int R, C;
	// 초기 지도
	static char[][] initMap;

	// 방문 체크
	static boolean[][] visited;

	// 따봉도치 큐
	static Queue<Point> dochiQ;
	// 물 전파 큐
	static Queue<Point> waterQ;
	
	// 좌표 클래스 + 시간
	static class Point {
		int row, col, time;

		public Point(int row, int col, int time) {
			super();
			this.row = row;
			this.col = col;
			this.time = time;
		}
	}
	
	// 방향 배열 (상 하 좌 우)
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaCol = {0, 0, -1, 1};
	
	public static int bfs() {
		
		// 큐가 빌 때 까지
		while (!dochiQ.isEmpty()) {

			// 물 먼저 퍼뜨림
			// 현재 물 큐의 크기
			int waterSize = waterQ.size(); 
			for (int i = 0; i < waterSize; i++) {
				Point water = waterQ.poll();

				for (int dir = 0; dir < 4; dir++) {
					int newRow = water.row + deltaRow[dir];
					int newCol = water.col + deltaCol[dir];

					// 범위 벗어나면 안됨
					if (newRow < 0 || newRow >= R || newCol < 0 || newCol >= C) continue;
					// 벽이거나 비버의 집이거나 이미 물이면 패스
					if (initMap[newRow][newCol] == 'X' || initMap[newRow][newCol] == 'D' || initMap[newRow][newCol] == '*') continue;
					
					// 안 벗어났으면 물 전파하기 
					initMap[newRow][newCol] = '*';
					waterQ.add(new Point(newRow, newCol, 0));
				}
			}

			// 2. 따봉도치 이동
			// 현재 따봉도치 큐의 크기
			int dochiSize = dochiQ.size();
			for (int i = 0; i < dochiSize; i++) {
				Point dochi = dochiQ.poll();

				for (int dir = 0; dir < 4; dir++) {
					int newRow = dochi.row + deltaRow[dir];
					int newCol = dochi.col + deltaCol[dir];

					// 범위 벗어나면 안됨
					if (newRow < 0 || newRow >= R || newCol < 0 || newCol >= C) continue;
					// 방문했어도 안됨
					if (visited[newRow][newCol]) continue;
					// 물이거나 벽이면 안됨
					if (initMap[newRow][newCol] == '*' || initMap[newRow][newCol] == 'X') continue;

					// 비버의 집에 도달하면 시간 반환
					if (initMap[newRow][newCol] == 'D') {
						return dochi.time + 1;
					}

					// 방문 처리
					visited[newRow][newCol] = true;
					dochiQ.add(new Point(newRow, newCol, dochi.time + 1));
				}
			}
		}

		// 반복문을 나왔다는 것은 비버의 집에 도달하지 못한 것 
		return -1;
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 행의 크기 R, 열의 크기 C 입력받음
		st = new StringTokenizer(br.readLine().trim());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// 초기 지도를 입력받으요
		initMap = new char[R][C];
		// 방문 처리
		visited = new boolean[R][C];
		
		// 큐 초기화
		dochiQ = new LinkedList<>();
		waterQ = new LinkedList<>();

		for (int row = 0; row < R; row++) {
			String str = br.readLine().trim();
			for (int col = 0; col < C; col++) {
				initMap[row][col] = str.charAt(col);
				
				// 따봉 도치의 시작 지점
				if (initMap[row][col] == 'S') {
					dochiQ.offer(new Point(row, col, 0));
					// 방문 처리
					visited[row][col] = true;
				} 
				
				// 물들의 시작
				if (initMap[row][col] == '*') {
					waterQ.offer(new Point(row, col, 0));
				}
			}
		}

		// 결과 계산
		int ans = bfs();
		
		// -1 이면 비버의 집 도달 못한 것이기 때문에 KAKTUS 출력
		if (ans == -1) {
			sb.append('K').append('A').append('K').append('T').append('U').append('S');
		}
		// -1 이 아니라면 정답 출력
		else {
			sb.append(ans);
		}
		System.out.println(sb);
	}
}
