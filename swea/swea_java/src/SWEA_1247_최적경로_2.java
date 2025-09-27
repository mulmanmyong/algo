import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1247_최적경로_2 {
	
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
 * 기존 코드는 백트래킹 + DFS
 * 이를 DP로 구현을 하려면?
 * 우선 각각의 거리를 미리 계산을 해둬야 함
 * 비트마스킹을 이용해서 해당 위치를 갔는 지 확인하기 -> 이게 배열의 인덱스로 들어갈 것 
 */
	
	
	// 고객의 수 N
	static int N;
	// 고객들의 좌표
	static int[][] point;
	// 회사의 좌표
	static int[] companyPoint;
	// 집의 좌표
	static int[] homePoint;
 	// 최소 거리를 담을 변수
	static int minDistance;
	
	// 거리 저장 배열
	static int[][] dist;
	// dp
	static int[][] dp;
	
	
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
			
			// 거리를 미리 계산
			// 
			dist = new int[N + 2][N + 2];
			
			// 회사와 고객, 고객과 집까지의 거리를 계산하기 
			// 회사는 N, 집은 N+1 -> 고객방문 여부 비트마스킹을 쓸 건데 이 때 0~N-1까지하는게 편해서 이렇게 함
			for (int i = 0; i < N; i++) {
				// 회사 -> 고객
				dist[N][i] = calculateDistance(companyPoint[0], companyPoint[1], point[i][0], point[i][1]);
				// 고객 -> 집
				dist[i][N + 1] = calculateDistance(point[i][0], point[i][1], homePoint[0], homePoint[1]);
			}
			
			// 고객과 고객
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					// 제자리는 안됨 
					if (i == j) continue;
					// i->j로 가는 거리 
					dist[i][j] = calculateDistance(point[i][0], point[i][1], point[j][0], point[j][1]);
				}
			}
			
			// dp를 이용해서 해당 고객 방문했는 지 확인하기
			// 비트마스킹으로 방문상태, 방문할 고객을 인덱스로 가짐
			int size = 1 << N;
			dp = new int[size][N];
			
			// 최소값을 갱신하는 것이므로 초기값을 최대값으로 설정
			for (int[] row : dp) {
				Arrays.fill(row, Integer.MAX_VALUE);
			}
			
			// 회사에서 각 고객으로 이동하는 경우 
			for (int i = 0; i < N; i++) {
				// 1 << i 는 i번째 고객을 방문했음을 의미, 
				dp[1 << i][i] = dist[N][i];
			}
			
			// dp를 진행 최소값을 갱신하며...
			// 모든 방문 상태를 확인하며..
			for (int currentVisitBits = 1; currentVisitBits < size; currentVisitBits++) {
				
				// i번 고객에서
				for (int i = 0; i < N; i++) {
					// 해당 비트에서 i가 없으면 i에서 출발하는 것도 아님
					if ((currentVisitBits & (1 << i)) == 0)	continue;
					
					// j번 고객으로
					for (int j = 0; j < N; j++) {
						// j가 이미 있으면 j 고객은 방문했던 것임
						if ((currentVisitBits & (1 << j)) != 0)	continue;
						
						// 방문하지 않았으면 j 고객으로 갈 수 있음
						int nextBit = currentVisitBits | (1 << j);
						// 다음 상태와 현재에서 다음으로 이동할때 중에서 최소값으로 갱신
						dp[nextBit][j] = Math.min(dp[nextBit][j], dp[currentVisitBits][i] + dist[i][j]);
					}
				}
				
			}
			
			// 고객들 모두 방문 완료..
			// 이제 집으로 이동하는 경우 중 최소인 것
			for (int i = 0; i < N; i++) {
				minDistance = Math.min(minDistance, dp[size-1][i] + dist[i][N+1]);
			}
			
			sb.append('#').append(test_case).append(' ').append(minDistance).append('\n');
		}
		System.out.print(sb);
	}
	
	public static int calculateDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
