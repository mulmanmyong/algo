import java.io.*;
import java.util.*;

public class BOJ_9084_동전_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [문제설명]
 * 동전에는 1원, 5원, 10원, 50원, 100원, 500원이 있음
 * 이 동전들로는 정수의 금액을 만들 수 있으며, 그 방법도 여러 가지 있을 수 있음
 * 30원을 만들기 위해서는 1원짜리 30개 또는 10원짜리 2개와 5원짜리 2개 등의 방법이 가능
 * 
 * 동전의 종류가 주어질 때에 주어진 금액을 만드는 모든 방법을 세는 프로그램 작성
 * 
 * [입력]
 * 입력의 첫 줄에는 테스트 케이스의 개수 T가 주어짐
 * 	각 테스트 케이스의 첫줄에는 동전의 가지수 N이 주어짐
 * 	두번째 줄에는 N가지 동전의 각 금액이 오름차순으로 정렬되어 주어짐
 * 	세번째 줄에는 만들어야할 금액 M이 주어짐
 * 
 * [출력]
 * 각 테스트 케이스에 대해 입력으로 주어지는 N가지 동전으로 금액 M을 만드는 모든 방벙의 수를 한 줄에 하나씩 출력
 * 
 * [생각]
 * dp를 활용할 것인데, dp에 저장하는 것은 방법의 수
 * 크기는 금액 M 
 * 
 * 각 동전의 종류에 대해 해당 금액을 만들 수 있는 것을 누적해나가기?
 */
	
	// 테스트 케이스 T
	static int T;
	// 동전 종류의 수 N
	static int N;
	// 동전들 coins
	static int[] coins;
	// 만들어야하는 금액 M
	static int M;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스 T를 입력
		T = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= T; t++) {
			
			// 동전 종류의 수 N을 입력
			N = Integer.parseInt(br.readLine().trim());
			
			// 동전들을 입력받음
			coins = new int[N];
			st = new StringTokenizer(br.readLine().trim());
			for (int i = 0; i < N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			
			// 만들어야하는 금액 M을 입력받음
			M = Integer.parseInt(br.readLine().trim());
			
			// dp[i] = i라는 금액을 만드는 방법의 수
			int[] dp = new int[M+1];
			// 초기값 : 만약에 i가 본인이라면 i-coin은 0이고, 이것은 본인 하나로 i라는 금액을 만들 수 있으니 1가지를 더하는 것
			dp[0] = 1;
			
			// 각 동전에 대해
			for (int coin : coins) {
				
				// 해당 동전을 이용해서 i라는 금액을 만들 수 있는 개수 
				for (int i = coin; i <= M; i++) {
					
					// i라는 금액을 만들기 위한 방법의 수는
					// i의 금액을 만들어온 방법의 수 + 해당 금액에서 해당 동전을 하나뺀 방법의 수
					dp[i] = dp[i] + dp[i-coin];
				}
			}
			
			// dp[M] = M이라는 금액을 만들기 위한 모든 경우의 수 
			sb.append(dp[M]).append('\n');
		}
		
		System.out.println(sb);
	}
}
