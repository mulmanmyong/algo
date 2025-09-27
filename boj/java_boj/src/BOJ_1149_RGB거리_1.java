import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149_RGB거리_1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * RGB거리에는 집이 N개 있음. 거리는 선분으로 나타낼 수 있음 
 * 1~N번 집이 순서대로 있음
 * 
 * 집은 빨 초 파 중 하나의 색으로 칠함. 각 색을 칠하는 비용이 주어짐
 * 	1. 1번 집의 색은 2번 집의 색과 같지 않아야 함
 * 	2. N번 집의 색은 N-1번집의 색과 같지 않아야 함
 * 	3. i(2<= i <= N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 함
 */
	
	static int N;
	static int[][] cost;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine().trim());
		cost = new int[N][3];
		for (int home = 0; home < N; home++) {
			st = new StringTokenizer(br.readLine().trim());
			cost[home][0] = Integer.parseInt(st.nextToken());
			cost[home][1] = Integer.parseInt(st.nextToken());
			cost[home][2] = Integer.parseInt(st.nextToken());
		}
		
		// 0 1 2 빨 초 파 
		// 전체적으로 이번에 선택된 색이랑 이전 색이랑 달라야함
		// 각 현재의 선택된 색이랑 다른 이전색의 경우에서 cost가 작은 것을 더하는 형식으로 진행
		// 상향식 dp 
		for (int home = 1; home < N; home++) {
			cost[home][0] += Math.min(cost[home-1][1], cost[home-1][2]);
			cost[home][1] += Math.min(cost[home-1][0], cost[home-1][2]);
			cost[home][2] += Math.min(cost[home-1][0], cost[home-1][1]);
		}
		
		// 끝까지 다 칠하면 마지막 색의 3가지 경우 중에서 가장 작은 것을 결과로 출력
		int minCost = Math.min(cost[N-1][0], Math.min(cost[N-1][1], cost[N-1][2]));
		sb.append(minCost);
		System.out.println(sb);
	}
}
