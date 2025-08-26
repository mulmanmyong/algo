import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS 풀이 
public class SWEA_7733_치즈도둑_1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int cheeseSize;
	static int[][] cheese;
	static int minDay;
	static int maxDay;
	static int maxCount;
	static boolean[][] visited;
	
	static int[] deltaRow = {-1, 1, 0, 0,};
	static int[] deltaColumn = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			// 치즈의 크기를 입력받음
			cheeseSize = Integer.parseInt(br.readLine().trim());
			
			minDay = 101;
			maxDay = 0;
			// 치즈를 입력받음
			cheese = new int[cheeseSize][cheeseSize];
			for (int row = 0; row < cheeseSize; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int column = 0; column < cheeseSize; column++) {
					cheese[row][column] = Integer.parseInt(st.nextToken());
					minDay = Math.min(minDay, cheese[row][column]);
					maxDay = Math.max(maxDay, cheese[row][column]);
				}
			}
			
 			
			/*
			 * x번째 날에 맛있는 정도가 x인 치즈를 먹음
			 * 최소 맛과 최대 맛을 미리 찾아두고, 맛에 따라 치즈를 먹었음 처리를 함
			 * 그 후 각 날마다 bfs를 통해 치즈덩어리의 개수를 계산 
			 */
			maxCount = 1;
			for (int eat = minDay; eat <= maxDay; eat++) {
				
				// 방문 처리를 할 배열을 생성 
				visited = new boolean[cheeseSize][cheeseSize];
				
				// 먹었음 처리를 함 -> 0 으로
				// 해당 일짜 이전 애들은 먹은 것이기에 방문 처리를 함
				for (int row = 0; row < cheeseSize; row++) {
					for (int column = 0; column < cheeseSize; column++) {
						if (cheese[row][column] <= eat) {
							cheese[row][column] = 0;
							visited[row][column] = true;
						}
					}
				}
				
				// bfs진행
				int count = 0;
				for (int row = 0; row < cheeseSize; row++) {
					for (int column = 0; column < cheeseSize; column++) {
						// 방문하지 않았고, 먹은 부분이 아니면
						if (!visited[row][column] && cheese[row][column] != 0) {
							bfs(row, column);
							count++;
						}
					}
				}
				
				maxCount = Math.max(maxCount, count);
			}
			
			sb.append('#').append(test_case).append(' ').append(maxCount).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void bfs(int row, int column) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {row, column});
		visited[row][column] = true;
		
		while (!q.isEmpty()) {
			int currentRow = q.peek()[0];
			int currentColumn = q.peek()[1];
			q.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int newRow = currentRow + deltaRow[dir];
				int newColumn = currentColumn + deltaColumn[dir];
				
				// 범위 벗어나거나, 방문헀거나, 먹었으면 패스
				if (newRow < 0 || newRow >= cheeseSize || newColumn < 0 || newColumn >= cheeseSize || visited[newRow][newColumn] || cheese[row][column] == 0)	continue;
				// 그렇지 않다면 방문 처리후 다음으로 이동
				visited[newRow][newColumn] = true;
				q.add(new int[] {newRow, newColumn});
			}
		}
	}
}
