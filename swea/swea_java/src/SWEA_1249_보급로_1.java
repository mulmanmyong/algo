import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_1249_보급로_1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

/*
 * S->G 가기 위한 도로 복구 작업을 빠른 시간 내에 수행하려고 함
 * 도로가 파여진 깊이에 비례해서 복구 시간은 증가 
 * 깊이가 1이면 복구에 드는 시간은 1
 * 
 * 지도정보에는 각 칸마다 파여진 도로의 깊이가 주어짐. 이 도로를 복구해야만 다른 곳으로 이동 가능
 * 즉, 깊이가 싹 다 가중치 인것임 
 * 
 * 우선순위 큐를 이용한해서 가중치가 작은 애들 부터 작업을 수행 -> BFS+우선순위큐 
 * 
 * [입력]
 * 테스트케이스
 * 	지도의크기 N
 * 	지도의 정보 주어짐
 * 
 * [복구시간이 가장 작은 경로의 복구 시간을 출력]
 */

	// 테스트 케이스 입력
	static int testCase;
	static int N;
	static int[][] initMap;
	static PriorityQueue<int[]> pq;
	static boolean[][] visited;
	static int minCost;
	
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaCol = {0, 0, -1, 1};
	
	public static void dijkstraBFS() {
		pq = new PriorityQueue<>((a,b) -> Integer.compare(a[2], b[2]));
		pq.add(new int[] {0, 0, 0});
		
		visited = new boolean[N][N];
		visited[0][0] = true;
		
		while (!pq.isEmpty()) {
			int currentRow = pq.peek()[0];
			int currentCol = pq.peek()[1];
			int currentCost = pq.peek()[2];
			pq.poll();
			
			// 도달헀으면 그냥 종료 때리기
			if (currentRow == N-1 && currentCol == N-1) {
				minCost = currentCost;
				return;
			}
			
			for (int dir = 0; dir < 4; dir++) {
				int newRow = currentRow + deltaRow[dir];
				int newCol = currentCol + deltaCol[dir];
				
				// 범위 벗어나면 안됨
				if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N)	continue;
				if (visited[newRow][newCol])	continue;
				
				visited[newRow][newCol] = true;
				pq.add(new int[] {newRow, newCol, currentCost+initMap[newRow][newCol]});
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			N = Integer.parseInt(br.readLine().trim());
			
			initMap = new int[N][N];
			for (int row = 0; row < N; row++) {
				String str = br.readLine().trim();
				for (int col = 0; col < N; col++) {
					initMap[row][col] = str.charAt(col) - '0';
				}
			}
			
			// BFS를 이용한 다익스트라 하기 
			minCost = Integer.MAX_VALUE;
			dijkstraBFS();
			
			
			sb.append('#').append(test_case).append(' ').append(minCost).append('\n');
		}
		System.out.println(sb);
	}
}
