import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution2112 {
	
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 번호
	public static int thickness; // 필름의 두께 - row
	public static int width; // 필름의 가로 크기 - column
	public static int passCutline; // 통과 기준
	public static int[][] film;
	public static int[] A;
	public static int[] B;
	public static int minUseCount; // 최소 약품 사용량 
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스 번호가 주어짐
		testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			st = new StringTokenizer(br.readLine().trim());
			thickness = Integer.parseInt(st.nextToken());
			width = Integer.parseInt(st.nextToken());
			passCutline = Integer.parseInt(st.nextToken());
			
			// 각 셀들은 특성 A 또는 B를 가지고 있음 
			film = new int[thickness][width];
			for (int rowIndex = 0; rowIndex < thickness; rowIndex++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int columnIndex = 0; columnIndex < width; columnIndex++) {
					film[rowIndex][columnIndex] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 약품배열을 미리 만들어둬서, 참조해서 갈아끼는 형식으로 하기 
			// A 약품 
			A = new int[width];
			// B 약품 
			B = new int[width];
			for (int bIndex = 0; bIndex < width; bIndex++) B[bIndex] = 1;
			
			// 합격기준이상만큼 세로에 연속으로 있어야 함
			// 약품을 이용하면 한줄이 모두 그 특성으로 변경됨 
			// 검사를 하며 약품을 최소로 사용하여 합격기준의 약품사용횟수 찾기 
			// A가 0 B가 1
			// 모든 경우의 수를 탐색
			// 현재 행, 약품사용횟수 
			minUseCount = Integer.MAX_VALUE;
			findMinCount(0, 0);
			
			sb.append("#").append(test_case).append(" ").append(minUseCount).append("\n");
		}
		
		// 결과 출력
		System.out.println(sb);
	}
	
	public static void findMinCount(int row, int count) {
		
		// 현재까지의 개수가 이미 최소보다 크거나 같으면 패스
		// 불필요한 계산 제거
		if (count >= minUseCount) {
			return;
		}
		
		// 기저 조건 끝까지 탐색
		if (row == thickness) {
			if (passTest()) { // 현재 결과가 합격기준을 넘었다면
				minUseCount = Math.min(minUseCount, count); // 최소인지 판단 후 업데이트 
			}
			return;
		}
		
		// 원복을 위해 현재 상태 저장
//		int[] originalRow = new int[width];
//		for (int index = 0; index < width; index++) {
//			originalRow[index] = film[row][index];
//		}
		
		// 현재의 행에 약품을 투입하지 않음
		findMinCount(row + 1, count);
		int[] originalRow = film[row];
		
		// A약품은 0, B약품은 1
		// 현재의 행에 약품을 A약품 투입 
		// change(row, 0);
		film[row] = A;
		findMinCount(row + 1, count + 1);
		
		// 현재의 행에 약품을 B약품 투입
		// change(row, 1);
		// 카트리지를 미리 만들어서 해두니깐 훨 빠름. 360ms정도에서 190ms로 확 줄음 
		film[row] = B;
		findMinCount(row + 1, count + 1);
		
		// 원복
		film[row] = originalRow;
	}
	
	public static boolean passTest() {
		// 각 열에 대해 검사
		for (int column = 0; column < width; column++) {
            int sequenceCount = 1;
            boolean isPass = false;

            for (int row = 0; row < thickness - 1; row++) {
            	// 연속성 검사
                if (film[row][column] == film[row + 1][column]) {
                	// 다음열과 일치하면 연속!
                	sequenceCount++;
                } else {
                	// 다음열과 일치하지 않으면 초기화
                    sequenceCount = 1;
                }
                
                // 통과기준을 넘으면 통과했다고 체크 후 해당 열 검사 종료
                if (sequenceCount >= passCutline) {
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
	
//	public static void change(int row, int changeTo) {
//		// 해당 행을 약품을 써서 변경!
//		for (int changeIndex = 0; changeIndex < width; changeIndex++) {
//			film[row][changeIndex] = changeTo;
//		}
//	}
	
}
