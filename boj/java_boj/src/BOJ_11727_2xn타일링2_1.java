import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11727_2xn타일링2_1 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int MOD = 10007;
	static int n;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine().trim());
		// 점화식을 세워보니
		// 1일 땐 1가지 2일땐 3가지, 3일땐 5가지, 4일땐 11가지, 5일 땐 21가지가 나옴
		// 이를 봤을 대 dp[i] = dp[i-1] + 2*dp[i-2]임을 확인할 수 있음
		int[] dp = new int[n+1];
		
		// 초기값
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= n; i++) {
			dp[i] = dp[i-1] + 2*dp[i-2];
			if (dp[i] > MOD) {
				dp[i] %= MOD;
			}
		}
		
		sb.append(dp[n]);
		System.out.println(sb);
	}
}
