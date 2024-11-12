import java.util.Scanner;
import java.util.Arrays;
public class Main10818 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++){
            arr[i] = sc.nextInt();
        }

        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();

        System.out.println(min + " " + max);
    }
}
