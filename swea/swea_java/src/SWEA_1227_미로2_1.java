import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1227_미로2_BFS {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int startRow;
	static int startColumn;
	static int endRow;
	static int endColumn;
	static int[][] maze;
	
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaColumn = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = 10;
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			// 테케번호
			int tcase = Integer.parseInt(br.readLine().trim());
			
			// 1은 벽, 0은 길, 2는 출발점, 3은 도착점
			maze = new int[100][100];
			for (int row = 0; row < 100; row++) {
				String str = br.readLine().trim();
				for (int column = 0; column < 100; column++) {
					// 미로의 구성 
					maze[row][column] = str.charAt(column)-'0';
					
					// 출발좌표
					if (maze[row][column] == 2) {
						startRow = row;
						startColumn = column;
					}
					
					// 도착좌표
					if (maze[row][column] == 3) {
						endRow = row;
						endColumn = column;
					}
				}
			}
			
			
			if (canEscape(startRow, startColumn)) {
				sb.append('#').append(tcase).append(' ').append('1').append('\n');
			}
			else {
				sb.append('#').append(tcase).append(' ').append('0').append('\n');
			}
		}
		System.out.println(sb);
	}
	
	public static boolean canEscape(int startRow, int startColumn) {
		// 미로 탈출 가능한지
		// 시작을 기반으로 BFS
		Queue<int[]> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[100][100];
		q.add(new int[] {startRow, startColumn});
		visited[startRow][startColumn] = true;
		
		while (!q.isEmpty()) {
			int currentRow = q.peek()[0];
			int currentColumn = q.peek()[1];
			q.poll();
			
			// 4방향 탐색
			for (int dir = 0; dir < 4; dir++) {
				int newRow = currentRow + deltaRow[dir];
				int newColumn = currentColumn + deltaColumn[dir];
				
				// 범위 벗어나면 노노
				if (newRow < 0 || newRow >= 100 || newColumn < 0 || newColumn >= 100)	continue;
				// 방문했거나 벽이면 이동 불가능
				if (visited[newRow][newColumn] || maze[newRow][newColumn] == 1)	continue;
				// 다음이 종점이면 탈출 가능
				if (newRow == endRow && newColumn == endColumn)	return true;
				
				// 그렇지 않으면 계속 탐색
				visited[newRow][newColumn] = true;
				q.add(new int[] {newRow, newColumn});
			}
		}
		
		return false;
	}
}
