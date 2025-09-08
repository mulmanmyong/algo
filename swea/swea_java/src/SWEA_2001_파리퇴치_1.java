import java.io.*;
import java.util.*;

public class SWEA_2001_파리퇴치_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * 완탐으로 풀어보기 
 */
	
	// 배열의 크기 N, 파리채의 크기 M
	static int N, M;
	// 초기 맵
	static int[][] initMap;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			
			// 배열의 크기 N, 파리채의 크기 M 입력받기
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// 배열 입력받기
			initMap = new int[N][N];
			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < N; col++) {
					initMap[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 최대 합을 저장할 변수
			int maxSum = 0;
			// 완전탐색하기
			// 0 ~ N-M까지 -> 파리채의 크기가 M이니깐
			for (int startRow= 0; startRow <= N-M; startRow++) {
				for (int startCol = 0; startCol <= N-M; startCol++) {
					
					// 시작 위치를 기반으로 하여 MxM 크기의 구역 합 구하기
					int currentSum = 0;
					for (int row = startRow; row < startRow + M; row++) {
						for (int col = startCol; col < startCol + M; col++) {
							currentSum += initMap[row][col];
						}
					}
					
					// 최대 갱신하기
					maxSum = Math.max(maxSum, currentSum)
;				}
			}
			
			// 정답 입력 
			sb.append('#').append(test_case).append(' ').append(maxSum).append('\n');
		}
		System.out.println(sb);
	}
}
