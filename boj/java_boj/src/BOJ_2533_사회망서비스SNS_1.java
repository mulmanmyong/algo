import java.io.*;
import java.util.*;

public class BOJ_2533_사회망서비스SNS_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [설명]
 * 삼성이가 살고 있는 마을에는 N개의 집이 있음
 * 전기 공급을 위해 집들은 전선으로 연결되어있음
 * 
 * 루프가 없는 트리구조이고, 각 집은 한가지 경로로만 연결되어 있음
 * 
 * 전기 공급을 위해 배전함을 설치해야함. 배전함은 마을의 일부 집에만 설치 가능
 * 배전함이 설치된 집은 직접 전력 공급 받게되고, 배전함이 설치된 집과 연결된 이웃집들도 전기 공급받을 수 있음
 * 
 * [입력]
 * 첫째 줄에 집의 수 N(2~1000000) 주어짐, 집은 1번부터 번호가 부여됨
 * 다음 둘째 줄부터 N-1줄에는 연결된 두 집 u와 v가 빈칸을 사이에 두고 주어짐
 * 이 연결은 항상 하나의 트리를 이룸
 * 
 * [출력]
 * 모든 집에 전력 공급할 수 있도록 하는 배전함의 최소 개수 
 * 
 * [방법]
 * N이 백만으로 꽤 많은 편에 속한다. 그래서 그냥 완탐을 해버리면 무조건 터지게 된다.
 * 하지만 최적화는 이후의 이야기 일단은 풀어보자
 * 
 * 들어오는 것과 나가는 것의 개수를 파악하여 몇개씩 연결되는 지 파악을 해야하고
 * 이것을 플로이드 워셜을 통해서 해결할 수 있는 것 같고
 * 그것을 위해 인접행렬로 만드는 것이 좋을 것이고
 * 근데 플로이드 워셜을 하면 무조건 터질 것이고
 * 그래서 이를 dp를 이용하여 최적화를 해보자?
 * 1 << N을 하면 무조건 터질 거 같은데 백만이어가지고.......
 * 
 * 그냥 다 필요없이 비트로 완탐을 하고,,,
 * 앞에서 N==count임을 만족하는 것이 나오면 비트의 개수가 최저인것은 아니네 break걸면 안됨
 * 2^백만은 좀 큰 거 같은데.. 괜찮을란가 ..
 * 조기에 break는 걸면 안되고 최적화 방안만 생각을 해보자 
 * 
 * ---------------------------------------------------------------------------
 * 당신은 이 위의 이야기를 망각합니다.
 * 
 * 트리 구조에서는 DP를 이용하면 O(N) 시간 안에 해결 가능
 * 각 노드별로 배전함 설치 여부에 따른 최소 개수를 저장
 * dp[node][0] = node에 배전함 없음, 최소 배전함 개수
 * dp[node][1] = node에 배전함 있음, 최소 배전함 개수
 */
	
	static int N;
	static ArrayList<Integer>[] adjList;
	static int[][] dp;
	static boolean[] visited;
	
	public static void dfs(int node) {
		visited[node] = true;
			
		// node에 배전함 설치하면 1개 + 자식들 최소 선택
		dp[node][1] = 1; // node에 설치
		dp[node][0] = 0; // node에 설치하지 않음
			
		// 해당 노드랑 연결되어 있는 놈들 보기 
		for (int next : adjList[node]) {
			if (visited[next]) continue;
			dfs(next);
				
			// node가 배전함이면 자식들은 설치 여부 상관 없음
			dp[node][1] += Math.min(dp[next][0], dp[next][1]);
				
			// node가 배전함이 아니면 자식은 반드시 배전함 있어야 함
			dp[node][0] += dp[next][1];
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 집의 수 입력
		N = Integer.parseInt(br.readLine().trim());
		
		// 인접리스트 초기화 
		adjList = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			adjList[u].add(v);
			adjList[v].add(u);
		}
		
		// dp 배열 생성
		// 1~N이랑, 루트에 배전함을 설치한 경우 안한 경우 0과 1
		dp = new int[N+1][2];
		// 방문 배열 초기화 
		visited = new boolean[N+1];
		
		// 1번 루트부터 시작하기 
		dfs(1);
		
		// 루트에 배전함을 설치하거나 안할 경우 중 최소 선택
		sb.append(Math.min(dp[1][0], dp[1][1]));
		System.out.println(sb);
	}
	
}