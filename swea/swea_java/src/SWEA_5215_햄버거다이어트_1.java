import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution5215_Combination {
	
	public static int N;
	public static int L;
	public static int[] flavorScore;
	public static int[] calorie;
	public static int bestPerformance;
	public static int[][] area;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트 케이스의 개수
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; ++test_case) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken()); // 제한 칼로리
			
			// 맛과 칼로리를 담을 각각의 배열
			flavorScore = new int[N];
			calorie = new int[N];
			for (int index = 0; index < N; index++) {
				st = new StringTokenizer(br.readLine());
				flavorScore[index] = Integer.parseInt(st.nextToken());
				calorie[index] = Integer.parseInt(st.nextToken());
			}
			
			// 최고의 만족도를 담을 변수, 초기값은 0
			bestPerformance = 0;
			// 조합으로 풀이, 1개부터 N개의 재료를 고르는 조합의 모든 경우를 고려하기 
			for (int selectCount = 1; selectCount <= N; selectCount++) {
				// 조합을 구현할 때 필요한 파라미터
				// 시작인덱스, 지금 고른재료수, 골라야하는 재료수, 지금까지의 맛점수, 지금까지의 칼로
				// 시작은 0부터, 
				combination(0, 0, selectCount, 0, 0);
			}
			
			// 결과 출력 
			System.out.println("#"+test_case+" "+bestPerformance);
		}
	}
	
	public static void combination(int startIndex, int currentSelectCount, int selectCount, int totalFlavorScore, int totalCalorie) {
		
		// 기저 조건 - 제한 칼로리 넘었을 경우 => 값 저장 없이 return
		if (totalCalorie > L) {
			return;
		}
		
		// 기저 조건 - 골라야하는 횟수만큼 골랐을 경우 => 최대맛만점수 비교하여 업데이트
		if (currentSelectCount == selectCount) {
			if (totalFlavorScore > bestPerformance) { // 지금의 점 수가 제일 베스트면
				bestPerformance = totalFlavorScore; // 업데이트 
				return;
			}
		}
		
		// 구현부 - 재료를 고른다. 
		for (int selectIndex = startIndex; selectIndex < N; selectIndex++) {
			// 지금의 재료를 고르고 다음으로 이동
			// 맛 점수와 칼로리에 더해주면 됨  => return에 의해 모든 경우를 다 순회 가능 
			combination(selectIndex+1, currentSelectCount+1, selectCount, totalFlavorScore+flavorScore[selectIndex], totalCalorie+calorie[selectIndex]);
		}
	}
}
