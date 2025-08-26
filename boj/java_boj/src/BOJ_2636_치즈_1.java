import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS 풀이 
public class BOJ_2636_치즈_1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int rowSize;
	static int columnSize;
	static int[][] pan;
	static boolean[][] visited;
	
	static int meltTime;
	static int lastcheese;
	
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaColumn = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		columnSize = Integer.parseInt(st.nextToken());
		
		pan = new int[rowSize][columnSize];
		for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int columnIndx = 0; columnIndx < columnSize; columnIndx++) {
				pan[rowIndex][columnIndx] = Integer.parseInt(st.nextToken());
			}
		}
		
		meltTime = 0;
		lastcheese = 0;
		
		// 공기 중에 녹음
		while (true) {
			int meltCheeseCount = bfs();
			
			// 다 녹았네 
			if (meltCheeseCount == 0) {
				break;
			}
			
			 meltTime++;
			 lastcheese = meltCheeseCount;
		}
		
		// 동작완료
		sb.append(meltTime).append('\n').append(lastcheese);
		System.out.println(sb);
	}
	
	public static int bfs() {
		// 공기랑 인접해있는 치즈 찾기 
		Queue<int[]> q = new ArrayDeque<>();
		visited = new boolean[rowSize][columnSize];
		
		// 이번에 녹을 치즈 좌표들
		ArrayList<int[]> meltList = new ArrayList<>();
		
		// 좌측 상단 부터 시작해서 공기랑 붙어있는 지 확인하기
		q.add(new int[] {0, 0});
		visited[0][0] = true;
		
		while (!q.isEmpty()) {
			int currentRow = q.peek()[0];
			int currentColumn = q.peek()[1];
			q.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int newRow = currentRow + deltaRow[dir];
				int newColumn = currentColumn + deltaColumn[dir];
				
				// 범위벗어나면 안됨
				if (newRow < 0 || newRow >= rowSize || newColumn < 0 || newColumn >= columnSize) continue;
				// 방문했으면 건너뛰기
				if (visited[newRow][newColumn])	continue;
				
				visited[newRow][newColumn] = true;
				// 공기를 기점으로 주변을 파악 중임
				// 따라서 공기면 계속 q에 넣고 공기와 인접한 치즈(1)면 녹을 치즈 좌표에 넣기 
				if (pan[newRow][newColumn] == 0) {
					// 공기임
					q.add(new int[] {newRow, newColumn});
				}
				else {
					// 공기와 인접한 치즈임
					meltList.add(new int[] {newRow, newColumn});
				}
			}
		}
		
		// 이번에 녹일 치즈를 다 찾음
		// 치즈 녹이기
		for (int meltIndex = 0; meltIndex < meltList.size(); meltIndex++) {
			pan[meltList.get(meltIndex)[0]][meltList.get(meltIndex)[1]] = 0;
		}
		
		// 녹인 치즈의 개수를 return
		return meltList.size();
	}
}
