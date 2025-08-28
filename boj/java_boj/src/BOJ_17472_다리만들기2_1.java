import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS와 PRIM 알고리즘을 이용해서 풀이 
public class BOJ_17472_다리만들기2_1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	// 세로 크기 N, 가로 크기 M
	static int N, M;
	// 지도
	static int[][] area;
	// 방문 처리 - BFS
	static boolean[][] visitedBFS;
	
	// 방향배열 : 상 하 좌 우
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaColumn = {0, 0, -1, 1};
	
	// MST를 위한 것들
	// 인접리스트
	static ArrayList<Node>[] adjList;
	// 방문 처리 - PRIM
	static boolean[] visitedPRIM;
	// 우선순위큐
	static PriorityQueue<Node>	pq;
	// 최소길이
	static int minimumTotalBridgeLength;
	
	static class Node implements Comparable<Node> {
		int to;
		int weight;
		
		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			// 우선순위 큐 오름차순으로 
			return this.weight - o.weight;
		}
	}
	
	// 섬에 번호를 매기는 BFS
	public static void bfs(int row, int column, int islandNum) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {row, column});
		area[row][column] = islandNum;
		visitedBFS[row][column] = true;
		
		while (!q.isEmpty()) {
			int currentRow = q.peek()[0];
			int currentColumn = q.peek()[1];
			q.poll();
			
			for (int direction = 0; direction < 4; direction++) {
				int newRow = currentRow + deltaRow[direction];
				int newColumn = currentColumn + deltaColumn[direction];
				
				// 범위를 벗어나면 패스
				if (newRow < 0 || newRow >= N || newColumn < 0 || newColumn >= M)	continue;
				// 이미 방문했거나 정복되지 않은 섬이면 패스
				if (visitedBFS[newRow][newColumn] || area[newRow][newColumn] != 1)	continue;
				
				// 방문처리
				visitedBFS[newRow][newColumn] = true;
				// 번호매기기
				area[newRow][newColumn] = islandNum;
				// queue에 추가 
				q.add(new int[] {newRow, newColumn});
			}
		}
	}
	
	// 다리 건설하기 
	public static void bridge(int row, int column, int startIsland) {
		
		// 4방향으로 탐색을 해야함
		// 그리고 1직선으로만 가야함
		for (int direction = 0; direction < 4; direction++) {
			int newRow = row;
			int newColumn = column;
			int bridgeLength = 0;
			
			// 다리 건설해보기
			while (true) {
				newRow += deltaRow[direction];
				newColumn += deltaColumn[direction];
				
				// 범위를 벗어나면 안도비입니다요
				if (newRow < 0 || newRow >= N || newColumn < 0 || newColumn >= M)	break;
				// 현재 섬과 동일한 번호면 안되겠지요
				if (area[newRow][newColumn] == area[row][column])	break;
				
				// 도비는 자유에요
				// 섬이 아니면 거리를 증가시켜요
				if (area[newRow][newColumn] == 0) {
					bridgeLength++;
				}
				// 그렇지 않으면 다른 섬을 만난 것이에요 
				else {
					// 근데 다리 길이가 2이상이어야 해요
					if (bridgeLength >= 2) {
						// 돌맹이가 아니라 멀쩡한 다리네요
						// 인접리스트에 추가를 합니다
						adjList[startIsland].add(new Node(area[newRow][newColumn], bridgeLength));
					}
					break;
				}
			}
		}
	}
	
	public static void prim(int islandCount) {
		
		int connectedCount = 0;
		
		// 우선순위큐와 인접리스트를 이용한 프림알고리즘
		// 시작 섬과 초기 가중치 -> 0
		pq.add(new Node(2, 0));
		while (!pq.isEmpty()) {
			int currentIsland = pq.peek().to;
			int currentLength = pq.peek().weight;
			pq.poll();
			
			// 방문한 섬이면 패스
			if (visitedPRIM[currentIsland])	continue;
			
			// 방문하지 않았으면 방문처리
			visitedPRIM[currentIsland] = true;
			// 최소길이 더하기
			minimumTotalBridgeLength += currentLength;
			// 섬 하나 연결되었으니 연결된 섬 1 증가
			connectedCount++;
			
			// 모든 섬 다 연결됐으면 종료
			if (connectedCount == islandCount)	return;
			
			// 현재 섬과 다음 섬과 연결되는 간선 우선순위 큐에 추가
			for (Node nextNode : adjList[currentIsland]) {
				// 이미 MST에 있는 섬이면 패스
				if (visitedPRIM[nextNode.to])	continue;
				
				// 그렇지 않으면 우선순위 큐에 추가
				pq.add(nextNode);
			}
		}
		
		// 모든 섬이 다 연결되었으면 while문 안에서 종료됨
		// 그렇지 않으면 여기까지 도달
		// 모든 섬이 연결되지 못한 것이니 -1로 업데이트
		minimumTotalBridgeLength = -1;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		area = new int[N][M];
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int column = 0; column < M; column++) {
				area[row][column] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 0은 바다, 1은 섬으로 입력이 주어짐
		// 즉, 그래프 탐색을 이용하기 위해서 섬에 번호를 붙여줘야함
		// BFS하기
		visitedBFS = new boolean[N][M];
		// 섬의 번호는 2부터 시작 -> 기존 섬이 1로 주어지기 때문
		int islandNum = 1;
		for (int row = 0; row < N; row++) {
			for (int column = 0; column < M; column++) {
				// 방문했으면 패스
				if (visitedBFS[row][column])	continue;
				
				// 그렇지 않으면 BFS 수행 - 번호도 붙이기
				// 1이면 번호가 안붙여진 섬
				if (area[row][column] == 1) {
					// 숫자증가 및 번호매기기
					bfs(row, column, ++islandNum);
				}
			}
		}
		
		// 인접리스트 생성
		adjList = new ArrayList[islandNum+1];
		for (int num = 2; num <= islandNum; num++) {
			adjList[num] = new ArrayList<>();
		}
		
		// 탐색하다가 섬이면 해당 섬과 다리를 건설할 수 있는 다른 섬이 있는 지 찾기
		// 건설이 가능하면 해당 섬과 다른 섬과의 다리 길이를 인접리스트에 추가
		for (int row = 0; row < N; row++) {
			for (int column = 0; column < M; column++) {
				// 바다가 아니라면!
				if (area[row][column] != 0) {
					// 다리 건설 모드
					bridge(row, column, area[row][column]);
				}
			}
		}
		
		// 거의 다옴. 이제 프림 알고리즘을 이용하여 모든 섬을 연결하는 다리 길이의 최솟값을 출력. 
		// 모든 섬을 연결하는 것이 불가능하면 -1 출력 
		pq = new PriorityQueue<>();
		visitedPRIM = new boolean[islandNum+1];
		minimumTotalBridgeLength = 0;
		// 섬번호가 2부터 시작하기때문에 실질적 섬의 개수는 islandNum-1
		prim(islandNum-1);
		
		// 결과 출력
		sb.append(minimumTotalBridgeLength);
		System.out.println(sb);
	}
}
