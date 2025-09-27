import java.io.*;
import java.util.*;

public class BOJ_2655_가장높은탑쌓기_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [문제설명]
 * 밑면이 정사각형인 직육면체 벽돌들을 사용하여 탑을 쌓고자 함
 * 탑은 벽돌을 한 개씩 아래에서 위로 쌓으면서 만들어 감
 * 
 * 아래의 조건을 만족하면서 가장 높은 탑을 쌓을 수 있는 프로그램을 작성하기
 * 	1. 벽돌은 회전시킬 수 없음. 즉, 옆면을 밑면으로 사용할 수 없음
 * 	2. 밑면의 넓이가 같은 벽돌은 없으며, 또한 무게가 같은 벽돌도 없음
 * 	3. 벽돌들의 높이는 같을 수도 있음
 * 	4. 탑을 쌓을 때 밑면이 좁은 벽돌 위에 밑면이 넓은 벽돌은 놓을 수 없음
 * 	5. 무게가 무거운 벽돌을 무게가 가벼운 벽돌 위에 놓을 수 없음 
 * 
 * [입력]
 * 입력될 벽돌의 수 주어짐 (최대 100개)
 * 둘째 줄부터 각 줄에 한 개의 벽돌에 관한 정보인 벽돌 밑면의 넓이, 벽돌의 높이, 무게가 차례대로 양의 정수
 * 
 * 벽돌은 입력되는 순서대로 1부터 연속적인 번호를 가짐
 * 
 * [출력]
 * 탑을 쌓을 때 사용된 벽돌의 수 출력
 * 두 번째 줄 부터 가장 위 벽돌 부터 아래까지 차례대로 한줄에 하나씩 출력
 * 
 * [어찌 풀까]
 * dp를 이용할 건데, 현재의 벽돌번호를 기준으로 dp를 하는데 저장하는 값은 최대 높이
 * 밑면이 넓을 수록 아래로, 무거울수록 아래로
 * 
 * block 클래스를 만들어서, 벽돌번호, 밑면, 높이, 무게 저장하기 
 * 그 다음에 제일 밑에 쌓는 경우가 제일 밑면이 넓어야 하니깐 밑면 기준 오름차순으로 정렬하기
 * 
 * 그 후 무게를 비교하며 현재 쌓으려는 놈이 밑에 있는 놈보다 무거운지 판단하기 
 * 그후 무거운 놈부터 시작하여 스택에 넣어야하니깐.. 
 * 내림차순이 아니라 오름차순으로 하고, 현재 쌓으려는 놈이 밑에 밑에 있는 놈보다 무거운지 봐야하네
 * 
 * 이후 스택을 이용해서 무거운 놈부터 dp[인덱스] 최대 높이와 일치하는 놈들만 스택에 담기 -> 이러면 스택 제일 하단은 무거운 놈일 것
 * 그리고 쌓은 벽돌의 수는 스택의 크기 일 것
 */
	
	// 입력될 벽돌의 수 N
	static int N;
	
	// 벽돌의 정보를 저장할 클래스
	static class Block implements Comparable<Block>{
		// 벽돌번호, 밑면너비, 높이, 무게 저장하기 
		int n, b, h, w;

		public Block(int n, int b, int h, int w) {
			super();
			this.n = n;
			this.b = b;
			this.h = h;
			this.w = w;
		}

		@Override
		public int compareTo(Block o) {
			return this.b - o.b;
		} 
	}
	
	// 벽돌들의 정보를 저장할 배열
	static Block[] block;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 입력받을 벽돌의 개수를 입력받기 
		N = Integer.parseInt(br.readLine().trim());
		
		// 벽돌들의 정보를 입력받기
		block = new Block[N+1];
		block[0] = new Block(0, 0, 0, 0);
		for (int n = 1; n <= N; n++) {
			st = new StringTokenizer(br.readLine().trim());
			int b = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			block[n] = new Block(n, b, h, w);
		}
		
		// 블럭들의 정보를 밑면너비(b) 기준 오름차순 정렬
		Arrays.sort(block);
		
		// 최대 높이를 저장할 dp
		int[] dp = new int[N+1];
		// 최대 높이를 저장할 변수
		int max = -1;
		
		// 블럭의 정보들을 탐색
		// i 번째 블럭을 탐색 
		for (int i = 1; i <= N; i++) {
			// 일단 자신만을 쌓음
			dp[i] = block[i].h;
			
			// i번째 이전의 블럭까지 탐색 (0번째 블록은 없음!)
			for (int j = 1; j < i; j++) {
				// i 번째 이전에 오는 블록은 너비가 작고 무게도 가벼움
				// j 번째 블록이 i번째 블록보다 가벼운가?
				// 가벼운 놈만 쌓아져올 것임
				if (block[j].w < block[i].w) {
					// 가벼우면
					// 1. 현재의 상태와
					// 2. 현재의 높이 + 이전까지의 값을 더한 값
					// 최대 보기
					dp[i] = Math.max(dp[i], dp[j] + block[i].h);
				}
			}
			// i번째 블록까지 쌓아서 내려왔을 때 이게 최대 높이인가?
			max = Math.max(max, dp[i]);
		}
		
		// 블록을 N까지 도전을 해봤을 때 최대 높이를 찾았음
		// 그러면 이제 스택에 무거운 놈 부터 담아보기
		Stack<Integer> st = new Stack<>();
		// N이 1이 될 때까지 감소 하며 판단
		while (N > 0) {
			// dp[N] 까지의 높이가 최대 높이이면?
			if (max == dp[N]) {
				// 해당 블럭이 최대 높이까지에 포함이 된 것 (높이가 같은 것이 없기 때문ㅇ)
				// 해당 번호를 스택에 넣기 (제일 아래에 깔린다)
				st.add(block[N].n);
				// 그리고 해당 블럭을 제외했을 때 최대 높이로 갱신
				max -= block[N].h;
			}
			// N은 계속 감소, 최대 높이에 포함되는 블럭이면 쌓을 것이고, 아니면 안쌓고 패스하고 형식
			N--;
		}
		
		// 스택의 크기가 블럭의 개수
		sb.append(st.size()).append('\n');
		// 스택이 빌 때 까지 담아줌
		while (!st.isEmpty()) {
			sb.append(st.pop()).append('\n');
		}
		System.out.print(sb);
	}
}
