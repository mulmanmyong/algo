import java.io.*;
import java.util.*;

public class SWEA_1949_등산로조정_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * 등산로를 조정합니다
 * 등산로 부지는 N*N크기를 가지고 있습니다. 최대한 긴 등산로를 만들 것입니다.
 * 
 * 등산로를 만드는 규칙
 * 	1. 등산로는 가장 높은 봉우리에서 시작
 * 	2. 등산로는 산으로 올라갈 수 있도록 반드시 높은 지형에서 낮은 지형으로 가로 또는 세로 방향으로 연결이 되어야 함
 * 		즉, 높이가 같은 곳 혹은 낮은 지형이나, 대각선 방향의 연결은 불가능 
 * 3. 긴 등산로를 만들기 위해 딱 한 곳을 정해서 최대 K 깊이만큼 지형을 깎는 공사를 할 수 있음
 * 
 * 
 */
	
	// 지도의 한 변의 길이 N, 최대 공사 가능 깊이 K
	static int N, K;
	// 지도 정보
	static int[][] initMap;
	// 최대 높이
	static int highest;
	// 가장 긴 거리 저장
	static int longest;
	// 방문 처리
	static boolean[][] visited;
	
	// 방향 배열
	// 상 하 좌 우
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaCol = {0, 0, -1, 1};
	
	// 시작 row, 시작 col, 현재까지의 거리, 그리고 지형을 깎기를 시도했는지 여부
	public static void findLength(int currentRow, int currentCol, int dist, boolean cut) {
		// DFS로 하기
		
		// 최대 길이가 되어버렸으면 갱신하기
		longest = Math.max(longest, dist);
		
		// 현재 좌표 기준 4방향 탐색
		for (int dir = 0; dir < 4; dir++) {
			int newRow = currentRow + deltaRow[dir];
			int newCol = currentCol + deltaCol[dir];
			
			// 범위 벗어 나면 안됨
			if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N)	continue;
			// 방문해도 안됨
			if (visited[newRow][newCol])	continue;
			
			// 이제 이동을 할건데
			
			// 나보다 낮은 경우
			if (initMap[newRow][newCol] < initMap[currentRow][currentCol]) {
				// 방문 처리
				visited[newRow][newCol] = true;
				// 다음으로 바로 이동
				findLength(newRow, newCol, dist + 1, cut);
				// 원복
				visited[newRow][newCol] = false;
			}
			// 나랑 같거나 높은 경우
			else {
				// 공사가 가능한 경우이면
				if (!cut) {
					// 다음 위치를 최대 깊이만큼 공사를 해보고 갈 수 있는 지 확인
					for (int k = 1; k <= K; k++) {
						// 공사를 했을 때 다음으로 이동이 가능해지면
						if (initMap[newRow][newCol] - k < initMap[currentRow][currentCol]) {
							// 방문 처리
							visited[newRow][newCol] = true;
							// 공사하기
							initMap[newRow][newCol] -= k;
							// 공사했음 처리하고 이동
							findLength(newRow, newCol, dist + 1, true);
							// 원복
							visited[newRow][newCol] = false;
							initMap[newRow][newCol] += k;
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= testCase; test_case++) {
			
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			initMap = new int[N][N];
			highest = -1;
			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < N; col++) {
					initMap[row][col] = Integer.parseInt(st.nextToken());
					highest = Math.max(highest, initMap[row][col]);
				}
			}
			
			// 제일 높은 위치 찾고, 그 것을 기반으로 탐색 시작
			longest = 0;
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (highest == initMap[row][col]) {
						visited = new boolean[N][N];
						// 원복
						visited[row][col] = true;
						// 초기값은
						// 시작 위치, 시작길이(본인포함 1), 그리고 공사여부 (false면 안한거, true면 한거)
						findLength(row, col, 1, false);
					}
				}
			}

			sb.append('#').append(test_case).append(' ').append(longest).append('\n');
		}
		System.out.println(sb);
	}
}