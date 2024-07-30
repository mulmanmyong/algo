import java.util.Scanner;
public class Main2566 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] arr = new int[9][9];
        int max = arr[0][0];
        int col = 1, row = 1;
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                arr[i][j] = sc.nextInt();
                if (arr[i][j] > max){
                    max = arr[i][j];
                    row = i + 1;
                    col = j + 1;
                }
            }
        }
        System.out.println(max);
        System.out.print(row + " " + col);
    }
}
