import java.io.*;
import java.util.*;

public class SWEA_2117_홈방범서비스_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

/*
 * [설명]
 * NxN 크기의 도시에 홈방범 서비스를 제공하려고 함
 * 홈방범 서비스는 운영 상의 이유로 파란색 부분과 같이 마름모 모양의 영역에서만 제공
 * 
 * 홈방법 서비스를 제공하기 위해서는 운영 비용이 필요함
 * 서비스 영역의 크기 K가 커질수록 운영비용이 커짐
 * 운영 비용은 서비스 영역의 면적과 동일하며, 아래와 같이 구할 수 있음
 * 운영 비용 = K*K + (K-1)*(K-1)
 * 
 * 영역에서 벗어난 범위가 있어도 운영비용은 변경되지 않음
 * 
 * 
 * 제공받는 집들은 각각 M의 비용을 지불할 수 있음
 * 보안회사에서 손해보지 않는 한 최대한 많은 집에 홈방범 서비스를 제공하려고 함
 * 
 * 손해를 보지 않으면서 홈방범 서비스를 가장 많은 집들에 제공하는 서비스 영역을 찾고,
 * 홈방법 서비스를 제공 받는 집들의 수를 출력
 * 
 * [입력]
 * 첫 줄에는 총 테스크 케이스의 개수 T 주어짐
 * 각 테스트 케이스의 첫 번째 줄에는 도시의 크기 N과 하나의 집이 지불할 수 있는 비용 M 주어짐
 * 그 다음 줄부터 도시 정보 주어짐, 지도에서 1은 집이 위치한 곳, 나머지는 0
 * 
 * [출력]
 * 출력해야 할 정답은 손해를 보지 않으면서 홈방범 서비스를 가장 많은 집들에 제공하는 
 * 서비스 영역을 찾았을 때, 그 때의 서비스를 제공 받는 집들의 수
 * 
 * [흐음]
 * 그냥 완탐하면 될 거 같은데 
 */
	
	// 도시의 크기 N, 하나의 집이 지불할 수 있는 비용 M
	static int N, M;
	// 도시의 상태
	static int[][] city;
	
	// 집이 위치한 곳을 저장할 리스트
	static ArrayList<int[]> homeList;

	// 정답을 저장할 변수
	static int ans;
	
	public static void simulation() {
		
		// 도시의 모든 지점을 순회하며 서비스 영역의 중심으로 설정
		for (int row = 0; row < N; row++) {
			for (int col = 0; col < N; col++) {
				
				// 서비스 영역의 크기 K를 1부터 도시 전체를 덮을 수 있는 크기까지 증가시킴
				for (int k = 1; k <= N * 2; k++) {
					int count = 0;
					// 운영 비용 계산
					int cost = k * k + (k - 1) * (k - 1);
					
					// 저장된 집 목록을 순회하며 현재 서비스 영역에 포함되는지 확인
					for(int[] home : homeList) {
						int homeRow = home[0];
						int homeCol = home[1];
						
						// 중심점과 집 사이의 거리 계산
						int dist = Math.abs(row - homeRow) + Math.abs(col - homeCol);
						
						// 거리가 k보다 작으면 서비스 영역에 포함됨
						if (dist < k) {
							count++;
						}
					}
					
					// 손해인지 계산
					int lose = count * M - cost;
					
					// 손해를 보지 않는 경우 
					// 0 이상이면 손해본것이 아님
					if (lose >= 0) {
						// 현재 개수가 더 크면 갱신
						ans = Math.max(ans, count);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {

			// 도시의 크기 N, 하나의 집이 지불할 수 있는 비용 M 입력
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// 변수 초기화
			city = new int[N][N];
			homeList = new ArrayList<>();
			ans = 0; // 각 테스트 케이스마다 정답 초기화
			
			// 도시의 상태 입력
			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < N; col++) {
					city[row][col] = Integer.parseInt(st.nextToken());
					
					// 1이면 집이다
					if (city[row][col] == 1) {
						homeList.add(new int[] {row, col});
					}
				}
			}
			
			// K = 1부터해서 점점 늘려가면?
			simulation();
			
			sb.append('#').append(test_case).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}
}