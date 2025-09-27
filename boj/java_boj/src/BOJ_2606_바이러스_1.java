import java.io.*;
import java.util.*;

public class BOJ_2606_바이러스_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [설명]
 * 웜 바이러스는 네트워크를 통해 전파됨
 * 한 컴퓨터가 웜 바이러스에 걸리면 그 컴퓨터와 네트워크 상에서 연결되어 있는 모든 컴퓨터는 웜 바이러스 걸림
 * 
 * 어느 날 1번 컴퓨터 웜 바이러스에 걸림
 * 컴퓨터의 수와 네트워크 상에서 서로 연결되어 있는 정보가 주어질 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수 출력
 * 
 * [입력]
 * 첫째 줄에 컴퓨터의 수 주어짐 (100 이하 양의 정수 1~100)
 * 둘째 줄에 네트워크 상에 직접 연결되어 있는 컴퓨터 쌍의 수 주어짐
 * 셋째 줄부터 컴퓨터의 번호쌍 주어짐
 * 
 * [출력]
 * 1번 컴퓨터가 웜 바이러스에 걸렸을 때, 1번을 통해 걸리게 되는 컴퓨터의 수를 출력
 * 
 * [어캄]
 * 일단 플로이드 워셜을 이용해서 이어져 있는 연관관계를 만들고
 * 
 * 1 출발이든 1 도착이든 경로가 존재하는 컴퓨터의 수를 count하기
 */ 
	
	// 컴퓨터의 수 N
	static int N;
	// 컴퓨터 쌍의 수 M
	static int M;
	// 연관관계 저장할 인접행렬
	static boolean[][] adjMatrix;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 컴퓨타의 수 N, 콤푸타 쌍의 수 M 입력
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
		
		// 연관관계 입력
		adjMatrix = new boolean[N+1][N+1];
		for (int i = 0; i < M; i++) {
			// a와 b 입력
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			adjMatrix[a][b] = true;
			adjMatrix[b][a] = true;
		}
		
		// 플로이드 진행 
		// 경유지
		for (int k = 1; k <= N; k++) {
			// 출발지
			for (int i = 1; i <= N; i++) {
				// 도착지
				for (int j = 1; j <= N; j++) {
					// i->k , k->j 만족하면 i->j도 가능, j->i도 가능
					if (adjMatrix[i][k] && adjMatrix[k][j]) {
						adjMatrix[i][j] = true;
						adjMatrix[j][i] = true;
					}
				}
			}
		}
		
		// 1로 시작해서 연결되어있는 놈의 개수 찾기 
		int ans = 0;
		// 1은 시작이니깐 연결되어있는 지 확인은 2부터
		for (int i = 2; i <= N; i++) {
			// 1->i 또는 i->1 경로 있는 지 확인
			if (adjMatrix[1][i] || adjMatrix[i][1]) {
				ans++;
			}
		}

		sb.append(ans);
		System.out.println(sb);
	}
}
