import java.io.*;
import java.util.*;

public class BOJ_7579_앱_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [문제설명]
 * '실행 중'인 앱은 하나뿐이지만 보이지 않는 상태로 많은 앱이 활성화되어있음
 * 메모리에 남겨둬서 빠른 실행을 위함
 * 
 * 근데 스마트폰의 메모리는 제한적, 그랳서 모든 앱을 활성화된 채로 남겨두면 메모리 부족이 옴
 * 새로운 앱을 실행시키기 위해 필요한 메모리가 부족해지면 활성화된 앱들 중 몇개를 선택하여 삭제 -> 이것이 비활성화
 * 
 * 비활성화 문제를 스마트하게 해결해보자 
 * 현재 N개의 앱이 활성화 되어있음(A1...AN) 
 * 이들 앱은 각각 mi 바이트만큼의 메모리 사용 중 
 * Ai를 비활성화한 후 다시 실행할 때 추가적으로 들어가는 비용을 ci라고 함
 * 이러한 상황에서 사용자가 새로운 앱 B를 실행하고자 함, 추가로 M바이크 메모리 필요함
 *  -> 실행을 위해 현재 실행 중인 앱 중 몇개를 비활성화하여 m바이트 이상의 메모리 추가 확보 하기
 *  비활성화했을 경우의 비용의 합을 최소화하여 필요한 메모리 M바이트를 확보하는 방법을 찾기
 *  
 *  [입력]
 *  입력은 3줄
 *  첫 줄 N과 M이 주어짐 (활성화되어있는 앱의 개수, 필요한 메모리)
 *  둘째줄 현재 활성화 되어 있는 앱이 사용 중인 메모리의 바이트 N개 (ㅡ)
 *  셋째줄 비활성화했을 경우의 비용 N개 (c)
 *  
 *  [출력]
 *  M바이트를 확보하기 위한 앱 비활성화의 최소 비용 출력
 *  
 *  [아이디어]
 *  dp[N][C] -> 해당 코스트로 최대 확보할 수 있는 메모리 구하기
 *  그러면 해당 비용으로 확보할 수 있는 최대 메모리를 저장하고, 
 *  이는 이후에 0~최대 코스트까지 눌려가며 M이상 확보된 첫번째에 break하기 -> 그것이 최소 비용
 */
	
	// 활성화되어있는 앱의 개수 N, 필요한 메모리 M
	static int N, M;
	// 앱이 사용 중인 메모리 m
	static int[] m;
	// 비활성화했을 경우의 비용 c
	static int[] c;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 앱이 사용 중인 메모리
		m = new int[N+1];
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 1; i <= N; i++) {
			m[i] = Integer.parseInt(st.nextToken());
		}
		
		// 최대 코스트
		int totalCost = 0;
		// 비활성화했을 경우의 비용
		c = new int[N+1];
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 1; i <= N; i++) {
			c[i] = Integer.parseInt(st.nextToken());
			totalCost += c[i];
		}
		
		// dp[i][j] = 최대 확보가능 메모리를 저장하기
		int[][] dp = new int[N+1][totalCost+1];
		
		// i번째 앱을 확인
		for (int i = 1; i <= N; i++) {
			// 현재 제한 코스트가 j일 때
			for (int j = 0; j <= totalCost; j++) {
				
				// i의 비용이 j보다 큰가?
				if (c[i] > j) {
					// 그렇다면 해당 앱을 비활성화할 수 없음 = 이전값 유지
					dp[i][j] = dp[i-1][j];
				}
				// 크면 해당 비용을 사용해버려
				else {
					// 1. 해당 앱을 비활성화하지 않늕 경우 = 이전 값 유지
					// 2. 해당 앱을 비활성화하는 경우 = 현재 앱을 비활성화했을 때의 메모리 + (제한비용 - 현재의비용)의 이전까지의 값
					dp[i][j] = Math.max(dp[i-1][j], m[i] + dp[i-1][j-c[i]]);
				}
				
			}
		}
		
		// 제한 코스트를 돌며, 메모리 확보가 M 이상이 되면 break
		for (int cost = 0; cost <= totalCost; cost++) {
			if (dp[N][cost] >= M) {
				sb.append(cost);
				break;
			}
		}

		System.out.println(sb);
	}
}
