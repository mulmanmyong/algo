import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_21942_석주의이상한자석_시뮬레이션 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int boardSize;
	static int[][] board;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			
			boardSize = Integer.parseInt(br.readLine().trim());
			board = new int[boardSize][boardSize];
			for (int row = 0; row < boardSize; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int column = 0; column < boardSize; column++) {
					board[row][column] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 첫째 줄에 자석이 없으면 끌려오지 않음
			
			// 자석이 있어도 제일 위의 인력이 작거나 같아도 끌려오지 않음 
			
			// 현재의 자석과 다음 자석까지의 꺼리를 계산하여 인력을 계사ㅑㄴ
			// 다음 자석(덩어리들)의 인력을 계산 : 연속해서 이어져있는 자석의 개수가 그 인력임
			// 끌려온 자석의 인력이 더 크면 정지해있던 자석과 인력이 더해짐
			// 그 힘으로 자석이 가속해서 끌려 감
			
			// 제일 아래에 자석을 둔 경우 
			underMagnetic();
			// 오른쪽에 자석을 둔 경우 
			rightMagnetic();
			
			// 제일 마지막행 자석의 개수 제일 마지막 열 자석 개수 계산
			int rowMagneticCount = 0;
			for (int column = 0; column < boardSize; column++) {
				if (board[boardSize-1][column] == 1) {
					rowMagneticCount++;
				}
			}
			
			int columnMagneticCount = 0;
			for (int row = 0; row < boardSize; row++) {
				if (board[row][boardSize-1] == 1) {
					columnMagneticCount++;
				}
			}
			
			sb.append('#').append(test_case).append(' ').append(rowMagneticCount).append(' ').append(columnMagneticCount).append('\n');
		}
		
		System.out.println(sb);
	}
	
	public static void underMagnetic() {
		// 제일 아래에 자석이 있는 경우
		// 첫째 줄에 자석 유무를 판단하기 
		for (int firstLine = 0; firstLine < boardSize; firstLine++) {
			// 자석이 없으면 동작 수행 안함
			if (board[0][firstLine] == 0)	continue;
			
			// 자석이 있으면 멈출때까지 동작 수행
			double currentPower = 1; // 초기 자석의 힘은 1 
			// 자석의 시작과 끝
			int startIndex = 0;
			int endIndex = 0;
			while (true) {
				
				int waitStart = 0;
				int waitEnd = 0;
				
				// 첫번째 자석의 인력계산
				for (int firstMagnetic = endIndex+1; firstMagnetic < boardSize; firstMagnetic++) {
					if (board[firstMagnetic][firstLine] == 0) {
						currentPower *= 1.9;
						startIndex++;
						endIndex++;
					}
					else {
						waitStart = firstMagnetic;
						break;
					}
				}
				
				// 끝까지 탐색했으면
				if (endIndex == boardSize-1) {
					break;
				}
				
				// 기다리는 자석의 인력계산
				double waitPower = 0; // 초기 자석의 힘은 1
				for (int waitMagnetic = waitStart; waitMagnetic < boardSize; waitMagnetic++) {
					if (board[waitMagnetic][firstLine] == 1) {
						waitPower++;
					}
					else {
						waitEnd = waitMagnetic-1;
						break;
					}
				}
				
				// 자석이 보드 끝까지 이어져있다면, 자석이 끝나는 지점이 설정되지 않음
                if (waitEnd < waitStart) {
                    waitEnd = boardSize - 1;
                }
				
				// 멈추는 조건 : 첫째줄에 끌려오는 자석이 정지해 있는 자석의 인력보다 작거나 같을 때 정지
				if (currentPower <= waitPower) {
					break;
				}
				
				
				// 멈추지 않았다면 인력이 합쳐짐
				currentPower += waitPower;
				endIndex = waitEnd;
			}
			
			// 움직인 곳은 비었으니 0으로 변경
			for (int row = 0; row < startIndex; row++) {
				board[row][firstLine] = 0;
			}
			
			// 시작 자석부터 끝 자석까지 자석으로 채움 (움직인 상태)
			for (int row = startIndex; row <= endIndex; row++) {
				board[row][firstLine] = 1;
			}
		}

	}
	
	public static void rightMagnetic() {
		// 제일 아래 쪽에 자석 있는 경우
		// 첫째 줄에 자석 유무를 판단하기 
		for (int firstLine = 0; firstLine < boardSize; firstLine++) {
			// 자석이 없으면 동작 수행 안함
			if (board[firstLine][0] == 0)	continue;
			
			// 자석이 있으면 멈출때까지 동작 수행
			double currentPower = 1; // 초기 자석의 힘은 1 
			// 자석의 시작과 끝
			int startIndex = 0;
			int endIndex = 0;
			while (true) {
				
				int waitStart = 0;
				int waitEnd = 0;
				
				// 첫번째 자석의 인력계산
				for (int firstMagnetic = endIndex+1; firstMagnetic < boardSize; firstMagnetic++) {
					if (board[firstLine][firstMagnetic] == 0) {
						currentPower *= 1.9;
						startIndex++;
						endIndex++;
					}
					else {
						waitStart = firstMagnetic;
						break;
					}
				}
				
				// 끝까지 탐색했으면
				if (endIndex == boardSize-1) {
					break;
				}
				
				// 기다리는 자석의 인력계산
				double waitPower = 0; // 초기 자석의 힘은 1
				for (int waitMagnetic = waitStart; waitMagnetic < boardSize; waitMagnetic++) {
					if (board[firstLine][waitMagnetic] == 1) {
						waitPower++;
					}
					else {
						waitEnd = waitMagnetic-1;
						break;
					}
				}
				
				// 자석이 보드 끝까지 이어져있다면, 자석이 끝나는 지점이 설정되지 않음
                if (waitEnd < waitStart) {
                    waitEnd = boardSize - 1;
                }
				
				// 멈추는 조건 : 첫째줄에 끌려오는 자석이 정지해 있는 자석의 인력보다 작거나 같을 때 정지
				if (currentPower <= waitPower) {
					break;
				}
				
				
				// 멈추지 않았다면 인력이 합쳐짐
				currentPower += waitPower;
				endIndex = waitEnd;
			}
			
			// 움직인 곳은 비었으니 0으로 변경
			for (int column = 0; column < startIndex; column++) {
				board[firstLine][column] = 0;
			}
			
			// 시작 자석부터 끝 자석까지 자석으로 채움 (움직인 상태)
			for (int column = startIndex; column <= endIndex; column++) {
				board[firstLine][column] = 1;
			}
		}
	}
}
