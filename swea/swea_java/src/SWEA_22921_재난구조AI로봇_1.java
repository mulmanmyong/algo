import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_22921_재난구조AI로봇_1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	/*
	 * AI 로봇을 아무곳에 투하하는 경우 로봇이 손상될 위험이 있어, 
	 * 건물의 가장 자리 (0, 0) 위치에 투하, 조난자가 있는 위치 (N-1, N-1)위치 까지 이동 
	 * 
	 * 로봇 상하좌우 이동 
	 * 	하지만 연료 소모 비용 발생 
	 * 	1) 현재 위치의 높이 < 다음 위치의 높이 : |다음 위치의 높이 - 현재 위치의 높이|*2
	 * 	2) 현재 위치의 높이 > 다음 위치의 높이 : 0 
	 * 	3) 현재 위치의 높이 == 다음 위치의 높이 : 1 
	 * 
	 * 	[제약 사항]
	 * 	1. 건물의 범위(N)는 5부터 30이하의 자연수 
	 * 	2. 잔해가 쌓여있는 높이 0부터 9이하의 자연수 
	 * 	3. 로봇은 (0, 0) 위치에서 시작 
	 * 	4. 조난자는 (N-1, N-1) 위치에 존재 
	 */
	
	// 테스트케이스  
	static int testCase;
	// 건물의 범위
	static int N;
	// 각 위치에 쌓인 잔해의 정보  
	static int[][] initMap;
	// 연료 소모량을 담을 배열 
	static int[][] fuel;
	// 방문 처리 배열
	static boolean[][] visited;
	// 최소 소모 연료 
	static int minFuel;
	
	// 방향 배열
	// 상하좌우 
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaCol = {0, 0, -1, 1};
	
	// ㅜㅡㅜ DFS는 시간 초과..
	public static void findMinFuelDFS(int currentRow, int currentCol, int currentFuel) {
		// DFS로 진행하기 
		
		// 가지 치기 : 현재까지 소모한 연료가 이미 최소 소모 연료보다 커지면 종료
		if (currentFuel >= minFuel) {
			return;
		}
		
		// 기저 조건 : 현재의 좌표가 목적지에 도달했으면
		if (currentRow == N-1 && currentCol == N-1) {
			// 최소 연료 소모 갱신
			minFuel = Math.min(minFuel, currentFuel);
			return;
		}
		
		// 방문 처리
		visited[currentRow][currentCol] = true;
		
		// 구현 : 4방향으로 이동을 하며, 이미 왔던 길을 되돌아 오지 않도록 하기 
		for (int dir = 0; dir < 4; dir++) {
			int newRow = currentRow + deltaRow[dir];
			int newCol = currentCol + deltaCol[dir];
			
			// 범위를 벗어나면 안됨
			if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N)	continue;
			// 이미 지나온 범위면 안됨
			if (visited[newRow][newCol])	continue;
			
			// 그렇지 않다면 이동하기
			visited[newRow][newCol] = true;
			
			// 연료 소모량 계산해서 이동하기 
			// 1) 현재 위치의 높이 < 다음 위치의 높이 : |다음 위치의 높이 - 현재 위치의 높이|*2
			if (initMap[currentRow][currentCol] < initMap[newRow][newCol]) {
				findMinFuelDFS(newRow, newCol, currentFuel + (Math.abs(initMap[newRow][newCol] - initMap[currentRow][currentCol])*2));
			}
			// 2) 현재 위치의 높이 > 다음 위치의 높이 : 0 
			else if (initMap[currentRow][currentCol] > initMap[newRow][newCol]) {
				findMinFuelDFS(newRow, newCol, currentFuel);
			}
			// 3) 현재 위치의 높이 == 다음 위치의 높이 : 1 
			else {
				findMinFuelDFS(newRow, newCol, currentFuel + 1);
			}
			
			// 원복
			visited[newRow][newCol] = false;
		}
 	}
	
    public static void findMinFuelBFS(int startRow, int startCol) {
    	// fuel 배열을 최대값을 초기화 
    	fuel = new int[N][N];
    	for (int i = 0; i < N; i++) {
    		Arrays.fill(fuel[i], Integer.MAX_VALUE);
    	}
    	
    	// BFS로 해보기 
        Queue<int[]> q = new LinkedList<>();  
        q.add(new int[] {startRow, startCol});
        fuel[startRow][startCol] = 0;
        
        // 탐색 시작 
        while(!q.isEmpty()) {
            int currentRow = q.peek()[0];
            int currentCol = q.peek()[1];
            q.poll();
            
            // 4방향 확인 
            for(int dir = 0; dir < 4; dir++) {
            	int newRow = currentRow + deltaRow[dir];
            	int newCol = currentCol + deltaCol[dir];
            	
            	// 범위 벗어나면 안됨 
            	if(newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) 	continue;
            	
            	// 이동할 때 연료 소모량 
				int cost = 0;
				if (initMap[currentRow][currentCol] < initMap[newRow][newCol]) {
					cost = Math.abs(initMap[newRow][newCol] - initMap[currentRow][currentCol]) * 2;
				} else if (initMap[currentRow][currentCol] > initMap[newRow][newCol]) {
					cost = 0;
				} else {
					cost = 1;
				}
				
            	// 지금까지 소모량+이번소모량이 다음 소모량보다 작으면 
				if (fuel[currentRow][currentCol] + cost < fuel[newRow][newCol]) {
					// 갱신 
					fuel[newRow][newCol] = fuel[currentRow][currentCol] + cost;
					// 큐에 저장 
					q.add(new int[]{newRow, newCol});
				}
            }
        }
        
        minFuel = fuel[N-1][N-1];
    }
    
	// 우선순위 큐를 이용한 BFS -> 다익스트라임 -> 아닌듯? 모르겠네,, 정상동작은 함 
	public static void findMinFuelDijkstra() {
		// fuel 배열을 최대값을 초기화 
		fuel = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(fuel[i], Integer.MAX_VALUE);
		}

		// 우선순위 큐를 이용해서 연료 소모량이 적은 순부터 진행 
		// row, col, 현재까지의 연료 소모량
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
		
		// 초기값 설정
		fuel[0][0] = 0;
		pq.offer(new int[]{0, 0, 0}); // 시작점 (0,0), 연료 0

		// 우선순위 큐를 이용하여 탐색 시작 
		while (!pq.isEmpty()) {
			int currentRow = pq.peek()[0];
			int currentCol = pq.peek()[1];
			int currentFuel = pq.peek()[2];
			pq.poll();

			// 현재까지 소모량이 현재 저장되어있는 최저 소모량 보다 크면 패스 
			if (currentFuel > fuel[currentRow][currentCol])		continue;
			
			// 현재의 좌표가 목적지에 도달하면 종료 
			if (currentRow == N - 1 && currentCol == N - 1) {
				minFuel = currentFuel;
				return;
			}
			
			// 4방향 탐색
			for (int dir = 0; dir < 4; dir++) {
				int newRow = currentRow + deltaRow[dir];
				int newCol = currentCol + deltaCol[dir];
				
				// 범위 벗어나면 안됨 
				if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) continue;

				// 연료 소모량 
				int cost = 0;
				if (initMap[currentRow][currentCol] < initMap[newRow][newCol]) {
					cost = Math.abs(initMap[newRow][newCol] - initMap[currentRow][currentCol]) * 2;
				} else if (initMap[currentRow][currentCol] > initMap[newRow][newCol]) {
					cost = 0;
				} else {
					cost = 1;
				}

				// 지금까지 소모량+이번소모량이 다음 소모량보다 작으면 
				if (currentFuel + cost < fuel[newRow][newCol]) {
					// 갱신 
					fuel[newRow][newCol] = currentFuel + cost;
					// 우선순위 큐에 저장 
					pq.offer(new int[]{newRow, newCol, currentFuel + cost});
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스를 입력받음 
		testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			// 건물의 범위를 입력 받음 
			N = Integer.parseInt(br.readLine().trim());
			
			// 건물의 정보를 입력받음  
			initMap = new int[N][N];
			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int column = 0; column < N; column++) {
					initMap[row][column] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 
			minFuel = Integer.MAX_VALUE;
			visited = new boolean[N][N];
//			findMinFuelDFS(0, 0, 0);
			findMinFuelBFS(0, 0);
		
			sb.append('#').append(test_case).append(' ').append(minFuel).append('\n');
		}
		System.out.println(sb);
	}
	
}
