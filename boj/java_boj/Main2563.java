import java.util.Scanner;
public class Main2563 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //이미 거쳐간 부분 1로 설정하고 그렇지 않은 부분은 0, 그러면 1로 된 부분을 모두 count또는 더하기
        int n = sc.nextInt();
        int[][] arr = new int[100][100];
        for (int a = 0; a < n; a++){
            int x = sc.nextInt();
            int y = sc.nextInt();
            for (int i = y; i < y + 10; i++){
                for (int j = x; j < x + 10; j++){
                    arr[i][j] = 1;
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < 100; i++){
            for (int j = 0; j < 100; j++){
                sum += arr[i][j];
            }
        }
        System.out.println(sum);
    }
}
