import java.io.*;
import java.util.*;

public class SWEA_3499_퍼펙트셔플_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [설명]
 * 퍼펙트셔플할것임 
 * N개의 카드가 있는 덱 주어지고, 이를 퍼펙트 셔프ㅜㄹ하면 어캐 될것인지
 * N이 홀수면 교대로 놓을 때 먼저 놓는 쪽에 한장이 더 들어감
 * 
 * [입력]
 * 첫 번째 줄에 테스트 케이스의 수 T 주어짐
 * 	각 테스트 케이스의 첫 번째 줄에는 자연수 N이 주어짐
 * 	덱에 카드가 놓인 순서대로 N개의 카드 이름이 공백으로 주어짐
 * 
 * [출력]
 * 퍼펙트 셔플한 이후의 결과 출력
 */
	
	static int N;
	static String[] card1;
	static String[] card2;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {

			N = Integer.parseInt(br.readLine().trim());
			// 짝수는
			if (N%2 == 0) {
				card1 = new String[N/2];
				card2 = new String[N/2];
			}
			// 홀수는
			else {
				card1 = new String[N/2+1];
				card2 = new String[N/2];
			}
			
			st = new StringTokenizer(br.readLine().trim());
			for (int i = 0; i < card1.length; i++) {
				card1[i] = st.nextToken();
			}
			for (int i = 0; i < card2.length; i++) {
				card2[i] = st.nextToken();
			}
			
			sb.append('#').append(test_case).append(' ');
			// 번갈아가면서 출력
			int idx1=0;
			int idx2=0;
			for (int i = 1; i <= N; i++) {
				// 홀수면 앞에 카드
				if (i % 2 != 0) {
					sb.append(card1[idx1++]).append(' ');
				}
				// 짝수면 뒤에 카드
				else {
					sb.append(card2[idx2++]).append(' ');
				}
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
