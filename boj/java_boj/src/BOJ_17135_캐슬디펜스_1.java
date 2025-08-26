import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 시뮬레이션 풀이 
public class BOJ_17135_캐슬디펜스_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int rowSize;
	static int columnSize;
	static int attackLimit;
	
	static int[][] board; // 게임판
	static int[][] initArr; // 초기화를 위한 배열
	static int[] archer; // 궁수 배치하기
	static int[] blankArr; // 게임 진행하면서 제일 위에칸은 공백으로 만들기 위함
	static int maxKill; // 최대 사살 수 
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		columnSize = Integer.parseInt(st.nextToken());
		attackLimit = Integer.parseInt(st.nextToken());
		
		board = new int[rowSize][columnSize]; // 궁수가 있을 위치 포함 
		initArr = new int[rowSize][columnSize]; // 초기화용 배열
		for (int row = 0; row < rowSize; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int column = 0; column < columnSize; column++) {
				board[row][column] = Integer.parseInt(st.nextToken());
			}
		}


		// 초기 배열 복사해두기
		for (int row = 0; row < rowSize; row++) {
			initArr[row] = board[row].clone();
		}
		
		// 빈칸은 0, 적이있는칸은 1 
		// 궁수는 3명 배치 
		// 궁수를 3명 배치하는 모든 경우를 배치 
		// 궁수는 rowSize열에 배치하기 
		// 궁수와 적의 거리가 공격범위 이하면 공격 가능 
		
		// 궁수의 위치를 기반으로 공격할 수 있는 적을 탐색하기 
		// 같은 적을 공격할 수 있지만, 서로 다른 적을 공격할 수 있으면 다른 적을 공격할 수 있도록 할 것 
		// 제일 가까이 있는 적 먼저 공격할 것 
		
		archer = new int[columnSize];
		blankArr = new int[columnSize];
		maxKill = -1;
		setArcher(0, 0);
		
		sb.append(maxKill);
		System.out.println(sb);
	}
	
	// 궁수들 배치 조합
	public static void setArcher(int columnIndex, int setCount) {
		// 끝까지 도달 했을 경우
		if (columnIndex == columnSize) {
			// 근데 궁수 3명을 배치했으면
			if (setCount == 3) {
				// 공격 
				attack();
			}
			return;
		}
			
		// 현재 인덱스에 궁수를 배치, 궁수는 2로 처리
		// 근데 배치하는 경우는 궁수를 덜 배치했을 경우
		if (setCount < 3) {
			archer[columnIndex] = 2;
			setArcher(columnIndex + 1, setCount + 1);
		}
		// 현재 인덱스에 궁수를 안배치 
		archer[columnIndex] = 0;
		setArcher(columnIndex + 1, setCount);
	}
	
	// 거리 계산하기
	public static int calculateDistance(int r1, int c1, int r2, int c2) {
		return Math.abs(r1-r2) + Math.abs(c1-c2);
	}
	// 적을 공격하기
	public static void attack() {
		// 현재 배치에서 적을 공격
		int currentKill = 0;
		
		// 각 라운드 진행
		for (int round = rowSize-1; round >= 0; round--) {
			
			// 사살했는지 확인할 배열
			// 이번 턴에 공격할 대상들을 저장할 리스트
			ArrayList<int[]> targets = new ArrayList<>();
			
			for (int arhcerColumnIndex = 0; arhcerColumnIndex < columnSize; arhcerColumnIndex++) {
				// archer가 2이면 궁수임 , columnIndex는 2인 인덱스 rowIndex는 rowSize고정 
				// 궁수가 배치 안되어 있으면 
				if (archer[arhcerColumnIndex] != 2) continue;
				
				// 궁수의 위치를 찾으면 진행, 사살할 적 찾기 진행 
				
				/*
				 * 적을 사살할 수 있는 조건
				 * 1. 궁수와 공격하려는 적의 거리가 사정거리 안에 있기
				 * 2. 거리가 같을 경우 제일 왼쪽에 있는 적을 먼저 사살
				 * 3. 같은 적이 여러 궁수에게 공격당할 수 있음
				 * 4. 궁수의 공격이 끝나면 적은 아래로 한칸 이동
				 */

				int minDistance = Integer.MAX_VALUE;
				int targetRow = -1;
				int targetColumn = -1;
				
				// 제일 가까운 적 먼저 사살
				// 거리가 같을 경우 왼쪽에 있는 적 먼저 사살
				// 왼쪽 우선 탐색을 위해 열 먼저 탐색 
				for (int column = 0; column < columnSize; column++) {
					for (int row = 0; row < rowSize; row++) {
						// 적이 있으면
						if (board[row][column] == 1) {
							int distance = calculateDistance(row, column, rowSize, arhcerColumnIndex);
							
							// 사정거리 안에 있는지
							if (distance <= attackLimit) {
								// 사정거리 안에 있으면 최소 거리인지
								if (distance < minDistance) {
									// 최소 거리면 사살할 적의 위치 저장
									minDistance = distance;
									targetRow = row;
									targetColumn = column;
								} 
								// 거리가 같으면
								else if (distance == minDistance) {
									// 현재 사살할 적보다 더 왼쪽에 있는지
									if (column < targetColumn) {
										// 더 왼쪽에 있으면 타겟 변경 
										targetRow = row;
										targetColumn = column;
									}
								}
							}
						}
					}
				}
				
				// 탐색 결과 사살할 적이 있으면, 타겟 좌표를 데스노트에 추가
				if (targetRow != -1) {
					targets.add(new int[]{targetRow, targetColumn});
				}
			}
			
			// 데스노트에 있는 적 모두 죽이기
			for (int[] target : targets) {
				// 아직 다른 궁수에 의해 죽지 않았다면
				if (board[target[0]][target[1]] == 1) {
					// 죽어라
					board[target[0]][target[1]] = 0; 
					currentKill++;
				}
			}
			
			// 궁수의 공격이 끝남, 다음 라운드 이동 
			// 한칸씩 내리기
			for (int i = rowSize - 1; i > 0; i--) {
				board[i] = board[i-1].clone();
			}
			
			// 맨 윗줄은 빈 칸으로 채우기 
			board[0] = blankArr.clone(); 
		}
		
		// 최대 사살 수 갱신
		maxKill = Math.max(maxKill, currentKill);
		
		// 현재 배치 공격 완 원복
		for (int row = 0; row < rowSize; row++) {
			board[row] = initArr[row].clone();
		}
	}
}
