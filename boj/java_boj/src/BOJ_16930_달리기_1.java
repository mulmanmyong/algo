import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16930_달리기_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * 로봇 청소기가 NxM인 집 안 청소 중임
 * 1초에 최대 K칸 상 하 좌 우 한방향 직선으로 이동가능
 * 벽은 통과 불가. 방향 바꾸려면 반드시 방향을 바꾸려는 위치에서 멈춰야 함
 * (r1, c1) 에서 (r2, c2) 까지 이동하는 최소 시간 구하기
 * 이동 불가하면 -1 출력
 * 
 * [입력]
 * 첫째 줄에 정수의 방 크기 N, M 그리고 최대 이동가능한 칸의 개수 K 입력으로 주어짐
 * M 줄에 거쳐 정보가 주어짐
 * 빈공간 ., 장애물 #
 * 
 * 마지막줄 r1 c1 2r c2 주어짐
 */
	
	// 방의 크기 최대 이동가능한 칸의 개수 K
	static int N, M, K;
	// 방의 정보
	static char[][] room;
	// 시작 위치 종료 위치
	static int startRow, startCol;
	static int endRow, endCol;
	
	// 최소시간
	static int minTime;
	// 방문처리 배열
//	static boolean[][] visited;
	static int[][] visited;
	
	
	// 방향 배열
	// 상 하 좌 우
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaCol = {0, 0, -1, 1};
	
//	public static void simulation(int currentStartRow, int currentStartCol, int currentTime) {
//		if (currentStartRow == endRow && currentStartCol == endCol) {
//			minTime = Math.min(minTime, currentTime);
//			return;
//		}
//		
//		// 현재방향을 기점으로 쭉 몇칸 이동할지 정하기
//		
//			
//		for (int dir = 0; dir < 4; dir++) {
//			for (int go = 1; go <= K; go++) {	
//				int newRow = currentStartRow + deltaRow[dir]*go;
//				int newCol = currentStartCol + deltaCol[dir]*go;
//				
//				// 범위 벗어 났는 지?
//				if (newRow <= 0 || newRow > N || newCol <= 0 || newCol > M)	continue;
//				// 방문했으면 패스
//				if (visited[newRow][newCol])	continue;
//				// 장애물이면 종료
//				if (room[newRow][newCol] == '#')	break;
//			
//				// 그렇지 않다면 이동하기 
//				visited[newRow][newCol] = true;
//				simulation(newRow, newCol, currentTime + 1);
//				visited[newRow][newCol] = false;
//			}
//			
//		}
//	}

    public static int BFS() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startRow, startCol});
        visited[startRow][startCol] = 0; // 시작점 시간은 0

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int currentRow = current[0];
            int currentCol = current[1];
            int currentTime = visited[currentRow][currentCol];
            
            if (currentRow == endRow && currentCol == endCol) {
                return currentTime;
            }

            // 4방향으로 이동
            for (int dir = 0; dir < 4; dir++) {
                // 한 방향으로 최대 K칸까지 직진
                for (int go = 1; go <= K; go++) {
                    int newRow = currentRow + deltaRow[dir] * go;
                    int newCol = currentCol + deltaCol[dir] * go;

                    // 범위를 벗어나거나 벽을 만나면 해당 방향 탐색 중단
                    if (newRow <= 0 || newRow > N || newCol <= 0 || newCol > M || room[newRow][newCol] == '#') {
                        break;
                    }
                    
                    // 이미 더 빠른 경로로 방문했다면 통과
                    if (visited[newRow][newCol] != -1 && visited[newRow][newCol] < currentTime + 1) {
                        break;
                    }
                    
                    // 아직 방문하지 않았거나, 더 긴 경로로 방문했다면 갱신하고 큐에 추가
                    if (visited[newRow][newCol] == -1 || visited[newRow][newCol] > currentTime + 1) {
                         visited[newRow][newCol] = currentTime + 1;
                         q.add(new int[]{newRow, newCol});
                    }
                }
            }
        }
        
        return -1; // 목적지에 도달하지 못한 경우
    }

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 방의크기, 최대 이동한 칸의 개수 입력
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 방의 정보를 입력받음
		room = new char[N+1][M+1];
		for (int row = 1; row <= N; row++) {
			String str = br.readLine().trim();
			for (int col = 1; col <= M; col++) {
				room[row][col] = str.charAt(col-1);
			}
		}
		
		// 시작과 도착을 입력받음
		st = new StringTokenizer(br.readLine().trim());
		startRow = Integer.parseInt(st.nextToken());
		startCol = Integer.parseInt(st.nextToken());
		endRow = Integer.parseInt(st.nextToken());
		endCol = Integer.parseInt(st.nextToken());
		
		// 동작을 시켜보기 
		minTime = Integer.MAX_VALUE;
//		minTime = -1;
//		visited = new boolean[N+1][M+1];
		visited = new int[N+1][M+1];
//		simulation(startRow, startCol, 0);
		minTime = BFS();
		
		// 도달 못했으면 초기값 그대로
//		if (minTime == Integer.MAX_VALUE) {
//			sb.append(-1);
//		}
//		else {
//			sb.append(minTime);
//		}
		sb.append(minTime);
		System.out.println(sb);
	}
}
