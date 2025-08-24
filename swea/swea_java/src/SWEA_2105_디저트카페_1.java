import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2105_디저트카페_완전탐색 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int areaSize;
	static int[][] area;
	static int maxCount;
	
	// 대각선 이동
	static int[] deltaRow = {-1, -1, 1, 1};    // 좌 우 좌 우
	static int[] deltaColumn = {-1, 1, -1, 1}; // 상 상 히 하
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			
			areaSize = Integer.parseInt(br.readLine().trim());
			area = new int[areaSize][areaSize];
			for (int row = 0; row < areaSize; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int column = 0; column < areaSize; column++) {
					area[row][column] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 시작 지점은 완탐하기 
			maxCount = -1;
			for (int row = 0; row < areaSize; row++) {
				for (int column = 0; column < areaSize; column++) {
					// 어차피 사각형이 최소로 해도 안만들어지는 경우 
					if (column == 0 || column == areaSize-1 || row >= areaSize-2)	continue;
					// 현재 좌표를 기준으로 최대 개수 찾기 
					maxCount = Math.max(calculateCount(row, column), maxCount);
				}
			}
			
			sb.append('#').append(test_case).append(' ').append(maxCount).append('\n');
		}
		System.out.println(sb);
	}
	
	public static int calculateCount(int startRow, int startColumn) {
		// 현재 좌표에서의 최대 방문 횟수 계산하기
		int currentMaxCount = -1;
		
		// 얼마나 움직일 것인가
		// 좌상 우하 라인 대각성
		for (int aLength = 1; aLength < areaSize; aLength++) {
			// 좌하 우상 라인 대각선
			for (int bLength = 1; bLength < areaSize; bLength++) {
				
				// 해당 경우가 가능해도 최대 길이보다 작거나 같으면 밑에 작업 불필요
				if (2*aLength + 2*bLength <= maxCount)	continue;
				
				// 동일한 디저트를 먹지 않기 위해 확인할 배열 : 1 ~ 100까지
				boolean[] visited = new boolean[101];
				// 멀쩡한 경로인지 판단
				boolean isPossible = true;
				
				int row = startRow;
				int column = startColumn;
				// 시작점 방문함
				visited[area[row][column]] = true;
				
				// 어찌됐든 4각형만 이루어지면 됨, 동일한 방향으로 사각형을 구성하다보면, 중복없이 확인가능
				// 우하 -> 좌하 -> 좌상 -> 우상 순으로 돌면 사각형 완성!
				
				// 방향 배열 좌상 우상 좌하 우하 : 0 1 2 3
				
				// 우하
				for (int moveCount = 0; moveCount < aLength; moveCount++) {
					row += deltaRow[3];
					column += deltaColumn[3];
					
					// 갈 수 없는 경로 : 범위를 벗어나거나 이미 먹은 디저트임
					if (row < 0 || row >= areaSize || column < 0 || column >= areaSize || visited[area[row][column]]) {
						isPossible = false;
						break;
					}
					
					// 갈 수 있으면 해당 디저트 먹음 표시
					visited[area[row][column]] = true;
				}
				// 불가능한 경로면 패스
				if (!isPossible)	continue;
				
				// 좌하
				for (int moveCount = 0; moveCount < bLength; moveCount++) {
					row += deltaRow[2];
					column += deltaColumn[2];
					
					// 갈 수 없는 경로 : 범위를 벗어나거나 이미 먹은 디저트임
					if (row < 0 || row >= areaSize || column < 0 || column >= areaSize || visited[area[row][column]]) {
						isPossible = false;
						break;
					}
					
					// 갈 수 있으면 해당 디저트 먹음 표시
					visited[area[row][column]] = true;
				}
				// 불가능한 경로면 패스
				if (!isPossible)	continue;
				
				// 좌상
				for (int moveCount = 0; moveCount < aLength; moveCount++) {
					row += deltaRow[0];
					column += deltaColumn[0];
					
					// 갈 수 없는 경로 : 범위를 벗어나거나 이미 먹은 디저트임
					if (row < 0 || row >= areaSize || column < 0 || column >= areaSize || visited[area[row][column]]) {
						isPossible = false;
						break;
					}
					
					// 갈 수 있으면 해당 디저트 먹음 표시
					visited[area[row][column]] = true;
				}
				// 불가능한 경로면 패스
				if (!isPossible)	continue;
				
				// 우상
				for (int moveCount = 0; moveCount < bLength; moveCount++) {
					row += deltaRow[1];
					column += deltaColumn[1];
					
					// 마지막 지점이라서 시작지점 잘 돌아왔는 지 확인
					// 시작 지점에 도달 했으면 정상적으로 디저트카페 투어 완료
					if (row == startRow && column == startColumn) {
						// 현재까지의 이동거리가 방문한 디저트카페수
						// 최대로 갱신
						currentMaxCount = Math.max(currentMaxCount, 2*aLength + 2*bLength);
						break;
					}
					
					// 갈 수 없는 경로 : 범위를 벗어나거나 이미 먹은 디저트임
					if (row < 0 || row >= areaSize || column < 0 || column >= areaSize || visited[area[row][column]]) {
						isPossible = false;
						break;
					}
					
					// 갈 수 있으면 해당 디저트 먹음 표시
					visited[area[row][column]] = true;
				}
				// 불가능한 경로면 패스
				if (!isPossible)	continue;

			}
		}
		
		return currentMaxCount;
	}
}
