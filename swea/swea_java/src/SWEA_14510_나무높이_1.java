import java.io.*;
import java.util.*;

public class SWEA_14510_나무높이_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [문제설명]
 * N개의 나무 있음. 초기 각 나무의 키 주어짐
 * 하루에 한 나무 줄 수 있음
 * 홀수 날에 물을 준 나무는 1자람, 짝수날에 물을 준 나무는 2자람
 * 처음에 키가 가장컸던 나무와 같아지도록 할 수 있는 최소 날짜 계산
 * 물을 안 줄 수도 있음
 * 
 * [입력]
 * 첫째 줄에 테스트 케이스 T줌
 * 	각 테스트 케이스 첫째 줄에 나무의 개수 N 주어짐
 * 	각 테스트 케이스 둘째 줄에 각 나무의 키들 주어짐
 * 
 * [출력]
 * 처음에 키가 가장컸던 나무와 같아지도록 할 수 있는 최소 날짜를 출력
 * 
 * [어찌할까]
 * 그리디 방식
 * 입력받을때 가장 큰 나무를 찾음
 * 그 키를 뺌으로써 각 나무마다 자라야하는 키를 구하기
 * 그것을 기준으로 2자라는 날, 1자라는 날 계산
 * 2로 나누었을 때 나누어 떨어지면 짝수 날에 물 주기
 * 1일이 남으면 그것은 홀수날에 주고
 * 
 * 그러면 비대칭이 될것 2씩 자라는 날은 1씩 자라나는 2일로 바꿀 수 있기 때문에
 * 이를 바꿀 수 있는 만큼 바꾸기
 * 
 */
	
	// 나무의 개수 N
	static int N;
	// 나무들
	static int[] trees;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {

			// 나무의 개수 N을 입력받음
			N = Integer.parseInt(br.readLine().trim());
			
			// 최초가장 높은 놈
			int highest = 0;
			// 나무들을 입력받음
			trees = new int[N];
			st = new StringTokenizer(br.readLine().trim());
			for (int i = 0; i < N; i++) {
				trees[i] = Integer.parseInt(st.nextToken());
				highest = Math.max(highest, trees[i]);
			}
			
			// 자라나야하는 길이로 변경
			for (int i = 0; i < N; i++) {
				trees[i] = highest-trees[i];
			}
			
			// 1씩 자라야하는 지 2씩 자라야하는 지 판별
			// 2로 나누었을 때 나누어지는 몫만큼 짝수날에 주기
			// 나머지 1은 홀수날에 주기
			int oddDay = 0;
			int evenDay = 0;
			for (int i = 0; i < N; i++) {
				oddDay += (trees[i] % 2);
				evenDay += (trees[i] / 2);
			}
			
			// 비대칭일 껀데
			// 2씩 자라는 날을 1씩 자라는 날 2일로 바꿀 수 있음!
			// 1씩 2일 2씩 3일
			// 일 경우에는 안되지만
			// 1씩 2일 2씩 4일이면
			// 기존방식대로 하면 8일이지만,
			// 하루로 바꾸면
			// 1 2 1 2 0 2 0 2
			// 1 2 1 2 1 2 1로 바꿀수가 있어서 하루가 단축됨!!
			// 즉 oddDay < evenDay-1일 동안만 변경 가능한 것임
			while (oddDay < evenDay-1) {
				// 짝수날을 없애고
				evenDay--;
				// 홀수날을 2증가 시키고
				oddDay += 2;
			}
			
			// 이제 날짜를 계산
			int ans = 0;
			// 균형은 맞춰졌으니 
			// 홀수 날이 많으면 홀수날*2-1을 해줘야함
			if (oddDay > evenDay) {
				ans = 2*oddDay - 1;
			}
			// 짝수날이 같너가 많으면 어차피 2일을 다 채워야한다는 거니깐 2*짝수날을 해야함
			else {
				ans = 2*evenDay;
			}
			
			sb.append('#').append(test_case).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}
}
