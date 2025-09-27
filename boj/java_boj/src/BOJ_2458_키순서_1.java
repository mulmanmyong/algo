import java.io.*;
import java.util.*;

public class BOJ_2458_키순서_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [설명]
 * 1~N까지 번호가 붙여져 있는 학생들에 대하여 두 학생끼리 키를 비교한 결과의 일부가 주어져 ㅣㅇㅅ음
 * 키 모두 다름 
 * 
 * 자신이 몇 번째인지 알 수 있는 학생들도 있고 그렇지 못한 학생들도 있음
 * 자신이 몇 번째인지 알 수 있는 학생들의 수를 출력하십시오.,.,.,
 * 
 * [입력]
 * 첫째 줄에 학생들의 수 N(2-500), 두 학생 키를 비교한 횟수 M이 주어짐
 * M개의 줄에 거쳐 두 학생의 키를 비교한 a와 b가 주어짐. 이는 번호가 a인 학생이 번호가 b인 학생보다 작다는 것을 의미
 * 
 * [출력]
 * 자신이 키가 몇 번째인지 알 수 있는 학생이 모두 몇 명인지를 출력
 * 
 * [어캄]
 * 자신보다 밑에 있는 아이와 자신보다 위에 있는 아이의 개수가 N-1개가 되면 몇번째인지 알 수 있는 것임 
 * 그렇다면 입력을 기반으로 하여 인접행렬을 만들고,
 * 경로가 있으면 true, 그렇지 않으면 false로 함
 * 
 * 이를 플로이드 워셜을 이용해서 이동할 수 있는 dp 기법을 이용해서 시작에서 경유, 경유-도착이 모두 true라면
 * 시작-도착도 이동할 수 있는 경로이니 true로 변경을 해주는 것이야 
 * 이를 N^3회를 하고
 * 
 * 2중 for문을 통해서 특정 지점에서 모든 지점을 돌면서 경로가 있는 지 파악
 * 그 개수가 N-1개가 되면 count증가
 */
	
	// 학생들의 수 N, 비교 횟수 M
	static int N, M;
	// 학생들의 키 비교를 저장할 인접행렬 생성 adjMatrix[a][b] = a에서 b로 가는 경로가 있습니다. (a가 b보다 작다)
	static boolean[][] adjMatrix;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 학생들의 수 N, 비교 횟수 M 입력
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
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

		sb.append(ans);
		System.out.println(sb);
	}
}
