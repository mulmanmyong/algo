import java.util.Scanner;
import java.util.Arrays;
public class Main2581 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int M = sc.nextInt();
        int N = sc.nextInt();

        int[] arr = new int[N-M+1];
        int index = 0;
        for (int n = M; n <= N; n++){
            int check = 0;
            for (int j = 1; j <= n; j++){
                if (n % j == 0){
                    check++;
                }
            }
            if (check == 2){
                arr[index++] = n;
            }
        }
        int result = Arrays.stream(arr).sum();
        if (result == 0){
            System.out.println(-1);
        }
        else {
            System.out.println(result);
            System.out.println(arr[0]);
        }
    }
}
