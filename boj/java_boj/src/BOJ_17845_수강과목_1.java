import java.io.*;
import java.util.*;

public class BOJ_17845_수강과목_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [문제 설명]
 * 서윤이는 어떤 과목을 들을 지 고민중
 * 학점을 잘 받을 수 있으면서도 중요한 과목을 듣고 싶음
 * 그래서 모든 과목의 중요도와 일정 이상의 학점을 받기 위해 필요한 공부시간을 다 적음
 * 
 * 처음에는 모든 과목을 들으려 했느나 자신의 공부 시간에 한계가 있음을 깨달음.
 * 그래서 시간의 한계 초과하지 않으면서 과목의 중요도 합이 최대가 되로도록 몇개 선택하여 수강
 * 
 * 죄대가 되는 중요도를 출력
 * 
 * [입력]
 * 첫 줄에 최대 공부시간 N(1~10000), 과목수 K(1~1000) 주어짐
 * 이후 k개의 줄에 거쳐 중요도I와 필요 공부시간T 주어짐
 * 
 * [출력]
 * 얻을 수 있는 최대 중요도 출력
 * 
 * [아이디어]
 * 그저 배낭문제임
 * dp를[K][N] 으로 두고, 최대 중요도를 저장하며 갱신하는 방식으로 진행
 * 최대 중요도 int 넘지 않음
 * 
 */
	
	// 최대 공부시간 N, 과목수 K
	static int N, K;
	// 중요도 I
	static int[] I;
	// 필요 공부시간 T
	static int[] T;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 최대 공부시간 N, 과목수 K 입력 받음
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 중요도와 필요 공부시간 입력받음
		I = new int[K+1];
		T = new int[K+1];
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine().trim());
			I[i] = Integer.parseInt(st.nextToken());
			T[i] = Integer.parseInt(st.nextToken());
		}
		
		// dp[i][t] : 최대 시간 한계 내에서 최대 중요도를 저장할 것임
		int[][] dp = new int[K+1][N+1];
		
		// i번째 과목 고려 (K까지)
		for (int i = 1; i <= K; i++) {
			// 제한시간 t 고려 (N까지)
			for (int t = 1; t <= N; t++) {
				
				// 현재 공부 제한 시간을 넘는 지 아닌 지 판단
				
				// 현재 필요 공부 시간이 제한 시간을 넘으면 공부할 수 없음
				if (T[i] > t) {
					// 이전 값 유지
					dp[i][t] = dp[i-1][t];
				}
				// 현재의 과목이 제한시간을 넘진 않으니 공부할 수 있긴 함
				else {
					// 1. 해당 과목을 공부안할 경우 = 이전 값 유지
					// 2. 현재 과목을 공부 할 경우 = 현재 중요도 + 현재 공부시간을 제외했을 때의 이전까지의 중요도
					dp[i][t] = Math.max(dp[i-1][t], I[i] + dp[i-1][t-T[i]]);
				}
			}
		}

		// 최대 중요도 = dp[K][N]
		sb.append(dp[K][N]);
		System.out.println(sb);
	}
}
