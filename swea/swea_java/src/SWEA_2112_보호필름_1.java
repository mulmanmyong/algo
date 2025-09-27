import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2112_보호필름_1 {
	
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;

/*
 * 보호필름 제작
 * 같은 엷은 투명한 막을 D장 쌓아서 제작
 * 바 모양의 셀들이 가로 방향으로 W개 붙여서 만들어짐
 * 이렇게 만들어진 필름이 두께 D, 가로크기 W 보호 필름
 * 
 * 각 셀은 특성 A 또는 B 가지고 있음
 * 합격기준 K라는 값을 사용함
 *  -> 동일한 특성의 셀들이 K개 이상 연속적으로 있는 경우에만 성능 검사 통과
 *  
 *  성능 검사를 통과하기 위해 약품을 넣을 수 있는데 한 행에 같은 특성으로 약품을 투입할 수 있음 
 *  
 *  약품 투입 횟수를 최소로하여 성능검사를 통과할 수 있는 방법을 찾아라 
 *  
 *  [입력]
 *  보호필름의 두께 D, 가로크기 W, 합격기준 K
 *  D줄에 보호필름의 단면 주어짐. A는  0 B는 1
 *  
 *  [출력]
 *  성능 검사 통과하는 최소 약품 투입 횟수
 */
	
	static int D, W, K;
	static int[][] film;
	
	// A약품 투입
	static int[] A;
	// B약품 투입
	static int[] B;
	
	// 최소 약품 사용량 
	static int minUseCount; 
	
	public static void findMinCount(int row, int count) {
		
		// 현재까지의 개수가 이미 최소보다 크거나 같으면 패스
		// 불필요한 계산 제거
		if (count >= minUseCount) {
			return;
		}
		
		// 기저 조건 끝까지 탐색
		if (row == D) {
			if (passTest()) { // 현재 결과가 합격기준을 넘었다면
				minUseCount = Math.min(minUseCount, count); // 최소인지 판단 후 업데이트 
			}
			return;
		}
		
		// 원복을 위해 현재 상태 저장
		int[] originalRow = film[row];
        
		// 현재의 행에 약품을 투입하지 않음
		findMinCount(row + 1, count);
		
		// 현재의 행에 약품을 A약품 투입 
		film[row] = A;
		findMinCount(row + 1, count + 1);
		
		// 현재의 행에 약품을 B약품 투입
		film[row] = B;
		findMinCount(row + 1, count + 1);
		
		// 원복
		film[row] = originalRow;
	}
	
	public static boolean passTest() {
		// 각 열에 대해 검사
		for (int col = 0; col < W; col++) {
            int sequenceCount = 1;
            boolean isPass = false;

            for (int row = 0; row < D - 1; row++) {
            	// 연속성 검사
                if (film[row][col] == film[row + 1][col]) {
                	// 다음열과 일치하면 연속!
                	sequenceCount++;
                } else {
                	// 다음열과 일치하지 않으면 초기화
                    sequenceCount = 1;
                }
                
                // 통과기준을 넘으면 통과했다고 체크 후 해당 열 검사 종료
                if (sequenceCount >= K) {
                    isPass = true;
                    break;
                }
            }
            
            // 하나라도 통과 못하면
            if (!isPass) {
                return false;
            }
        }
		
		// 모든 열 합격!
        return true;
	} 
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			st = new StringTokenizer(br.readLine().trim());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			// 각 셀들은 특성 A 또는 B를 가지고 있음 
			film = new int[D][W];
			for (int row = 0; row < D; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < W; col++) {
					film[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 약품배열을 미리 만들어둬서, 참조해서 갈아끼는 형식으로 하기 
            // A약품은 0, B약품은 1
			// A 약품 
			A = new int[W];
			// B 약품 
			B = new int[W];
			for (int bIndex = 0; bIndex < W; bIndex++) B[bIndex] = 1;
			
			// 합격기준이상만큼 세로에 연속으로 있어야 함
			// 약품을 이용하면 한줄이 모두 그 특성으로 변경됨 
			// 검사를 하며 약품을 최소로 사용하여 합격기준의 약품사용횟수 찾기 
			// 모든 경우의 수를 탐색
			// 현재 행, 약품사용횟수 
			minUseCount = Integer.MAX_VALUE;
			findMinCount(0, 0);
			
			sb.append("#").append(test_case).append(" ").append(minUseCount).append("\n");
		}
		
		// 결과 출력
		System.out.println(sb);
	}
}
