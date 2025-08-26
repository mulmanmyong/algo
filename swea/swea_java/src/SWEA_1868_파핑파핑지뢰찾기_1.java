import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS로 풀이  
public class SWEA_1868_파핑파핑지뢰찾기_1 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N;
	static int count;
	static char[][] board;
	static boolean[][] visited;
	
	static int[] deltaRow = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] deltaColumn = {0, 1, 1, 1, 0, -1, -1, -1};
	
	// 주변에 지뢰가 하나라도 있으면 퍼지지 않음
	public static boolean trap(int row, int column) {
		for (int dir = 0; dir < 8; dir++) {
			int newRow = row + deltaRow[dir];
			int newColumn = column + deltaColumn[dir];
			if (newRow < 0 || newRow >= N || newColumn < 0 || newColumn >= N)	continue;
			if (board[newRow][newColumn] == '*')	return true;
		}
		return false;
	}
	
	public static void search(int row, int column) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {row, column});
		visited[row][column] = true;
		
		while (!q.isEmpty()) {
			int currentRow = q.peek()[0];
			int currentColumn = q.peek()[1];
			q.poll();
		
			for (int dir = 0; dir < 8; dir++) {
				int newRow = currentRow + deltaRow[dir];
				int newColumn = currentColumn + deltaColumn[dir];
				
				// 범위를 안벗어나면
				if (newRow < 0 || newRow >= N || newColumn < 0 || newColumn >= N)	continue;
				// 방문했거나, 다음 위치가 지뢰면 패스
				if (visited[newRow][newColumn] || board[newRow][newColumn] == '*')	continue;
				
				// 그렇지 않으면 다음 위치 방문 처리
				visited[newRow][newColumn] = true;
				
				// 그리고 다음 위치를 기준으로 주변에 지뢰가 없을 경우에만 전파하기
				if (!trap(newRow, newColumn)) {
					visited[newRow][newColumn] = true;
					q.add(new int[] {newRow, newColumn});
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			N = Integer.parseInt(br.readLine().trim());
			board = new char[N][N];
			
			for (int i = 0; i < N; i++) {
//				st = new StringTokenizer(br.readLine().trim(), "");
				String str = br.readLine().trim();
				for (int j = 0; j < N; j++) {
//					board[i][j] = st.nextToken().charAt(0);
					board[i][j] = str.charAt(j);
				}
			}
			
			// 최소 몇번을 클릭해야 지뢰가 없는 모든 칸에 숫자가 표시될 것인지 출력 
			// bfs를 탐색, 지뢰가 없고, 방문했던 곳이 아닌 곳만 탐색
			count = 0;
			visited = new boolean[N][N];
			// * 지뢰, . 안전한 칸
			for (int row = 0; row < N; row++) {
				for (int column = 0; column < N; column++) {
					if (board[row][column] == '*' || visited[row][column])	continue;
					// 최소 클릭 을 위해 주변에 지뢰가 하나도 없는 경우를 위주로 먼저 클릭
					if (!trap(row, column)) {
						count++;
						search(row, column);
					}
				}
			}
			
			// 위에 전파되면서 확인되지 않은 칸은 개인적으로 클릭하기, 주변에 지뢰가 있어서 전파가 되지 않음!
			for (int row = 0; row < N; row++) {
				for (int column = 0; column < N; column++) {
					// 지뢰가 있거나 이미 방문한 칸이면 패스
					if (board[row][column] == '*' || visited[row][column])	continue;
					// 그렇지 않으면 클릭
					count++;
				}
			}
			
			sb.append('#').append(test_case).append(' ').append(count).append('\n');
		}
		System.out.println(sb);
	}
}
