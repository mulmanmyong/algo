import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//프림으로 풀기, 인접리스트+우선순위큐
public class SWEA_3124_최소스패닝트리_2 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	// 정점의 개수 V, 간선의 개수 E
	static int V, E;
	// 인접행렬와 우선순위 큐로 구현
	// 인접행렬 런타임에러 ;;;;;;
//	static int[][] adjMatrix;
	// 인접 리스트와 우선순위 큐로 구현
	static ArrayList<Node>[] adjList;
	static PriorityQueue<int[]> pq;
	// 방문처리 배열
	static boolean[] visited;
	
	static class Node {
		int to;
		int weight;
		
		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			st = new StringTokenizer(br.readLine().trim());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			// 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어짐
//			adjMatrix = new int[V+1][V+1];
			adjList = new ArrayList[V+1];
			for (int index = 1; index <= V; index++) {
				adjList[index] = new ArrayList<>();
			}
			for (int index = 0; index < E; index++) {
				st = new StringTokenizer(br.readLine().trim());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				int C = Integer.parseInt(st.nextToken());
				
				// 무향 그래프이고, A와 B가 연결결되어있고, C가 가중치임
//				adjMatrix[A][B] = adjMatrix[B][A] = C;
				// 인접리스트로 구현 중
				adjList[A].add(new Node(B, C));
				adjList[B].add(new Node(A, C));
			}
			
			// 프림 알고리즘을 위한 방문처리 배열과 우선순위 큐 초기화
			visited = new boolean[V+1];
			// 정점 번호, 가중치 
			pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
			
			// 초기 값은 임의로 1번 시작, 0번은 없는 놈이기 때문
			visited[0] = true; // 없는 놈
			pq.add(new int[] {1, 0});
			
			// 반복
			long minCost = 0;
			int count = 0;
			
			while (!pq.isEmpty()) {
				// 제일 앞에 있는 정점 꺼내기 
				int currentTarget = pq.peek()[0];
				int currentCost = pq.peek()[1];
				pq.poll();
				
				// 이미 MST에 포함되어 있으면 패스
				if (visited[currentTarget])	continue;
				
				// 그렇지 않으면 MST에 담기
				visited[currentTarget] = true;
				// 연결했으니 최소 비용에 더하기
				minCost += currentCost;
				// 연결했으니 연결한 개수 1개 증가
				count++;
				
				// 모든 정점 다 연결되었으면 종료
				if (count == V)	break;
				
				// 아직 덜 연결되어있다면 현재 정점과 연결되어있는 정점 확인하고,
				// 연결되어있는 정점이 MST에 없으면 우선순위 큐에 추가
				for (Node nextNode : adjList[currentTarget]) {
					// 다음 정점이 이미 MST에 있으면 패스
					if (visited[nextNode.to])	continue;
					
					// MST에 없으면 우선순위 큐에 추가
					pq.add(new int[] {nextNode.to, nextNode.weight});
				}
			}
			
			// 프림 알고리즘으로 MST 구하기 완료
			// 결과 담기
			sb.append('#').append(test_case).append(' ').append(minCost).append('\n');
		}
		System.out.println(sb);
	}
}
