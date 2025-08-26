import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS 풀이 
public class SWEA_1238_Contact_1 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int dataSize;
	static int start;
	static ArrayList<Integer>[] list;
	static boolean[] visited;
	static int maxContact;
	static int maxContactNumber;
	
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 10개의 테스트 케이스가 주어짐 
		int testCase = 10;
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			st = new StringTokenizer(br.readLine().trim());
			dataSize = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			
			// 부여될 수 있는 번호는 1이상 100이하이기 때문에
			// 정적 선언
			list = new ArrayList[101];
			for (int index = 0; index < 101; index++) {
				list[index] = new ArrayList<>();
			}
			
			// 연결되어있는 번호 입력받기
			st = new StringTokenizer(br.readLine().trim());
			for (int index = 0; index < dataSize/2; index++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				// 이미 입력된 경우는 패스
				if (list[from].contains(to))	continue;
				list[from].add(to);
			}
			
			// 시작지점을 기점으로, bfs를 통해 최대 연락할 수 있는 인원 도출하기
			visited = new boolean[101];
			maxContact = 0;
			maxContactNumber = 0;
			bfs(start);
			
			
			sb.append('#').append(test_case).append(' ').append(maxContactNumber).append('\n');
		}
		System.out.println(sb);
	}
	
	public static void bfs(int node) {
		// queue를 현재 노드와 연락이 이어지는 횟수를 입력하여 진행 
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {node, 0});
		visited[node] = true;
		
		while (!q.isEmpty()) {
			int currentNode = q.peek()[0];
			int currentCount = q.peek()[1];
			q.poll();
			
			// 제일 나중에 연락 받은 사람 갱신
			if (currentCount > maxContact) {
				maxContact = currentCount;
				maxContactNumber = currentNode;
			}
			// 제일 늦게 연락받은 것으로 동일하다면, 더 큰 숫자로 갱신
			else if (currentCount == maxContact){
				maxContactNumber = Math.max(currentNode, maxContactNumber);
			}
			
			// 현재 노드와 연결된 노드 확인 
			for (int nextNode : list[currentNode]) {
				// 방문했던 노드면 패스
				if (visited[nextNode]) continue;
				// 그렇지 않으면
				// 방문 처리후 개수 늘려서 다음으로
				visited[nextNode] = true;
				q.add(new int[] {nextNode, currentCount+1});
			}
		}
	}
}
