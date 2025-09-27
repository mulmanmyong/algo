import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1463_1로만들기_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

/*
 * 시간 제한이 0.15초 이기 때문에 DP로 풀어야할 것
 * 
 * 정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 
 * 	1. X가 3으로 나누어 떨어지면 3으로 나눔
 * 	2. X가 2로 나누어 떨어지면 2로 나눔
 * 	3. 1을 뺌
 * 
 * 연산 3개 적절히 사용해서 1을 만들기
 * 
 *  DP에 해당 연산까지 걸린 횟수를 저장하기?
 *  
 */
	
	static int N;
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine().trim());
		dp = new int[N+1];
		
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		// 초기 값
		dp[1] = 0;
		
		// dp에는 연산횟수를 저장할 것임.
		// 즉, 연산을 하는 횟수의 최소값들을 저장할 것이다
		// 상향식 dp
		for (int x = 2; x <= N; x++) {
			// x가 3으로 나누어 떨어지는가
			if (x % 3 == 0)	{
				dp[x] = Math.min(dp[x], dp[x/3]+1);
			}
			if (x % 2 == 0) {
				dp[x] = Math.min(dp[x], dp[x/2]+1);
			}
			dp[x] = Math.min(dp[x], dp[x-1]+1);
		}
		
		sb.append(dp[N]);
		System.out.println(sb);
	}
}
