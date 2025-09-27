import java.io.*;
import java.util.*;

public class SWEA_5643_키순서_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [섬멸]
 * 1~N 번호 있는 학생들이 있음
 * 두 학생 키 비교한 결과 일부 주어짐
 * 
 * 몇번째인지 알수있는 학생의 수를 출력
 * 
 * [입력]
 * 첫 줄 테스크 케이스 입력
 * 	각 테스트 케이스 첫째줄에 학생들의 수 N주어짐
 * 	각 테스트 케이스 둘째줄에 비교횟수 M 주어짐
 * 	각 테스크 케이스 셋째줄부터 M줄에 거쳐 비교 학생 a,b주어짐
 * 	a가 b보다 작다는 것을 의미
 * 
 * [출력]
 * 자신이 몇 번째인지 알 수 있는 학생의 수 출력
 * 
 * [어캄]
 * BOJ 2458과 동일한 문제
 * 어찌 풀진 그것을 참고하도록
 */
	
	// 학생들의 수 N, 비교 횟수 M
	static int N, M;
	// 학생들의 키 비교를 저장할 인접행렬 생성 adjMatrix[a][b] = a에서 b로 가는 경로가 있습니다. (a가 b보다 작다)
	static boolean[][] adjMatrix;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			
			// 학생들의 수 N, 비교 횟수 M 입력
			N = Integer.parseInt(br.readLine().trim());
			M = Integer.parseInt(br.readLine().trim());
			
			// 키를 비교한 것을 기록할 인접행렬
			adjMatrix = new boolean[N+1][N+1];
			for (int i = 0; i < M; i++) {
				// a가 b보다 작음 a->b 경로임
				st = new StringTokenizer(br.readLine().trim());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjMatrix[a][b] = true;
			}
			
			// 플로이드 워셜 적용하기
			// 경유지
			for (int k = 1; k <= N; k++) {
				// 출발지
				for (int i = 1; i <= N; i++) {
					// 도착지
					for (int j = 1; j <= N; j++) {
						// i->k, k->j 경로가 존재하면 i->j로도 갈 수 있음
						if (adjMatrix[i][k] && adjMatrix[k][j]) {
							adjMatrix[i][j] = true;
						}
					}
				}
			}
			
			// 플로이드 워셜 완료
			// 나로 부터 가거나 나까지 오는 경로가 있는 지 확인
			// 확인 결과 경로가 N-1개 있으면 나는 모두와 연결이 되어있기 때문에 몇번째인지 알 수 있을 것
			
			// 결과를 저장
			int ans = 0;
			// i부터 시작
			for (int i = 1; i <= N; i++) {
				
				// i와 이어져있는 경로를 확인할 때 연결되어 있는 개수 확인
				int connect = 0;
				
				// j와 연결되어있는 지를 고려
				for (int j = 1; j <= N; j++) {
					// i->j 이거나 j->i이면 밑에든 위든 존재하니깐 몇번째인지 알지요
					if (adjMatrix[i][j] || adjMatrix[j][i]) {
						connect++;
					}
				}
				
				// 연결되어있는 것이 N-1개 인지 확인
				if (connect == N-1) {
					// 연결되어있으면 정답 증가
					ans++;
				}
			}
			
			sb.append('#').append(test_case).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}
}
