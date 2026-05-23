import java.util.Scanner;
public class Main10810 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] arr = new int[N];
        for (int a = 0; a <  M; a++){
            int i = sc.nextInt();
            int j = sc.nextInt();
            int k = sc.nextInt();
            for (i = i-1; i < j; i++){
                arr[i] = k;
            }
        }

        for (int i = 0; i < N; i++){
            System.out.print(arr[i] + " ");
        }
    }
}
