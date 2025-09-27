import java.io.*;
import java.util.*;

public class SWEA_3282_01Knapsack_1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	/*
	 * [문제 설명]
	 * 1~N까지의 번호가 부여된 N개의 물건과 최대 K 부피만큼을 넣을 수 있는 가방이 있음
	 * 1번 물건부터 N번 물건 각각은 부피 Vi와 가치 Ci를 가지고 있음 
	 * 
	 * 민수는 물건들 중 몇 개를 선택하여 가방에 넣어서 그 가치의 합을 최대화 하려 함
	 * 선택한 물건들의 부피 합이 K이하여야 함
	 * 
	 * 가방에 담을 수 있는 물건들의 최대 가치 구하기
	 * 
	 * [입력]
	 * 첫 번째 줄에 테스트 케이스의 수 T가 주어짐
	 * 	각 테스트 케이스의 첫째 줄에 물건의 개수와 가방의 부피인 N K가 주어짐
	 * 	N개의 줄에 걸쳐서 i번 물건의 정보를 나타내는 부피 Vi와 가치 Ci가 주어짐
	 * 
	 * [출력]
	 * 가방에 담을 수 있는 최대 가치 출력
	 * 
	 * [아이디어]
	 * 문제이름 그대로 배낭문제(0/1 Knapsack)
	 */
	
	// 물건의 개수 N, 가방의 부피 K
	static int N, K;
	// 물건들의 부피
	static int[] V;
	// 물건들의 가치
	static int[] C;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			
			// 물건의 개수와 가방의 부피를 입력받음
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			// 물건의 부피와 가치를 입력받음 (1번부터 시작)
			V = new int[N+1];
			C = new int[N+1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				V[i] = Integer.parseInt(st.nextToken());
				C[i] = Integer.parseInt(st.nextToken());
			}
			
			// dp[i][k] = i번째 물건까지 고려했을 때, 가방 용량 k일 때의 최대 가치
			int[][] dp = new int[N+1][K+1];
			
			// i번째 물건까지 고려
			for (int i = 1; i <= N; i++) {
				// 가방의 용량을 1부터 K까지 늘려가며 비교
				for (int k = 1; k <= K; k++) {
					
					// 현재 물건 i를 가방에 넣을 수 없는 경우: 이전 상태 그대로 유지
					if (V[i] > k) {
						dp[i][k] = dp[i-1][k];
					}
					// 현재 물건 i를 가방에 넣을 수 있는 경우
					else {
						// 1. 이전 상태에서 현재 물건을 넣지 않은 경우: dp[i-1][k]
						// 2. 현재 물건을 넣는 경우: 현재 가치 + (남은 무게만큼의 최대 가치)
						// 두 경우 중 최대값 선택
						dp[i][k] = Math.max(dp[i-1][k], C[i] + dp[i-1][k - V[i]]);
					}
				}
			}
			
			// dp[N][K]에는 최대 가치가 저장되어 있음
			sb.append('#').append(test_case).append(' ').append(dp[N][K]).append('\n');
		}
		System.out.println(sb);
	}
}
