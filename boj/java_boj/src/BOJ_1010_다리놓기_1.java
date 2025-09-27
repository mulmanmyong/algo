import java.io.*;
import java.util.*;

public class BOJ_1010_다리놓기_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	/*
	 * 동쪽과 서쪽으로 나누는 큰 일직선 모양의 강이 흐름
	 * 다리가 없어서 다리를 만들 거임 
	 * 
	 * 서쪽에는 N개의 사이트가 있고, 동쪽에는 M개의 사이트가 있음 (N <= M)
	 * 서쪽의 개수만큼 다리를 지을 건데, 다리 끼리 겹칠 수 없음.
	 * 다리를 지을 수 있는 경우의 수를 구하는 프로그램을 작성하기 
	 * 
	 * 파스칼의 삼각형 -> 이항계수를 이용해서 구할 수 있음
	 * 동적 계획법을 적용하여 이항계수 계산
	 */

	static int N, M;

	public static int bino(int n, int k) {
		// 이항계수 저장 배열
		int[][] B = new int[n + 1][k + 1];

		// 파스칼 삼각형을 이용한 DP 방식 계산
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= Math.min(i, k); j++) {
				if (j == 0 || j == i) {
					B[i][j] = 1;
				} 
				else {
					B[i][j] = B[i - 1][j - 1] + B[i - 1][j];
				}
			}
		}
		
		return B[n][k];
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken()); // 서쪽 사이트 수
			M = Integer.parseInt(st.nextToken()); // 동쪽 사이트 수

			// M개 중 N개를 선택하는 조합을 계산
			int result = bino(M, N);
			sb.append(result).append('\n');
		}

		System.out.println(sb);
	}
}
