import java.io.*;
import java.util.*;

public class BOJ_2239_스도쿠_1 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    /*
     * [섬멸]
     * 스도쿠를 풀 것임
     * 0으로 빈칸이 주어지면 스도쿠를 푸는 것임 
     * 
     * [입력]
     * 9개의 줄에 9개의 숫자로 보드가 입력됨. 아직 숫자가 채워지지 않은 칸에는 0이 주어짐
     * 
     * [출력]
     * 9개의 줄에 9개의 숫자로 답을 출력함. 답이 여러 개 있으면 그 중 사전식으로 앞서는 것을 출력
     * 즉, 81자리의 수가 제일 작은 경우를 출력
     * 
     * [어캄]
     * 재귀를 이용
     * 빈칸을 찾으면 그 위치에 숫자를 넣어보기
     * 그리고 그 넣은 위치가 속한 행, 열, 3x3박스 에 해당 숫자가 있는 지 판단
     * 판단해서 없으면 유효하고 이는 해당 위치에 그 숫자를 넣고 재귀 돌림
     */
    
    // 스도쿠 판
    static int[][] sudoku;

    public static boolean isValid(int row, int col, int num) {
        // 유효한지 확인
    	
        for (int i = 0; i < 9; i++) {
        	// 행과 열에서 num이 이미 있는지 확인
        	if (sudoku[row][i] == num || sudoku[i][col] == num) {
                // 있으면 유효하지 않음
        		return false;
            }
        }
        
        // 3x3 박스에서있는지 확인
        int boxStartRow = (row / 3) * 3;
        int boxStartCol = (col / 3) * 3;
        for (int i = boxStartRow; i < boxStartRow + 3; i++) {
            for (int j = boxStartCol; j < boxStartCol + 3; j++) {
            	// 있으면 유효하지 않음
                if (sudoku[i][j] == num) {
                    return false;
                }
            }
        }
        
        // 모든 조건을 통과했으면 합격 
        return true;
    }

    public static boolean solve() {
        
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
            	// 빈칸이면 
                if (sudoku[row][col] == 0) {
                    
                	// 1부터 9까지의 숫자 시도
                    for (int num = 1; num <= 9; num++) {
                    	
                    	// 여기에 이 숫자를 넣어도 됩니까
                        if (isValid(row, col, num)) {
                            // 됩니다.
                        	sudoku[row][col] = num;
                            
                            // 재귀 호출
                            if (solve()) {
                                return true;
                            }
                            
                            // 월복
                            sudoku[row][col] = 0;
                        }
                    }
                    
                    // 빈 칸을 채울 수 없으면 실패
                    return false;
                }
            }
        }
        
        // 모든 빈 칸을 채웠으면 성공
        return true;
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        // 초기 스도쿠 판떼기를 입력받음
        sudoku = new int[9][9];
        for (int row = 0; row < 9; row++) {
            String str = br.readLine().trim();
            for (int col = 0; col < 9; col++) {
                sudoku[row][col] = str.charAt(col)-'0';
            }
        }
        
        // 스도쿠 풀기
        solve();

        // 결과 출력
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                sb.append(sudoku[row][col]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
