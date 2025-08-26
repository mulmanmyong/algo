import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 시뮬레이션, 백트래킹으로 풀이 
public class BOJ_15683_감시_1 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int rowSize;
	static int columnSize;
	static int[][] area;
	static List<int[]> cctvList;
	static int minCount;
	
	static int[] deltaRow = {-1, 0, 1, 0};
	static int[] deltaColumn = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		columnSize = Integer.parseInt(st.nextToken());
		
		area = new int[rowSize][columnSize];
		// cctv들을 모두 넣어주기
		cctvList = new ArrayList<>();
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine());
			for (int column = 0; column < columnSize; column++) {
				area[row][column] = Integer.parseInt(st.nextToken());
				
				// 해당 위치가 cctv이면 삽입
				if (1 <= area[row][column] && area[row][column] <= 5) {
					cctvList.add(new int[] {area[row][column], row, column});
				}
			}
		}
		
		// 0은 빈칸, 1~5는 cctv, 6은 벽. 
		// CCTV는 벽을 통과할 수 없음. 
		// 백트래킹을 이용해서 최적의 방향을 모두 세팅해야함
		// 시작 cctv인덱스, 현재의 지도를 넘겨줌
		minCount = rowSize*columnSize;
		findBestDirection(0, area);
		
		sb.append(minCount);
		System.out.println(sb);
		
	}
	
	public static void findBestDirection(int cctvIndex, int[][] currentMap) {
		// 최적의 방향을 찾기 
		// 모든 cctv의 방향을 모두 설정해야함
		
		// 끝까지 방향을 설정했으면
		if (cctvIndex == cctvList.size()) {
			// 사각지대 count 후 최소 갱신
			int curCount = 0;
			for (int i = 0; i < rowSize; i++) {
				for (int j = 0; j < columnSize; j++) {
					if (currentMap[i][j] == 0)	curCount++;
				}
			}
			minCount = Math.min(minCount, curCount);
			return;
		}
		
		// 구현, 현재 CCTV의 방향을 가능한 모든 방향으로 설정하기
		int curCCTV = cctvList.get(cctvIndex)[0];
		int curRow = cctvList.get(cctvIndex)[1];
		int curColumn = cctvList.get(cctvIndex)[2];
		
		// 원복하기 어려우므로, 새로운 임시배열을 이용하여
		// 연산을 수행
		int[][] tempArr;
		// 모든 방향 연산 수행
		for (int dir = 0; dir < 4; dir++) {
			// 임시배열에 현재 배열 복사 
			tempArr = new int[rowSize][columnSize];
			for (int copyIndex = 0; copyIndex < rowSize; copyIndex++) {
				tempArr[copyIndex] = currentMap[copyIndex].clone();
			}
			
			// 현재 씨씨티비의 현재 방향으로의 감시방향 지정
			cctvMOD(tempArr, curCCTV, curRow, curColumn, dir);
			// 방향을 설정한 배열을 기반으로, 다음 재귀 수행
			findBestDirection(cctvIndex + 1, tempArr);
		}
	}
	
	// cctvMOD
	public static void cctvMOD(int[][] arr, int cctvNum, int row, int column, int dir) {
		// 상 우 좌 하
		switch(cctvNum) {
			case 1:
				// 한방향만 봄
				view(arr, row, column, dir);
				break;
			case 2:
				// 정반대인 양방향만 봄
				view(arr, row, column, dir);
				view(arr, row, column, (dir+2)%4);
				break;
			case 3:
				// 이웃한 양방향을봄
				view(arr, row, column, dir);
				view(arr, row, column, (dir+1)%4);
				break;
			case 4:
				// 3방향을 봄
				view(arr, row, column, dir);
				view(arr, row, column, (dir+1)%4);
				view(arr, row, column, (dir+2)%4);
				break;
			case 5:
				view(arr, row, column, dir);
				view(arr, row, column, (dir+1)%4);
				view(arr, row, column, (dir+2)%4);
				view(arr, row, column, (dir+3)%4);
				break;
		}
		
	}
	
	// cctv가 보고 있는 방향 채우기 
	public static void view(int[][] arr, int currentRow, int currentColumn, int direction) {
		// 방향 채우기
		int nextRow = currentRow;
		int nextColumn = currentColumn;
		
		while (true) {
			nextRow += deltaRow[direction];
			nextColumn += deltaColumn[direction];
			
			// 범위 벗어나거나 벽(6)을 만나면 중단
			if (nextRow < 0 || nextRow >= rowSize || nextColumn < 0 || nextColumn >= columnSize)	break;
			if (arr[nextRow][nextColumn] == 6)	break;
			
			// 그렇지 않으면 감시 중으로 변경(-1)
			if (arr[nextRow][nextColumn] == 0) {
				arr[nextRow][nextColumn] = -1;
			}
		}
	}
}
