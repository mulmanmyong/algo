import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//프림으로 풀기, 인접행렬+우선순위큐
public class SWEA_1251_하나로_2 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	// 섬의 개수 N
	static int N;
	// 좌표를 담을 배열
	static List<int[]>	position;
	// 세율 E
	static double E;
	
	// 프림 알고리즘 구현을 위한 자료구조
	// 인접행렬
	static double[][] adjMatrix;
	// 방문처리 배열
	static boolean[] visited;
	// 정점과 간선의 가중치를 저장할 우선순위 큐
	static PriorityQueue<double[]> pq;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스를 입력 받음
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			N = Integer.parseInt(br.readLine().trim());
			
			position = new ArrayList<>();
			// x좌표 입력하기 
			StringTokenizer stX = new StringTokenizer(br.readLine().trim());
			// y좌표 입력하기 
			StringTokenizer stY = new StringTokenizer(br.readLine().trim());
			for (int index = 0; index < N; index++) {
				int x = Integer.parseInt(stX.nextToken());
				int y = Integer.parseInt(stY.nextToken());
				position.add(new int[] {x, y});
			}
			
			// 세율 입력받기
			E = Double.parseDouble(br.readLine().trim());
			
			// 좌표간의 거리를 이용해서 정점과 간선의 가중치 저장하기 - 인접행렬 이용 
			adjMatrix = new double[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					// x좌표의 간격
					long dx = (long) position.get(i)[0] - position.get(j)[0];
					// y좌표의 간격
					long dy = (long) position.get(i)[1] - position.get(j)[1];
					// 좌표간의 길이 L^2을 하기 때문에 sqrt하지 않음 
					// 각 길이에서의 비용이기 때문에 비용을 저장
					double cost = E*(dx * dx + dy * dy);
					adjMatrix[i][j] = adjMatrix[j][i] = cost;
				}
			}
			
			/* 프림 알고리즘을 이용해서 최소 비용 찾기 */
			visited = new boolean[N];
			// 간선의 가중치를 기준으로 오름차순 정렬
			pq = new PriorityQueue<>((a, b) -> Double.compare(a[1], b[1]));
			
			double minCost = 0;
			int count = 0;
			
			// 시작은 아무거나 잡아서 최소신장트리에 추가 (0번 정점을 추가할 것임)
			pq.add(new double[] {0, 0});
			
			// 이 정점과 연결된 정점이 MST에 포함되어 있지 않은 것들은
			// 연결하는 간선을 우선순위 큐에 추가 
			// 우선순위 큐에서는 비용이 작은 순으로 입력되어 있기 때문에
			// 제일 앞에 있는 간선을 임의로 꺼내기 
			
			// 꺼낸 간선으로 연결된 정점과 간선을 MST에 추가하고, 연결된 정점을 기준으로 잡도록 변경
			
			// 이를 반복, => 이 때 이미 MST에 포함된 간선이면 우선순위 큐에서 꺼내고 아무 동작을 안함 
			
			// 즉, 우선순위 큐에 MST에 포함되지 않는 간선과 정점을 넣고, 비용이 작은 순으로 계속 꺼내며
			// MST에 포함되어 있으면 제거만 하고 아무 동작안하고 패스
			// MST에 포함 안되어 있으면 제거 후 MST에 추가하고, 기준 정점 변경하고 반복동작 
			
			while (!pq.isEmpty()) {
				// 제일 앞에 있는 간선을 임의로 꺼내기
				// 현재 목표로 하고 있는 정점
				int currentTarget = (int) pq.peek()[0];
				// 현재의 길이 (가중치)
				double currentCost = pq.peek()[1];
				pq.poll(); // 꺼내기
				
				//  이미 MST에 포함된 간선이면 우선순위 큐에서 꺼내고 아무 동작을 안함 
				if (visited[currentTarget])	continue;
				
				// 포함이 안되어있으면 MST에 추가
				visited[currentTarget] = true;
				// 전체 비용에 추가
				minCost += currentCost;
				// 연결된 정점 1 증가
				count++;
				
				// 모든 정점이 다 연결되었다면?
				if (count == N)	break; // 종료
				
				// 아직 더 연결되어야 하면
				// 현재 정점을 기준으로 MST에 추가 안된 정점들 우선순위 큐에 추가
				for (int nextIndex = 0; nextIndex < N; nextIndex++) {
					// 이미 연결된 정점이면 패스
					if (visited[nextIndex])	continue;
					// 연결되어있지 않다면 우선순위 큐에 추가
					pq.add(new double[] {nextIndex, adjMatrix[currentTarget][nextIndex]});
				}
			}
			
			sb.append('#').append(test_case).append(' ').append(Math.round(minCost)).append('\n');
		}
		System.out.println(sb);
	}
	
}
