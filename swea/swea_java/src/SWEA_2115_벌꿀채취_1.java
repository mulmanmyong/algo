import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2115_벌꿀채취_1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

/*
 * NxN개의 벌통이 정사각형 모양으로 배치되어 있음
 * 각 칸의 숫자는 각각의 벌통에 있는 꿀의 양을 나타냄, 꿀의 양은 서로 다를 수 있음 
 * 
 * [채취 과정]
 * 1. 두 명의 일꾼이 있음. 벌통의 수 M이 주어짐 
 *    각각의 일꾼은 가로로 연속되도록 M개의 벌통 선택 , 선택할 벌통에서 꿀을 채취
 *    단, 두명의 일꾼이 선택한 벌통은 서로 겹치면 안됨 
 *    
 * 2. 두 명의 일꾼은 선택한 벌통에서 꿀을 채취하여 용기에 담아야 함
 *    단, 서로 다른 벌통에서 채취한 꿀이 섞이게 되면 상품가치 떨어지게 되므로, 하나의 벌통에서 채취한 꿀은 하나의 용기에 담아야 함
 *    하나의 벌통에서 꿀을 채취할 때, 일부분 채취 불가능하고 모든 꿀을 한번에 채취
 *    두 일꾼이 채취할 수 있는 꿀의 최대 양은 각각 C
 *    
 * 3. 채취한 꿀은 시장에 팔림. 하나의 용기에 있는 꿀의 양이 많을수록 상품가치가 높음. 각 용기에 있는 꿀의 양의 제곱만큼의 수익이 생김 
 * 		ex) 6, 1, 8 을 채취했으면 6*6 + 1*1 + 8*8 이 됨 
 * 
 * 
 * [입력]
 * 테스트 케이스 입력
 * 	벌통들의 크기 N, 선택할 수 있는 벌통의 개수 M, 꿀을 채취할 수 있는 최대 양 C 
 * 	꿀의 양 정보 입력
 * 
 * [출력]
 * 벌꿀 최대 수익 
 */
	
	// 테스트 케이스 입력
	static int testCase;
	// 벌통들의 크기 N, 선택할 수 있는 벌통의 개수 M, 꿀을 채취할 수 있는 최대 양 C
	static int N, M, C;
	// 꿀의 양 정보 입력
	static int[][] honey;
	// 최대 수익
	static int maxMoney;
	static int currentMaxMoney;
	
	public static int maxHoney(int row, int col) {
	    currentMaxMoney = 0;
	    powerset(row, col, 0, 0, 0); 
	    return currentMaxMoney;
	}

	public static void powerset(int row, int col, int cnt, int sum, int profit) {
		// 용량 초과하면 컷
		if (sum > C) return;
	    // 끝까지 도달하면 갱신 후 return
	    if (cnt == M) {
	        currentMaxMoney = Math.max(currentMaxMoney, profit);
	        return;
	    }
	    
	    // 선택하는경우
	    powerset(row, col+1, cnt+1, sum + honey[row][col], profit + honey[row][col] * honey[row][col]);
	    // 선택안하는경우
	    powerset(row, col+1, cnt+1, sum, profit);
	}
	
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			// 꿀의 정보들을 입력
			honey = new int[N][N];
			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < N; col++) {
					honey[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			maxMoney = -1;
			// 일꾼은 2명임, 겹치지 않음. -> 이 방향으로 꿀을 채취, 
			
			for (int row1 = 0; row1 < N; row1++) {
				// 채취가능한 상태의 시작인 N-M까지 시작 열을 선택
				for (int col1 = 0; col1 <= N-M; col1++) {
					
					// 각각의 최대 수확
					int m1 = 0;
					int m2 = 0;
					
					// 해당 위치에서 시작해서 M까지의 벌꿀들 중에서 최대 수확 확인
					m1 = maxHoney(row1, col1);
					
					// 같은 행을 기점으로 탐색한 열 다음을 확인하기 어차피 불가능하면 다음 행을 가게 되어있다
					for (int row2 = row1; row2 < N; row2++) {
						for (int col2 = 0; col2 <= N-M; col2++) {
							
							// 해당위치에서 최대 벌꿀 수확가능 위치 
							// 같은 행일 때 열이 겹치면 안됨 그러면 패스, 즉, 시작이 col1의 시작위치부터 수확한거 보다 커야함
							if (row1 == row2 && (col1+M > col2)) continue;
							
							// 그렇지 않다면 해당 위치의 최대 수확해보기
							m2 = maxHoney(row2, col2);
							
							// 최대 갱신하기
							maxMoney = Math.max(maxMoney, m1+m2);
						}
					}
				}
			}
			
			sb.append('#').append(test_case).append(' ').append(maxMoney).append('\n');
		}
		System.out.println(sb);
	}
}
