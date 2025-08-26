import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS 풀이  
public class BOJ_10026_적록색약_1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int arrSize;
	static int[][] SaekYak;
	static int[][] nonoSaekYak;
	static int SaekYakCount;
	static int nonoSaekYakCount;
	
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaColumn = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 적록색약은 R과 G를 구분 못함.
		// 따라서 입력받을 때 2가지로 입력받기
		// 적록색약인 사람은 R이든 G든 상관없이 동일한 색으로 인식할 수 있게
		// 아닌 사람은 구분할 수 있게
		
		arrSize = Integer.parseInt(br.readLine().trim());
		SaekYak = new int[arrSize][arrSize];
		nonoSaekYak = new int[arrSize][arrSize];
		for (int row = 0; row < arrSize; row++) {
			String str = br.readLine().trim();
			for (int column = 0; column < arrSize; column++) {
				// 1은 R 2는 G 3은 B
				// 적록색약은 R과 G모두 1, B는 3
				int color = str.charAt(column);
				if (color == 'R') {
					SaekYak[row][column] = 1;
					nonoSaekYak[row][column] = 1;
				}
				else if (color == 'G') {
					SaekYak[row][column] = 1;
					nonoSaekYak[row][column] = 2;
				}
				else if (color == 'B') {
					SaekYak[row][column] = 3;
					nonoSaekYak[row][column] = 3;
				}
			}
		}
		
		// BFS로 각각 영역 구하기
		SaekYakCount = 0;
		SaekYakBFS();
		nonoSaekYakCount = 0;
		nonoSaekYakBFS();
		
		sb.append(nonoSaekYakCount).append(' ').append(SaekYakCount);
		System.out.println(sb);
	}
	
	public static void SaekYakBFS() {
		for (int row = 0; row < arrSize; row++) {
			for (int column = 0; column < arrSize; column++) {
				// 방문하면 0으로 바꾸기, 이미 방문했으면!
				if (SaekYak[row][column] == 0) continue; // 패스
				
				// 방문안해서 새로운 구역이면!
				SaekYakCount++;
				int currentColor = SaekYak[row][column]; // 현재 구역의 색상
				
				Queue<int[]> q = new ArrayDeque<>();
				q.add(new int[] {row, column});
				SaekYak[row][column] = 0; // 방문 처리
						
				// BFS 시작
				while (!q.isEmpty()) {
					int currentRow = q.peek()[0];
					int currentColumn = q.peek()[1];
					q.poll(); 
					
					for (int dir = 0; dir < 4; dir++) {
						int newRow = currentRow + deltaRow[dir];
						int newCol = currentColumn + deltaColumn[dir];
						
						// 범위를 벗어났으면 패스
						if (newRow < 0 || newRow >= arrSize || newCol < 0 || newCol >= arrSize) continue;
						// 현재랑 같은 색상이 아니면 패스
						if (SaekYak[newRow][newCol] != currentColor) continue;
							
						// 방문함
						SaekYak[newRow][newCol] = 0;
						q.add(new int[] {newRow, newCol});	
					}
				}
			}		
		}
	}
	
	public static void nonoSaekYakBFS() {
		for (int row = 0; row < arrSize; row++) {
			for (int column = 0; column < arrSize; column++) {
				// 방문하면 0으로 바꾸기, 이미 방문했으면!
				if (nonoSaekYak[row][column] == 0) continue; // 패스
				
				// 방문안해서 새로운 구역이면!
				nonoSaekYakCount++;
				int currentColor = nonoSaekYak[row][column]; // 현재 구역의 색상
				
				Queue<int[]> q = new ArrayDeque<>();
				q.add(new int[] {row, column});
				nonoSaekYak[row][column] = 0; // 방문 처리
						
				// BFS 시작
				while (!q.isEmpty()) {
					int currentRow = q.peek()[0];
					int currentColumn = q.peek()[1];
					q.poll(); 
					
					for (int dir = 0; dir < 4; dir++) {
						int newRow = currentRow + deltaRow[dir];
						int newCol = currentColumn + deltaColumn[dir];
						
						// 범위를 벗어났으면 패스
						if (newRow < 0 || newRow >= arrSize || newCol < 0 || newCol >= arrSize) continue;
						// 현재랑 같은 색상이 아니면 패스
						if (nonoSaekYak[newRow][newCol] != currentColor) continue;
							
						// 방문함
						nonoSaekYak[newRow][newCol] = 0;
						q.add(new int[] {newRow, newCol});	
					}
				}
			}		
		}
	}
}
