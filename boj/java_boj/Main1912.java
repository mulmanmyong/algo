import java.util.Scanner;

public class Main1912 {

    static int[] arr;
    static Integer[] dp;
    static int max;


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        arr = new int[N];
        dp = new Integer[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        dp[0] = arr[0];
        max = arr[0];

        recursive(N - 1);

        System.out.println(max);
    }

    static int recursive(int N) {
        if (dp[N] == null) {
            dp[N] = Math.max(arr[N] + recursive(N - 1), arr[N]);
            //top-down 방식, 위에서 부터 연속된 걸 합하다가, 그냥 단품이 더 큰 값이면, 그 값으로 대체해서 다시 연산 진행하는 그런 탐색방식

            max = Math.max(max, dp[N]);
        }

        return dp[N];
    }
}
