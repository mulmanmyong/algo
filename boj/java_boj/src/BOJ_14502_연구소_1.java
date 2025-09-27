import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14502_연구소_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	/*
	 * 0은 빈칸, 1은 벽, 2는 바이러스 -> 벽이 없는 곳은 바이러스 퍼져나갈 수 있음 
	 * 바이러스는 상 하 좌 우로 퍼져나감 
	 * 벽은 빈칸에 3개를 세워서, 안전 영역의 최대값을 구하는 프로그램을 작성
	 */

	// 지도의 세로 크기 N, 가로 크기 M
	static int N, M;
	// 지도의 모양
	static int[][] initMap;
	
	// 방향배열
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaCol = {0, 0, -1, 1};
	// 최대 안전 영역 크기
	static int maxSafeArea = 0;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 지도의 크기 입력이 주어짐
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 지도의 모양 주어짐
		initMap = new int[N][M];
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 0; col < M; col++) {
				initMap[row][col] = Integer.parseInt(st.nextToken());
			}
		}

		// 벽 세우기 시작
		buildWall(0);

		// 최대 안전 영역 출력
		System.out.println(maxSafeArea);
	}

	// 벽을 세우는 함수 
	static void buildWall(int depth) {
		if (depth == 3) {
			// 현재 상태의 지도를 복사
			int[][] buildMap = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					buildMap[i][j] = initMap[i][j];
				}
			}

			// 바이러스 퍼뜨리기
			spreadVirus(buildMap);

			// 안전 영역 계산
			int safe = countSafeArea(buildMap);
			if (safe > maxSafeArea) {
				maxSafeArea = safe;
			}
			return;
		}

		// 빈 칸에 벽 세우기
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				if (initMap[row][col] == 0) {
					initMap[row][col] = 1;
					buildWall(depth + 1);
					// 원복
					initMap[row][col] = 0;
				}
			}
		}
	}

	// 바이러스를 퍼뜨리기
	static void spreadVirus(int[][] map) {
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				if (map[row][col] == 2) {
					dfs(row, col, map);
				}
			}
		}
	}

	// DFS로 바이러스 퍼뜨리기
	static void dfs(int row, int col, int[][] map) {
		for (int dir = 0; dir < 4; dir++) {
			int newRow = row + deltaRow[dir];
			int newCol = col + deltaCol[dir];

			if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= M) continue;
			
			if (map[newRow][newCol] == 0) {
				map[newRow][newCol] = 2;
				dfs(newRow, newCol, map);
			}
			
		}
	}

	// 안전 영역 계산
	static int countSafeArea(int[][] map) {
		int count = 0;
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < M; col++) {
				if (map[row][col] == 0) {
					count++;
				}
			}
		}
		return count;
	}
}
