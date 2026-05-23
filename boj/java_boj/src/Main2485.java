import java.util.Arrays;
import java.util.Scanner;

public class Main2485 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr); //가로수의 순서가 뒤죽박죽일 수도 있으니
        int min = 0;
        for (int i = 0; i < n - 1; i++){
            int distance = arr[i+1] - arr[i];
            min = Euclidean(distance, min);
        }
        System.out.println((arr[n-1] - arr[0])/min + 1 - n);

    }

    public static int Euclidean(int a, int b) {
        if (b == 0)
            return a;
        return Euclidean(b, a % b);
    }
}
