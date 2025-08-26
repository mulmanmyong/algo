import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// DFS, 백트래킹으로 풀이 
public class SWEA_1247_최적경로_1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * 회사와 집의 위치, 그리고 각 고객의 위치는 이차원 정수 좌표 (x, y)로 주어짐
 * 두 위치 (x1, y1) (x2, y2) 사이의 거리는 |x1-x2|+|y1-y2|로 계산 -> 가중치 
 * 
 * 회사에서 출발하여 N명의 고객을 모두 방문하고 집으로 돌아는 경로 중 가장 짧은 것을 찾으려 함
 * 		회사출발 -> N명의 고객방문 -> 집 : 시작과 끝의 좌표는 동일함 
 * 
 * 입력
 * 첫째줄에 테스트 케이스
 * 		각 테스트 케이스의 첫째줄에는 고객의 수 N
 * 		각 테스트 케이스의 둘째줄에는 회사좌표, 집좌표, N명의 고객의 좌표가 차례로 나옴
 * 
 * 출력
 * 		#테스트케이스번호 최단경로이동거리
 * 
 * 최단경로에 팔려 크루스칼인가 했지만, 시작과 끝 지점이 정해져 있기 때문에, 이는 DFS+백트래킹으로 구현해야함
 */
	
	
	// 고객의 수 N
	static int N;
	// 고객들의 좌표
	static int[][] point;
	// 회사의 좌표
	static int[] companyPoint;
	// 집의 좌표
	static int[] homePoint;
 	// 최조 거리를 담을 변수
	static int minDistance;
	
	// dfs에서 방문 처리할 배열
	static boolean[] visited;
	
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스를 입력받는다
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			// 고객의 수를 입력 받음
			N = Integer.parseInt(br.readLine().trim());
			// 회사 집 고객의 좌표를 입력 받음
			st = new StringTokenizer(br.readLine().trim());
			
			// 회사의 좌표
			companyPoint = new int[2];
			companyPoint[0] = Integer.parseInt(st.nextToken());
			companyPoint[1] = Integer.parseInt(st.nextToken());
			
			// 집의 좌표
			homePoint = new int[2];
			homePoint[0] = Integer.parseInt(st.nextToken());
			homePoint[1] = Integer.parseInt(st.nextToken());
			
			// 고객의 좌표 
			point = new int[N][2];
			for (int index = 0; index < N; index++) {
				point[index][0] = Integer.parseInt(st.nextToken());
				point[index][1] = Integer.parseInt(st.nextToken());
			}
			
			// 최소 거리 담을 변수
			minDistance = Integer.MAX_VALUE;
			// 방문 처리할 배열
			visited = new boolean[N];
			// 시작거리, 방문고객수, 현재의 X좌표, 현재의 Y좌표 : DFS
			findMinimumDistance(0, 0, companyPoint[0], companyPoint[1]);
			
			sb.append('#').append(test_case).append(' ').append(minDistance).append('\n');
		}
		System.out.print(sb);
	}
	
	// 최소 거리 찾기 
	public static void findMinimumDistance(int currentDistance, int count, int x, int y) {		
		// 가지치기 : 지금까지의 거리가 이미 최소보다 커졌으면 return
		if (currentDistance >= minDistance) {
			return;
		}
		
		// 마지막이면 현재 좌표와 집까지의 거리 더하기
		// 그 후에 최소값 갱신
		if (count == N) {
			minDistance = Math.min(minDistance, currentDistance + calculateDistance(x, y, homePoint[0], homePoint[1]));
			return;
		}
		
		// 구현하기 : 차근차근 DFS하기
		for (int index = 0; index < N; index++) {
			// 이미 선택헀던 것이면 패스
			if (visited[index])	continue;
			// 그렇지 않으면 현재 인덱스 방문처리
			visited[index] = true;
			// 움직이려는 위치(현재의 인덱스), 현재 위치하고 있는 위치와의 거리 계산 후 더하기 
			findMinimumDistance(currentDistance + calculateDistance(x, y, point[index][0], point[index][1]), count+1, point[index][0], point[index][1]);
			// 원복
			visited[index] = false;
		}
	}
	
	public static int calculateDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
