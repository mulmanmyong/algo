import java.io.*;
import java.util.*;

public class SWEA_1263_사람네트워크2_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [설명]
 * 주어진 사람 네트워크에서 누가 가장 영향력이 있는 사람인지 알아보기
 * 
 * N은 입력 사람 네트워크의 노드 수
 * Closeness는 네트워크 상에서 한 사용자가 다른 모든 사람에게 얼마나 가까운가를 나타냄 
 * CC(i) = 는 i노드에서j 노드들까지의 최단거리 합을 의미함
 * 
 * 제약사항으로
 * 단, 사람 네트워크의 최대 사용자 수는 1,000 이하이다.
 * 테스트 케이스들은 아래의 그룹들로 이루어져 있다
 * 그룹 1 싸이클이 없고 N <= 10 인 경
 * 그룹 2 싸이클이 없고 10 < N <= 100 인경
 * 그룹 3 싸이클이 있고 N<= 10
 * 그룹 4 싸이클이 있고10 < N <= 100
 * 그룹 5 모든 경우가 존재하고 100 < N <= 1000
 * 
 * [입력]
 * 테스트 케이스 T 주어짐
 * 	각 테스트 케이스는 한줄에 주어짐
 * 	N 다음에 인접행렬이 행 우선순위로 주어짐 
 * 
 * [출력]
 * CC값들 중에서 최솟값을 한 줄에 출력 
 * 
 * [엌]
 * 플로이드 워셜로 최단 거리들을 구하고
 * 그것을 이용해서 CC를 구하고, 최솟값을 찾기
 * 
 */
	
	// 사람의 수 N
	static int N;
	// 플로이드 워셜을 위한 인접행렬
	static int[][] adjMatrix;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {

			// 한 줄에 입력이 주어짐
			st = new StringTokenizer(br.readLine().trim());
			
			// 사람의 수 N입력
			N = Integer.parseInt(st.nextToken());
			
			// 초기 인접행렬을 입력 받음
			// 사람은 0~N-1까지 번호가 부여
			adjMatrix = new int[N][N];
			// 최댓값으로 초기화
			for (int row = 0; row < N; row++) {
				Arrays.fill(adjMatrix[row], Integer.MAX_VALUE);
			}
			
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					int num = Integer.parseInt(st.nextToken());
					
					// 1인경우만 저장
					if (num == 1) {
						adjMatrix[row][col] = num;
					}
				}
			}
			
			// 플로이드 워셜을 진행
			// 현재저장된 길이와 현재계산한 길이 중 최소값으로 계속 갱신해나감
			
			// 경유지
			for (int k = 0; k < N; k++) {
				// 출발지
				for (int i = 0; i < N; i++) {
					// 목적지 
					for (int j = 0; j < N; j++) {
						
						// 출발지와 목적지가 같은 경우
						// 출발지와 경유지가 같은 경우
						// 경유지와 목적지가 같은 경우
						// 싸이클임 -> 패스
						if (i==j || i==k || k==j) continue;
						
						// 그것이 아니라면 최단거리를 기록해봄
						
						// 근데 경유지 쪽을 가는 길이 연결되어있지 않으면 안됨
						if (adjMatrix[i][k] == Integer.MAX_VALUE || adjMatrix[k][j] == Integer.MAX_VALUE) continue;
						
						// 현재 저장된 길이가 최소냐
						// 이 경유지를 통해 가는 길이 최소냐 
						adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k]+adjMatrix[k][j]);
						// 양방향
						adjMatrix[j][i] = adjMatrix[i][j];
					}
				}
			}
			
			// 최소 CC 찾기
			int ans = Integer.MAX_VALUE;
			// i를 기점으로 cc계산
			for (int i = 0; i < N; i++) {
				int cc = 0;
				for (int j = 0; j < N; j++) {
					// 시작과 끝 같으면 안됨, 
					if (i == j) continue;
					// adjMatrix[i][j] == Integer.MAX_VALUE 면 안됨. 연결되어 있지 않다는 것
					if (adjMatrix[i][j] == Integer.MAX_VALUE) continue;
					
					// cc값 계산
					cc += adjMatrix[i][j];
				}
				
				// 최소인지 보기
				ans = Math.min(ans, cc);
			}
			sb.append('#').append(test_case).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}
}
