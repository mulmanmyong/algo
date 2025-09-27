import java.io.*;
import java.util.*;

public class BOJ_20299_3대측정_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [설명]
 * 팀원 3명의 레이팅 합이 K 미만이면 VIP 클럽 가입 불가
 * 그리고 개개인의 레이팅도 L 이상이어야함 
 * 
 * [입ㄺ]
 * 첫째 줄에 정수 팀의 수 N, 팀 레이칭 최소 K, 개인 레이팅 최소 L
 * 둘째 줄부터 N개 줄에 거쳐 x1, x2, x3 입력됨
 * 
 * [추렭]
 * 첫째 줄에 VIP 클럽에 가입이 가능한 팀의 수 추렭
 * 둘째 줄에 VIP 회원들의 레이팅을 입력받은 순서대로 출ㄹ겨
 * 
 * []
 * 구현
 */
	// 팀의 수 N, 팀 레이칭 최소 K, 개인 레이팅 최소 L
	static int N, K, L;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 팀의 수 N, 팀 레이칭 최소 K, 개인 레이팅 최소 L 입력
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		// vip가 될 수 있는 지 판단하기
		ArrayList<Integer> vip = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int x3 = Integer.parseInt(st.nextToken());
			
			// 가입가능한지판단
			if (x1+x2+x3 >= K && x1 >= L && x2 >= L && x3 >= L) {
				// 가능
				vip.add(x1);
				vip.add(x2);
				vip.add(x3);
			}
		}
		
		sb.append((int) vip.size()/3).append('\n');
		for (int x : vip) {
			sb.append(x).append(' ');
		}
		System.out.println(sb);
	}
}
