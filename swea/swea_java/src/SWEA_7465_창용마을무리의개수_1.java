import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 인접리스트, dfs 풀이  
public class SWEA_7465_창용마을무리의개수_1 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	// 사람의 수 N, 관계의 수 M
	static int N, M;
	// 인접 리스트로 서로 알고있는 관계를 표시
	static List<Integer>[] adjList;
	// 확인했는지 체크
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			st = new StringTokenizer(br.readLine().trim());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// 인접리스트 생성
			adjList = new ArrayList[N+1];
			for (int i = 0; i < N+1; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			// 관계 설정
			for (int cmd = 0; cmd < M; cmd++) {
				st = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				adjList[a].add(b);
				adjList[b].add(a);
			}
			
			// 그룹의 수 count
			visited = new boolean[N+1];
			int count=0;
			for (int i = 1; i <= N; i++) {
				if (visited[i]) continue;
				count++;
				dfs(i);
			}
			
			sb.append('#').append(test_case).append(' ').append(count).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void dfs(int current) {
		// 현재 숫자 방문처리 
        visited[current] = true;

        // 연결되어있는 것 탐색
        for (int next : adjList[current]) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }
}
