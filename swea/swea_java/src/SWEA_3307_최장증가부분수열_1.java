import java.io.*;
import java.util.*;

public class SWEA_3307_최장증가부분수열_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
/*
 * 최장증가부분수열의 길이를 찾는 문제
 * dp 풀이 - N이 최대 1000이라 
 * dp로 보면 O(N^2)이기 때문에 1000000으로 충분히 dp로 풀이 가능 
 */
	
	// 수열의 길이 N
	static int N;
	// 수열
	static int[] sequence;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			
			// 수열의 길이 N을 입력
			N = Integer.parseInt(br.readLine().trim());
			
			// 수열 N개를 입력받음
			sequence = new int[N];
			st = new StringTokenizer(br.readLine().trim());
			for (int i = 0; i < N; i++) {
				sequence[i] = Integer.parseInt(st.nextToken());
			}
			
			// 증가수열의 길이 저장할 dp, 패딩 필요 읎다 
			int[] dp = new int[N];
			// 최대 길이 저장할 변수
			int maxLength = 0;
			
			// i번째 수를 고려
			for (int i = 0; i < N; i++) {
				// 초기에는 자신 혼자만 이기 때문에 길이가 1이다. 1로 초기화
				dp[i] = 1;
				
				// j번째 수가 i번째 수보다 작은지 보기
				// dp에는 지금까지의 증가수열길이가 저장되어있기 때문에 
				// j번쨰 수가 i번째 수보다 작으면 i까지 포함하여 +1의 길이가 됨
				for (int j = 0; j < i; j++) {
					// j번째 수가 i번째 수보다 작은지요?
					if (sequence[j] < sequence[i]) {
						// 작으면 j뒤에 i를 붙일 수 있음
						// 그래서 이미 저장되어있는 길이가 긴지
						// 아니면 j의 수 뒤에 이어 붙인 길이가 긴지 판단
						dp[i] = Math.max(dp[i], dp[j] + 1);
					}
				}
				
				// 최대 길이 갱신
				maxLength = Math.max(maxLength, dp[i]);
			}
			
			sb.append('#').append(test_case).append(' ').append(maxLength).append('\n');
		}
		System.out.println(sb);
	}
}
