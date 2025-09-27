import java.io.*;
import java.util.*;

public class BOJ_10811_바구니뒤집기_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
/*
 * 1~n 번허 매겨진 책이 일렬로 꽂혀있음
 * m번에 걸쳐 구간 선택 후, 그 구간의 책 순서 뒤집음 
 * 
 * i와 j주어지면 i번부터 j번까지 뒤집음
 * m번 반복후 어떤지 ㅎ출력
 * 
 * [입력]
 * 첫째 줄에 N 과 M 주어짐
 * 둘째 줄부터 M개의 줄에 i와 j 주어짐
 */
	
	// 책 N, 뒤집을 횟수 M
	static int N, M;
	// 책의 상태 저장
	static int[] books;
	
	public static void reverse(int st, int ed) {
		int[] tmp = new int[N+1];
		for (int i = st; i <= ed; i++) {
			tmp[i] = books[i];
		}
		
		// 뒤집어서 입력
		for (int i = ed; i >= st; i--) {
			books[st + ed-i] = tmp[i];
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// N과 M 입력
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 책의 상태 입력
		books = new int[N+1];
		for (int i = 0; i <= N; i++) {
			books[i] = i;
		}
		
		// M번 뒤집기
		for (int t = 1; t <= M; t++) {
			st = new StringTokenizer(br.readLine().trim());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			
			reverse(i, j);
		}
		
		// 결과 입력
		for (int i = 1; i <= N; i++) {
			sb.append(books[i]).append(' ');
		}
		System.out.println(sb);
	}
}
