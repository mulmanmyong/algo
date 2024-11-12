import java.util.Scanner;

public class Main9461 {

    public static long[] dp = new long[101]; //합하다보면 저장되는 값이 int의 최대를 넘어가게 되므로 long으로 사용

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;

        for (int i = 4; i < dp.length; i++) {
            dp[i] = -1;
        }

        int[] tmp = new int[N];
        for (int i = 0; i < N; i++) {
            int T = sc.nextInt();
            System.out.println(P(T));
        }
    }

    static long P(int N) {
        if (dp[N] == -1) {
            dp[N] = P(N - 2) + P(N - 3);
        }

        return dp[N];
    }
}
