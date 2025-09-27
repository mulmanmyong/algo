import java.io.*;
import java.util.*;

public class BOJ_1613_역사_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

/*
 * [설명]
 * 역사에 사건들이 존재함
 * 알고 있는 일부 사건들의 전후 관계들이 주어질 떄, 주어진 사건들의 전후 관계도 알수 있을까?
 * 
 * [입력]
 * 첫째 줄에 사건의 개수 n(400이하), 사건의 전후 관계의 수 k(50000이하) 주어짐
 * 둘째 줄부터 k줄에 거쳐 전후관계 알고 있는 두 사건의 번호 주어짐 a, b주어지면 a가 b보다 먼저 일어났다는 것을 의미
 * 전후 관계 모순인 경우는 없음
 * 
 * 이후 알고 싶은 사건 쌍의 수 s주어짐 
 * 다음 s줄에 각각 서로 다른 두 사건의 번호 주어짐
 * 사건의 번호는 1이상 N이하
 * 
 * [출력]
 * s줄에 거쳐 각줄에 답을 출력
 * 앞에 있는 번호의 사건이 먼저 일어났으면 -1
 * 뒤에 있는 번호의 사건이 먼저 일어났으면 1
 * 어떤지 모르면 0을 출력하기
 * 
 * [어캄]
 * 플로이드 워셜로 사건의 관게들을 모두 기록하기 
 * 
 * 이후 s줄에 거쳐서 입력에 따라 판단하기
 * 해당경로가 존재하면 순서에 따라 1, -1을 출력하고
 * 존재안하면 모르는 거니깐 0을 출력하게 해보자
 */
	
	// 사건의 개수 n, 사건의 전후 관계의 수 k
	static int n, k;
	// 전후 관계 저장할 인접행렬
	static boolean[][] adjMatrix;
	// 알고 싶은 사건 쌍의 수 s
	static int s;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 사건의 개수 n, 사건의 전후 관계의 수 k 입력
		st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		// 사건의 전후 관계 입력
		adjMatrix = new boolean[n+1][n+1];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjMatrix[a][b] = true;
		}
		
		// 플로이드 워셜로 전후관계 파악 가능 여부 저장하기
		// 경유지
		for (int k = 1; k <= n; k++) {
			// 출발지
			for (int i = 1; i <= n; i++) {
				// 도착지
				for (int j = 1; j <= n; j++) {
					// i->k, k->j 경로 다 존재하면 i->j도 가능한 것임. 전후관계 파악가능
					if (adjMatrix[i][k] && adjMatrix[k][j]) {
						adjMatrix[i][j] = true;
					}
				}
			}
		}
		
		// 전후관계 파악가능 한것 저장 완료
		// 물음에 답할 개수 s 입력
		s = Integer.parseInt(br.readLine().trim());
		
		// s줄에 거쳐 a, b에따라 물음 답하기
		for (int i = 1; i <= s; i++) {
			// a, b 입력
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// a가 먼저 발생했으면 -1 출력
			if (adjMatrix[a][b]) {
				sb.append(-1).append('\n');
			}
			// b가 먼저 발생했으면 1 출력
			else if (adjMatrix[b][a]) {
				sb.append(1).append('\n');
			}
			// 둘 다 아니면 어떤지 모르는 것(유추할 수 없음) 0 출력
			else {
				sb.append(0).append('\n');
			}
		}

		System.out.println(sb);
	}
}
