import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DFS 풀이 
public class BOJ_1987_알파벳_1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int rowCount;
	static int columnCount;
	static char[][] board;
	static boolean[] useAlphabet;
	static int maxCount;
	
	// 상하좌우
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaColumn = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		rowCount = Integer.parseInt(st.nextToken());
		columnCount = Integer.parseInt(st.nextToken());
		
		board = new char[rowCount][columnCount];
		for (int row = 0; row < rowCount; row++) {
			String str = br.readLine().trim();
			for (int column = 0; column < columnCount; column++) {
				board[row][column] = str.charAt(column);
			}
		}
		
		// 지금까지 지나온 칸 모든 칸에 적혀있는 알파벳과는 달라야 함
		useAlphabet = new boolean[26];
		maxCount = 0;
		useAlphabet[board[0][0]-'A'] = true;
		// 좌측 상단 시작 
		// 최대 지나가는 개수를 알아야 하니깐, dfs가 더 편할 지도 
		dfs(0, 0, 1);
		
		sb.append(maxCount);
		System.out.println(sb);
	}
	
	public static void dfs(int row, int column, int count) {
		maxCount = Math.max(count, maxCount);
		
		for (int dir = 0; dir < 4; dir++) {
			int nextRow = row + deltaRow[dir];
			int nextColumn = column + deltaColumn[dir];
			
			if (nextRow < 0 || nextRow >= rowCount || nextColumn < 0 || nextColumn >= columnCount) continue;
			if (useAlphabet[board[nextRow][nextColumn]-'A'])	continue;
			useAlphabet[board[nextRow][nextColumn]-'A'] = true;
			dfs(nextRow, nextColumn, count + 1);
			useAlphabet[board[nextRow][nextColumn]-'A'] = false; // 원복
		}
	}
}
