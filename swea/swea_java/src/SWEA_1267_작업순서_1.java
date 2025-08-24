import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1267_작업순서_위상정렬 {

	static class Node {
		int to;
		Node next;
		
		Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
	}
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	// 정점의 개수
	static int V;
	// 간선의 개수
	static int E;
	static Node[] adjList;
	static int[] inDegree;
	
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = 10;
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			st = new StringTokenizer(br.readLine().trim());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			adjList = new Node[V+1];
			inDegree = new int[V+1];
			
			st = new StringTokenizer(br.readLine().trim());
			for (int count = 0; count < E; count++) {	
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adjList[from] = new Node(to, adjList[from]);
				inDegree[to]++;
			}
			
			ArrayList<Integer> orderList = topologySort();
			
			// 출력
			sb.append('#').append(test_case).append(' ');
	        for (int student : orderList) {
	            sb.append(student).append(' ');
	        }
	        sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
	public static ArrayList<Integer> topologySort() {
		ArrayList<Integer> orderList = new ArrayList<>();
		Queue<Integer> q = new ArrayDeque<>();
		
		// 차수가 0인 것 담기
		for (int index = 1; index <= V; index++) {
			if (inDegree[index] == 0) {
				q.add(index);
			}
		}
		
		while (!q.isEmpty()) {
			int current = q.poll();
			orderList.add(current);
			
			for (Node node = adjList[current]; node != null; node = node.next) {
				// 앞에 있는 아이 빠졌으니 연결되어있는 놈 차수 1감소
				inDegree[node.to]--;
				// 차수가 0이면 큐에 담기
				if (inDegree[node.to] == 0) {
					q.add(node.to);
				}
			}
		}
		
		
		return orderList;
	}
}
