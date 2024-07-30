import java.util.Scanner;
public class Main10811 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] arr = new int[N];
        int[] reverse = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i + 1;
            reverse[i] = i + 1;
        }

        for (int i = 0; i < M; i++) {
            int j = sc.nextInt();
            int k = sc.nextInt();
            int n = k;

            if (j == k){
                continue;
            }
            else{
                for (j = j - 1; j < k; j++) {
                    reverse[j] = arr[--n];
                }
            }
            for (int a = 0; a < N; a++){
                arr[a] = reverse[a];
            }
        }
        for (int a = 0; a < N; a++) {
            System.out.print(reverse[a] + " ");
        }
    }
}