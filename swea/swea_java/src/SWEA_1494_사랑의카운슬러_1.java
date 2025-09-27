import java.io.*;
import java.util.*;

public class SWEA_1494_사랑의카운슬러_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [설명]
 * 오훈이에게 지렁이 친구 N마리 있음. 지렁이들을 위해 소개팅 주선
 * 
 * 임의의 지렁이 두 마리 매칭시킨 후 한 지렁이에게 다른 지렁이가 있는 곳으로 가도록 하는 것
 * 움직인 벡터 합의 크기가 작기를 바람
 * 
 * 벡터 V 는 x*x + y*y 로 계산
 * 
 * [입력]
 * 첫 번째 줄 테스트 케이스 T
 * 	각 테스트 케이스의 첫 번째 줄에 정수 N
 * 	두 번째 줄 N개의 줄에는 지렁이들이 존재하는 점의 좌표 주어짐
 * 
 * [출력]
 * 지렁이의 움직인 벡터의 합의 크기의 최솟값
 * 
 * [흠]
 * N은 짝수만 주어지니깐
 * 이를 그룹1 그룹2로 나눌 수 있고
 * 벡터의 합의 크기를 반환하는 것이기에 
 * 벡터의 합의 크기는 그룹1에서 그룹2로 가든 그룹2에서 그룹1로 가든 크기는 같은 값이 나온다
 * 
 * 그래서 2개의 그룹으로 나누어 각 벡터의 합을 구하는 것이다
 * 벡터의 합은 각 그룹의 x좌표는 x좌표끼리, y좌표는 y좌표끼리 더하면 됨
 * 그리고 이제 그룹1에서 그룹2로 가는 경우의 벡터는
 * (그룹2의 x좌표 - 그룹1의 x좌표, 그룹2의 y좌표 - 그룹1의 y좌표)가 된다
 * 여기서 크기가 그 x의 제곱 + y의 제곱이 된다. 이 최소를 구하면 됨
 * 
 * 조합으로 N개 중 N/2를 고르는 경우를 보고, N/2를 구하면 벡터의 크기를 구하는 방식으로 진행
 */
	
	// 좌표 클래스
	static class Point {
		long x, y;

		public Point(long x, long y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	// 정수 N
	static int N;
	// 지렁이들
	static Point[] worms;
	
	// 그룹나눌 배열
	static boolean[] group;
	// 최소값 정답
	static long ans;
	
	public static long calculate() {
		// 각 그룹의 합 x y 구하기 
		Point sum1 = new Point(0, 0);
		Point sum2 = new Point(0, 0);
		
		// 그룹 1과 2의 합 구하기
		for (int i = 0; i < N; i++) {
			// true면 group 1
			if (group[i]) {
				sum1.x += worms[i].x;
				sum1.y += worms[i].y;
			}
			// false group 2
			else {
				sum2.x += worms[i].x;
				sum2.y += worms[i].y;
			}
		}
		
		// 합을 구했으니 그룹1 -> 그룹2 벡터 구함
		Point vec = new Point(sum2.x-sum1.x, sum2.y-sum1.y);
		
		// 벡터의 크기 return
		return vec.x*vec.x + vec.y*vec.y;
	}

	public static void makeGroup(int index, int selectCount) {
		// 그룹만들어짐 각 그룹의 합 구하기
		if (selectCount == N/2) {
			ans = Math.min(ans, calculate());
			return;
		}
		
		// 만약에 끝까지 도달했으면 그냥 종료
		if (index == N) {
			return;
		}
		
		// 해당 지렁이를 그룹1로 (선택함)
		group[index] = true;
		makeGroup(index+1, selectCount+1);
		
		// 해당 지렁이를 그룹2로 (선택안함)
		group[index] = false;
		makeGroup(index+1, selectCount);
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {

			// 정수 N입력
			N = Integer.parseInt(br.readLine().trim());
			
			// 지렁이들의 좌표들 입력하기
			worms = new Point[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				worms[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			ans = Long.MAX_VALUE;
			group = new boolean[N];
			// 이제 이 좌표들을 기반으로 하며  N/2개 선택하며 그룹 만들기
			makeGroup(0, 0);
			
			sb.append('#').append(test_case).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}
}
