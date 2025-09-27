import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5215_햄버거다이어트_3 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int testCase;
	static int N, L;
	public static int[] flavorScore;
	public static int[] calorie;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
		
			
			flavorScore = new int[N];
			calorie = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				flavorScore[i] = Integer.parseInt(st.nextToken());
				calorie[i] = Integer.parseInt(st.nextToken());
			}
			
			// 상향식 dp
			// dp에 무엇을 담아야할까?
			// 맛의 점수와 칼로리를 담아야겠지?
			// 칼로리 c일 때 얻을 수 있는 최대 맛 점수 
			int[] dp = new int[L+1];
			
			// 일단 첨부터 끝까지 돌아야함
			for (int i = 0; i < N; i++) {
				// 재료를 순서대로 고려를 함
				
				// 이 때 해당 재료가 포함하는 지 안하는 지 고려하기
				// 이를 칼로리 기준으로 판단하기?
				for (int c = L; c >= calorie[i]; c--) {
					// 최대 제한 칼로리에서 현재 칼로리까지 계속 줄여감 
					// 해당 범위 내에서의 최대 맛 점수를 찾기
					// 최대 칼로리로부터 지금까지의 칼로리 점수를 뺏을 때, 그것은 해당 음식을 포함했다는 것. 그것을 점수를 추가해서 최대 점수를 갱신
					// 초기에는 0이겠지만, 음식을 가능한 제한 칼로리 내에서 음식을 더할 수 있음,
					dp[c] = Math.max(dp[c], dp[c - calorie[i]] + flavorScore[i]);
				}
			}
			
			sb.append('#').append(test_case).append(' ').append(dp[L]).append('\n');
		}
		System.out.println(sb);
	}
}
