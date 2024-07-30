import java.util.Scanner;

public class Main2580 {
    public static int[][] arr = new int[9][9];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        sudoku(0,0);
    }

    static void sudoku(int row, int col) {
        if (col == 9) {
            sudoku(row + 1, 0); //다음 행의 첫번째 열부터 시작
            return;
        }

        if (row == 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(arr[i][j] + " ");
                }
                sb.append('\n');
            }
            System.out.print(sb);
            System.exit(0); //재탐색의 위험에 의해 모두 다 채워지면, 출력 후 종료
        }

        if (arr[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (check(row, col, i)){
                    arr[row][col] = i; //중복 되는 값 없으니, 그 자리에 숫자 바꾸고, 다음 열로 이동
                    sudoku(row, col + 1);
                }
            }
            arr[row][col] = 0;
            return; //공백이 여러개 있을 경우, 공백이 많은 초반같은 경우에는 check에 의해 바로 넣어질 수 있지만, 추후에 제대로 값이 나오지 않게 되면,
                    //변경했던 것들을 다시 0으로 바꿔두고, 일단 패스를 하고 다시 진행을 해야할 수도 있기 때문에 0으로 바꾸는 과정이 존재
        }
        sudoku(row, col + 1);
    }

    static boolean check(int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (arr[row][i] == num) { //col check
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (arr[i][col] == num) { //row check
                return false;
            }
        }

        //3x3 check
        int new_row = (row / 3) * 3;
        int new_col = (col / 3) * 3;

        for (int i = new_row; i < new_row + 3; i++) {
            for (int j = new_col; j < new_col + 3; j++) {
                if (arr[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }
}
