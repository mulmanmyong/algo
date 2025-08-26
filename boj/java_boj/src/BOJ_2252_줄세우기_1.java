import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// 위상 정렬 풀이 
public class BOJ_2252_줄세우기_1 {

	// 인접 리스트를 위한 노드 클래스 생성
	static class Node {
		int to; // 연결된 정점
		Node next; // 다음 노드 
		
		Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
	}
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N;
	static int M;
	static Node[] adjList;
	static int[] inDegree;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 학생들의 번호가 1번부터 N번까지임 
		adjList = new Node[N+1];
		inDegree = new int[N+1];
		
		for (int count = 0; count < M; count++) {
			st = new StringTokenizer(br.readLine().trim());
			
			// A가 B앞에 서야함. A 다음이 B임
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			// 연결 리스트 구성
			adjList[A] = new Node(B, adjList[A]);
			// B 정점의 차수 증가
			inDegree[B]++;
		}
		
		ArrayList<Integer> orderList = topologySort();
		
		// 출력
        for (int student : orderList) {
            sb.append(student).append(' ');
        }
        System.out.println(sb);
	
	}
	
	public static ArrayList<Integer> topologySort() {
		ArrayList<Integer> orderList = new ArrayList<>();
		// 진입 차수가 0인 정점을 담을 거임 
		Queue<Integer> q = new ArrayDeque<>();
		
		// 1번부터 N번까지 돌며 진입차수 0인 애들 큐에 추가
		for (int index = 1; index <= N; index++) {
			if (inDegree[index] == 0) {
				q.add(index);
			}
		}
		
		while (!q.isEmpty()) {
			int current = q.poll();
			orderList.add(current);
			
			// 현재 정점에서 출발하는 모든 간선 확인
			for (Node node = adjList[current]; node != null; node = node.next) {
				// 현재 정점은 이미 담겼으니, 연결된 노드의 진입차수 1감소
				inDegree[node.to]--;
				// 이 때 진입차수가 0이 되면 큐에 담기
				if (inDegree[node.to] == 0) {
					q.add(node.to);
				}
			}
		}
		return orderList;
	}
}
