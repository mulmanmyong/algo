import java.io.*;
import java.util.*;

public class BOJ_12865_평범한배낭_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [문제 설명]
 * 이 문제는 아주 평범한 배낭 문제임!
 * 
 * 여행 짐을 쌀 것이다
 * 여행에 필요하다고 생각하는 N개의 물건이 있음. 각 물건은 무게 W와 가치 V를 가짐
 * 해당 물건을 배낭에 넣어서 가면 준서가 V만큼 즐길 수 있음
 * 
 * 근데 준서는 미필이라 행군같은 거 안해봄.. 
 * 그래서 최대 K만큼의 무게만을 넣을 수 있는 배낭만 들고 다닐 수 있음.
 * 
 * 최대한 즐거운 여행을 하기 위해 배낭에 넣을 수 있는 물건들의 가치의 최댓값을 알려주자
 * 
 * [입력]
 * 첫 줄에 물품의 수 N(1~100), 버틸 수 있는 무게 K(1~100000) 주어짐
 * N개의 줄에 거쳐 각 물건의 무게 W와 해당 물건의 가치 V 주어짐
 * 
 * [출력]
 * 물건들의 최대 가치 출력
 * 
 * [아이디어]
 * 그냥 배낭 문제임
 * 최대 수도 int 범위 안벗어남 
 * 
 */
	
	// 물품의 수 N, 버틸 수 있는 무게 K
	static int N, K;
	// 물건들의 무게 
	static int[] W;
	// 물건들의 가치
	static int[] V;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 물품의 수 N, 버틸 수 있는 무게 K 입력
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 물건들의 무게와 가치를 입력받음
		W = new int[N+1];
		V = new int[N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}
		
		// dp[i][k] = 해당 물품을 담든 안담든 담았을 때의 최대 가치를 의미
		int[][] dp = new int[N+1][K+1];
		
		// i번째 물건 고려 (N까지)
		for (int i = 1; i <= N; i++) {
			// 제한 무게 k일 때 담을 수 있는 지 고려 (K까지)
			for (int k = 1; k <= K; k++) {
				
				//  제한무게가 k 일때 해당 물품을 담을 수 있는 지 없는 지
				
				// 제한 무게가 넘어서 담을 수 가 없을 경우
				if (W[i] > k) {
					// 이전 상태 그대로 유지
					dp[i][k] = dp[i-1][k];
				}
				// 제한 무게 안이라서 담을 수 있음
				else {
					// 1. 이전 상태에서 현재 물건을 담지 않는 경우
					// 2. 물건을 담는 경우 : 현재물건의 가치 + 현재 물건의 뺏을 때 이전까지의 가치 
					dp[i][k] = Math.max(dp[i-1][k], V[i] + dp[i-1][k-W[i]]);
				}
			}
		}
		
		// 최대 가치는 dp[N][K]
		sb.append(dp[N][K]);
		System.out.println(sb);
	}
}
