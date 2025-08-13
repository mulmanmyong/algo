import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main16926 {
	
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int rowSize;
	public static int columnSize;
	public static int rotateCount;
	public static int[][] NMArray;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		columnSize = Integer.parseInt(st.nextToken());
		rotateCount = Integer.parseInt(st.nextToken());
		
		
		NMArray = new int[rowSize][columnSize];
		for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
				NMArray[rowIndex][columnIndex] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 회전을 해봅시다 
		for (int layer = 0; layer < Math.min(rowSize, columnSize) / 2; layer++) {
			Deque<Integer> dq = new ArrayDeque<>();
			
			int x1 = layer, y1 = layer;
			int x2 = rowSize - layer - 1, y2 = columnSize - layer - 1;
			
			// layer의 요소 deque에 넣기 (시계방향 기준)
			// 왼쪽 세로줄 (위 -> 아래)
			for (int row = x1; row < x2; row++)	dq.add(NMArray[row][y1]);
			// 아래 가로줄 (왼 -> 오) 
			for (int column = y1; column < y2; column++) dq.add(NMArray[x2][column]);
			// 오른쪽 세로줄 (아래 -> 위)
			for (int row = x2; row > x1; row--)	dq.add(NMArray[row][y2]);
			// 위 가로줄 (오 -> 왼)
			for (int column = y2; column > y1; column--) dq.add(NMArray[x1][column]);
		
			// 회전수 최적화
			int rotate_count = rotateCount % dq.size();
			for (int rotate = 0; rotate < rotate_count; rotate++) {
				dq.addFirst(dq.removeLast());
			}
			
			// 회전한것을 배열에 다시 넣기 
			// 왼쪽 세로줄 (위 -> 아래)
			for (int row = x1; row < x2; row++)	NMArray[row][y1] = dq.removeFirst();
			// 아래 가로줄 (왼 -> 오) 
			for (int column = y1; column < y2; column++) NMArray[x2][column] = dq.removeFirst();
			// 오른쪽 세로줄 (아래 -> 위)
			for (int row = x2; row > x1; row--)	NMArray[row][y2] = dq.removeFirst();
			// 위 가로줄 (오 -> 왼)
			for (int column = y2; column > y1; column--) NMArray[x1][column] = dq.removeFirst();
		}
		
		for (int rowIndex = 0; rowIndex < rowSize; rowIndex++) {
			for (int columnIndex = 0; columnIndex < columnSize; columnIndex++) {
				sb.append(NMArray[rowIndex][columnIndex]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
