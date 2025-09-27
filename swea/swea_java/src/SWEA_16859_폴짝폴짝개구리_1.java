import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_16859_폴짝폴짝개구리_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * 개구리는 반드시 파리를 뛰어 넘어야 상, 하, 좌, 우 방향으로 움직일 수 있음. 파리와 개구리 사이의 거리는 상관 x
 * 개구리가 파리 하나를 뛰어 넘으면 빈칸에 위치, 또는 파리를 먹고 그 곳에 위치할 수 있음
 * 개구리는 한 번 넘은 파리 다시 넘을 수 있음
 * 개구리 총 세번까지 이동 가능
 * 
 * 개구리는 맵 범위 밖으로 나갈 수 없다
 * 개구리는 2마리의 파리를 뛰어 넘을 수 없다
 * 개구리는 항상 1마리가 존재
 * 
 * 3번의 이동으로 잡을 수 있는 모든 파리 개수의 합
 */
	
	// 움직이는 횟수
	static final int TOTAL_MOVE = 3;
	// 테스트 케이스 입력
	static int testCase;
	// 맵의 크기 N
	static int N;
	// 맵의 정보를 입력
	// 파리없는 칸 0, 파리있는 칸 1, 개구리 있는칸 2
	static int[][] initMap;
	// 잡아먹었는지 체크
	static boolean[][] eat;
	// 잡았다 파리
	static int catchCount;
	
	// 방향배열
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaCol = {0, 0, -1, 1};
	
	public static void move(int currentRow, int currentCol, int moveCount) {
		
//		// 배열 출력 테스트
//		printTest(catchMap);
//		System.out.println();
		// 기저 조건 : 움직인 횟수가 TOTAL_MOVE에 도달하면
		if (moveCount == TOTAL_MOVE) {
			return;
		}
		
		// 상하 좌우로 움직이기 
		for (int dir = 0; dir < 4; dir++) {
			
			int newRow = currentRow + deltaRow[dir];
			int newCol = currentCol + deltaCol[dir];
			
			// 뛰어넘을 위치
			int jumpRow = -1;
			int jumpCol = -1;
			
			// 현재 결정된 방향으로 뛰어 넘을 수 있는 파리 있는 지 확인
			// 현재 결정된 방향으로 증가 시키며 파리가 한마리 있고, 그 다음에 공백이나 파리가 있는 지 확인,
			// 공백이 있어도 해당 위치로 점프, 파리가 있으면 해당 위치로 점프하고 잡았다 파리
			
			// 처음 나오는 파리의 위치 찾기 -> 뛰어넘을 위치
			while (true) {
				// 범위를 벗어나면 멈춥니닷
				if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N)		break;
				
				// 파리가 있는 위치인가요?
				if (initMap[newRow][newCol] == 1) {
					// 최초로 있는 파리의 위치네요.
					// 저장
					jumpRow = newRow;
					jumpCol = newCol;
					break;
				}
				
				// 찾을때까지 증가
				newRow += deltaRow[dir];
				newCol += deltaCol[dir];
			}
			
			// 뛰어 넘을 파리가 있나요?
			if (jumpRow != -1) {
				// 뛰어 넘을 파리가 있네요
				// 뛰어 넘읍시다.
				// 착지 위치
				int landRow = jumpRow + deltaRow[dir];
				int landCol = jumpCol + deltaCol[dir];
				
				while (true) {
					
					// 범위를 벗어나면 안됩니닷
					if (landRow < 0 || landRow >= N || landCol < 0 || landCol >= N)	break;
					
					// 범위 안이면..
					// 경우 1: 착지 지점에 다른 파리가 있는 경우
					if (initMap[landRow][landCol] == 1) {
						// 파리가 있으니 먹음
						eat[landRow][landCol] = true;

						// 개구리가 떠나고 착지
						initMap[currentRow][currentCol] = 0; 
						initMap[landRow][landCol] = 2;

						// 다음 탐색
						move(landRow, landCol, moveCount + 1); 

						// 원복
						initMap[landRow][landCol] = 1; 
						initMap[currentRow][currentCol] = 2;

						// 두 마리 이상 뛰어넘을 수 없으므로, 종료
						break;
					}

					// 경우 2: 착지 지점이 빈칸인 경우
					if (initMap[landRow][landCol] == 0) {
						// 개구리가 떠나고 착지
						initMap[currentRow][currentCol] = 0;
						initMap[landRow][landCol] = 2;

						move(landRow, landCol, moveCount + 1);

						// 원복
						initMap[landRow][landCol] = 0;
						initMap[currentRow][currentCol] = 2;
					}

					// 같은 방향으로 다음 착지 지점 탐색
					landRow += deltaRow[dir];
					landCol += deltaCol[dir];
				}
			}
			
			// 뛰어 넘을 파리가 없네요..
			// 다른 방향 탐색 
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			N = Integer.parseInt(br.readLine().trim());
			
			initMap = new int[N][N];
			eat = new boolean[N][N];
			int startRow=0;
			int startCol=0;
			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < N; col++) {
					initMap[row][col] = Integer.parseInt(st.nextToken());
					if (initMap[row][col] == 2) {
						startRow = row;
						startCol = col;
					}
				}
			}
			
			// 상하좌우로 움직이기
//			catchCount = 0;
//			System.out.println(startRow+" "+startCol);
			move(startRow, startCol, 0);
			
			// 잡아먹은 파리의 개수를 봅시다
			catchCount = 0;
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (eat[row][col])	{
//						catchCount += eat[row][col];
						catchCount++;
					}
				}
			}
			
//			printTest(eat);
			
			sb.append('#').append(test_case).append(' ').append(catchCount).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void printTest(int[][] arr) {
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				System.out.print(arr[row][col]+" ");
			}
			System.out.println();
		}
	}
	
}
