import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1992_쿼드트리_분할정복 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int quadTreeSize;
	static char[][] quadtree;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		quadTreeSize = Integer.parseInt(br.readLine().trim());
		quadtree = new char[quadTreeSize][quadTreeSize];
		for (int row = 0; row < quadTreeSize; row++) {
			String token = br.readLine().trim();
			for (int column = 0; column < quadTreeSize; column++) {
				quadtree[row][column] = token.charAt(column);
			}
		}
		
		// 모두다 0이면 0으로 압축 가능 모두다 1이면 1로 압축 가능
		// 그렇지 않으면 4등분, 제일 작게 쪼개졌을 땐 그냥 해당하는 숫자
		// 나눠질때 구분자로 ()를 이용
		// 파라미터로 쿼드트리를 볼 시작 좌표, 그리고 확인할 길이를 줌
		doQuadTree(0, 0, quadTreeSize);
		
		System.out.println(sb);
	}
	
	public static void doQuadTree(int row, int column, int length) {
		
		// 제일 작게 쪼개졌을 때
		if (length == 1) {
			sb.append(quadtree[row][column]);
			return;
		}
		// 아직 더 쪼갤 수 있을 때
		else {
			// 압축 가능한지(쿼드트리인지) 확인하기
			if (isQuadTree(row, column, length)) {
				// 참이면 압축가능
				// 압축하고, 더이상 진행 필요 X
				sb.append(quadtree[row][column]);
				return;
			}
			else {
				// 거짓이면 압축 불가능 쪼개기 
				// 구분자로 열고
				sb.append('(');
				// 왼쪽 위
				doQuadTree(row, column, length/2);
				// 오른쪽 위
				doQuadTree(row, column+length/2, length/2);
				// 왼쪽 아래
				doQuadTree(row+length/2, column, length/2);
				// 오른쪽 아래
				doQuadTree(row+length/2, column+length/2, length/2);
				// 구분자로 닫기
				sb.append(')');
			}
		}
	}
	
	public static boolean isQuadTree(int row, int column, int length) {
		char check = quadtree[row][column];	
		for (int rowIndex = row; rowIndex < row + length; rowIndex++) {
			for (int columnIndex = column; columnIndex < column + length; columnIndex++) {
				if (check != quadtree[rowIndex][columnIndex]) return false;
			}
		}
		return true;
	}
}
