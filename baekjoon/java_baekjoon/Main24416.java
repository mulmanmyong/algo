import java.util.Scanner;

public class Main24416 {
    public static int N;
    public static int cnt;
    public static int dpcnt;
    public static int[] dp;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        dp = new int[N];

        fib(N);
        fibonacci(N);

        System.out.println(cnt + " " + dpcnt);

    }

    static int fib(int n) {
        if (n == 1 || n == 2) {
            cnt++;
            return 1;  //# 코드1
        }
        else
            return (fib(n - 1) + fib(n - 2));
    }

    static int fibonacci(int n) {
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            dpcnt++; //코드2
        }
        return dp[n - 1];
    }
}
