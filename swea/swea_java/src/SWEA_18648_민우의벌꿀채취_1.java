import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class SWEA_18648_민우의벌꿀채취_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	/*
	 * 몇 가지 규칙을 정해 벌꿀을 채취하려고 함
	 * 규칙
	 * 1) 하나의 벌집에 저장된 꿀을 채취할 수 있음
	 * 2) 벌꿀을 채취하고자할 때 반드시 인접한 벌집의 벌꿀을 채취해야 함
	 * 3) 총 4개의 벌집에 저장된 벌꿀을 채취해야 함
	 * 4) 비용 편익이 최대가 되어야 함
	 *
	 * 비용 편익 계산 방법
	 * Ci = i번째 벌집에 저장된 벌꿀의 양
	 * 비용 편익 = (C1 + C2 + C3 + C4)^2 (제곱)
	 *
	 * 연속적으로 연결되어 있어야만 가능, 한그룹 ,, 2그룹으로 나누어지면 안됨
	 *
	 * [제약 사항]
	 * 1. 벌집의 가로(W), 세로(H)의 크기는 3이상 15이하의 정수. (3<=W,H<=15)
	 * 2. 한 벌집의 벌꿀 양은 1이상 1000이하의 정수. (1<=U<=1000)
	 *
	 * [입력]
	 * 1. 테스트케이스 입력
	 * 1-1. 벌집의 가로(W), 세로(H)입력
	 * 1-2. H줄에 걸쳐 벌꿀의 양 주어짐
	 */

	// 테스트 케이스 입력
	static int testCase;
	// W, H 입력
	static int W, H;
	// 벌집정보
	static int[][] HoneyComb;
	// 방문처리
	static boolean[][] visited;

	// 벌꿀을 채취해야하는 개수
	static final int TOTAL_GET_HONEY_COUNT = 4;
	// 최대가 되는 편익의 개수
	static long maxCost;

	// 방향배열
	// 한칸이 벌집이라 6각형이니깐..

	// 열기준으로 움직이는 방향이 다르다는 것을 알음
	// 좌상 상 우상 좌하 하 우하 순으로 생성
	// 짝수 열
	static int[] even_deltaRow = {-1, -1, -1, 0, 1, 0};
	static int[] even_deltaCol = {-1, 0, 1, -1, 0, 1};

	// 홀수 열
	static int[] odd_deltaRow = {0, -1, 0, 1, 1, 1};
	static int[] odd_deltaCol = {-1, 0, 1, -1, 0, 1};

	public static void findMaxCost(int currentRow, int currentCol, int count, long cost) {

		// 기저조건 4개 담으면 최대값 비교
		if (count == TOTAL_GET_HONEY_COUNT) {
			maxCost = Math.max(cost, maxCost);
			return;
		}

		// 방향배열 짝수 홀수 다름
		// 열 기준으로
		// 짝수
		if (currentCol % 2 == 0) {
			for (int dir = 0; dir < 6; dir++) {
				int newRow = currentRow + even_deltaRow[dir];
				int newCol = currentCol + even_deltaCol[dir];

				// 범위 벗어나면 안됨
				if (newRow < 0 || newRow >= H || newCol < 0 || newCol >= W) continue;
				// 방문헀어도 안됨
				if (visited[newRow][newCol]) continue;

				// 안벗어났으면 해당 위치 선택했다 처리
				visited[newRow][newCol] = true;
				findMaxCost(newRow, newCol, count + 1, cost + HoneyComb[newRow][newCol]);
				visited[newRow][newCol] = false; // 원복
			}
		} else {
			for (int dir = 0; dir < 6; dir++) {
				int newRow = currentRow + odd_deltaRow[dir];
				int newCol = currentCol + odd_deltaCol[dir];

				// 범위 벗어나면 안됨
				if (newRow < 0 || newRow >= H || newCol < 0 || newCol >= W) continue;
				// 방문헀어도 안됨
				if (visited[newRow][newCol]) continue;

				// 안벗어났으면 해당 위치 선택했다 처리
				visited[newRow][newCol] = true;
				findMaxCost(newRow, newCol, count + 1, cost + HoneyComb[newRow][newCol]);
				visited[newRow][newCol] = false; // 원복
			}
		}
	}

	// 다른 모양을 찾는 메서드
	public static void findAnotherShape(int currentRow, int currentCol) {

		ArrayList<int[]> neighbors = new ArrayList<>();
		// 방향배열 짝수 홀수 다름
		// 열 기준으로
		// 짝수
		if (currentCol % 2 == 0) {
			for (int dir = 0; dir < 6; dir++) {
				int newRow = currentRow + even_deltaRow[dir];
				int newCol = currentCol + even_deltaCol[dir];

				// 범위 벗어나면 안됨
				if (newRow < 0 || newRow >= H || newCol < 0 || newCol >= W) continue;
				// 안 벗어났으면 이웃에 추가
				neighbors.add(new int[]{newRow, newCol});
			}
		} else {
			for (int dir = 0; dir < 6; dir++) {
				int newRow = currentRow + odd_deltaRow[dir];
				int newCol = currentCol + odd_deltaCol[dir];

				// 범위 벗어나면 안됨
				if (newRow < 0 || newRow >= H || newCol < 0 || newCol >= W) continue;
				// 안 벗어났으면 이웃에 추가
				neighbors.add(new int[]{newRow, newCol});
			}
		}

		// 중심점에 붙일 이웃이 3개 미만이면 별 모양이 만들어지지 않음
		if (neighbors.size() < 3) {
			return; // 패스
		}

		// 3개 선택하기
		for (int nIndex1 = 0; nIndex1 < neighbors.size(); nIndex1++) {
			for (int nIndex2 = nIndex1 + 1; nIndex2 < neighbors.size(); nIndex2++) {
				for (int nIndex3 = nIndex2 + 1; nIndex3 < neighbors.size(); nIndex3++) {
					long currentCost = HoneyComb[currentRow][currentCol];
					currentCost += HoneyComb[neighbors.get(nIndex1)[0]][neighbors.get(nIndex1)[1]];
					currentCost += HoneyComb[neighbors.get(nIndex2)[0]][neighbors.get(nIndex2)[1]];
					currentCost += HoneyComb[neighbors.get(nIndex3)[0]][neighbors.get(nIndex3)[1]];

					maxCost = Math.max(maxCost, currentCost);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 테스트 케이스 입력
		testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {

			// W, H 입력 받기
			st = new StringTokenizer(br.readLine().trim());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			// 벌집의 정보 입력 받기
			HoneyComb = new int[H][W];
			for (int row = 0; row < H; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int column = 0; column < W; column++) {
					HoneyComb[row][column] = Integer.parseInt(st.nextToken());
				}
			}

			// 그래프 기법?
			// 벌집모향 6방향으로 인접한 벌집으로 연결됨
			// 일단은 DFS로 완전탐색 수행
			maxCost = 0;
			for (int row = 0; row < H; row++) {
				for (int col = 0; col < W; col++) {
					// 완탐이기에 시작점 마다 다 다름
					visited = new boolean[H][W];
					visited[row][col] = true; // 시작점 방문 처리
					findMaxCost(row, col, 1, HoneyComb[row][col]);

					// 예외 상황 탐색
					// 현재를 기준으로 이웃한 3개가 선택되는 경우가 위 DFS에선 안나옴
					// 위의 DFS에서는 중심을 기준으로 둘러쌓여있는 애들만 선택하는 경우는 안나오기 때문에 왔다갔다 하는 경우는 없게 된다..
					findAnotherShape(row, col);
				}
			}

			sb.append('#').append(test_case).append(' ').append(maxCost * maxCost).append('\n');
		}
		System.out.println(sb);
	}
}