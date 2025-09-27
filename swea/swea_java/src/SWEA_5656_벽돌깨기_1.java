import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 중복 순열과 
public class SWEA_5656_벽돌깨기_1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	// 구슬을 쏠 수 있는 횟수 N, 벽돌들의 정보 W(column), H(row)
	static int N, W, H;
	// 벽돌들의 정보를 입력받음
	static int[][] blocks;
	// 구슬을 쏘는 위치
	static int[] shootPosition;
	// 최대로 벽돌을 깬 개수
	static int maxBreak;
	
	// 시뮬레이션 수행하는 공간
	static int[][] tempBlock;
	static int currentBreak;
	
	// 상 하 좌 우
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaColumn = {0, 0, -1, 1};
	
	// 제일 상단을 찾는 메서드
	public static int findTop(int column) {
		// 0이 아닌 위치를 반환
		for (int row = 0; row < H; row++) {
			if (tempBlock[row][column] > 0)		return row;
		}
		
		// 그렇지 않으면 -1을 반환 -> 이미 벽돌이 다깨졌음
		return -1;
	}
	
	// 중력 적용
	public static void gravity() {
		for (int column = 0; column < W; column++) {
			// 벽돌을 채워넣을 가장 아래쪽 행 인덱스
			int bottom = H - 1;
			// 가장 아래 행부터 위로 올라오면서 벽돌을 찾음
			for (int row = H - 1; row >= 0; row--) {
				if (tempBlock[row][column] > 0) {
					// 0이 아닌 벽돌을 찾으면 bottom 위치로 내림
					int temp = tempBlock[row][column];
					tempBlock[row][column] = 0; // 원래 위치는 비움
					tempBlock[bottom][column] = temp;
					bottom--; // 다음 벽돌이 채워질 위치는 한 칸 위
				}
			}
		}
	}
	
	// 벽돌 박살내기
	public static void breakBlock(int currentRow, int currentColumn) {
		
		// 현재 위치 벽돌 깨기 
		int currentCount = tempBlock[currentRow][currentColumn]-1;
		tempBlock[currentRow][currentColumn] = 0;
		currentBreak++;
		
		// 4방향 박살내기
		for (int direction = 0; direction < 4; direction++) {
			// 이동하는 횟수는 
			for (int count = 1; count <= currentCount; count++) {
				// 1칸떨어진곳, 2칸 떨어진곳, 3칸 떨어진곳...
				int newRow = currentRow + deltaRow[direction]*count;
				int newColumn = currentColumn + deltaColumn[direction]*count;
				
				// 범위 벗어나면 종료
				if (newRow < 0 || newRow >= H || newColumn < 0 || newColumn >= W)	break;
				
				// 그렇지 않으면 동작수행
				// 1이면 자기 자신만 박살내기
				if (tempBlock[newRow][newColumn] == 1) {
					tempBlock[newRow][newColumn] = 0;
					currentBreak++;
				}
				// 2이상이면 재귀로 해당 위치 기준으로 박살내기
				else if (tempBlock[newRow][newColumn] >= 2){
					breakBlock(newRow, newColumn);
				}
			}
		}
	}
	
	// 벽돌깨기 시작
	public static void simulation() {
		currentBreak = 0; // 초기화
		
		// 현재 상황에서의 벽돌깨기를 수행할 것임
		tempBlock = new int[H][W];
		for (int row = 0; row < H; row++) {
			tempBlock[row] = blocks[row].clone();
		}
		
		// 위치 가져오기
		for (int shootIndex = 0; shootIndex < N; shootIndex++) {
			int currentColumn = shootPosition[shootIndex];
			int currentRow = findTop(currentColumn);
			
			// 벽돌이 있는 좌표를 가져왔는데 이 때 currentRow가 -1 이면 해당 열은 이미 벽돌깨기를 다 수행헀음
			// 그러므로 패스
			if (currentRow == -1)	continue;
			
			// 현위치 포함 상하좌우 벽돌깨기
			breakBlock(currentRow, currentColumn);
			
			// 블록 박살 냈으니 중력을 적용해야해
			gravity();
			
		}
		
		// 구슬 다 쏘면 박살낸 개수 최신화
		maxBreak = Math.max(maxBreak, currentBreak);
	}
	
	// 구슬을 쏘는 위치 정하기
	public static void setPosition(int count) {
		// 기저 조건 : N개의 위치를 골랐으면
		if (count == N) {
			// 해당 위치로 시뮬레이션 수행
			simulation();
			return;
		}
		
		// 중복 가능하게 구슬 쏘는 위치 정하기
		for (int select = 0; select < W; select++) {
			// 해당 위치에서 쏩니다
			shootPosition[count] = select;
			setPosition(count + 1); // 다음으로 갑니다
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			// // 구슬을 쏠 수 있는 횟수 N, 벽돌들의 정보 W(column), H(row) 를 입력받음
			st = new StringTokenizer(br.readLine().trim());	
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			// 벽돌의 정보를 입력받음
			blocks = new int[H][W];
			// 벽돌의 개수를 카운트
			int initBlockCount = 0;
			for (int row = 0; row < H; row++) {
				st = new StringTokenizer(br.readLine().trim());	
				for (int column = 0; column < W; column++) {
					blocks[row][column] = Integer.parseInt(st.nextToken());
					if (blocks[row][column] >= 1) {
						initBlockCount++;
					}
				}
			}
			
			maxBreak = 0;
			// 구슬을 쏘는 위치를 중복 순열로 결정하기
			shootPosition = new int[N];
			setPosition(0);
			
			// 결과는 남은 벽돌의 개수를 구하는 것 
			sb.append('#').append(test_case).append(' ').append(initBlockCount-maxBreak).append('\n');
		}
		System.out.println(sb);
	}
}
