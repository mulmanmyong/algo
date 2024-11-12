import java.util.Scanner;

public class Main1904 {
    //00만 가능
    public static int dp[] = new int[1000001]; //N 최대
    public static int N;
    public static int cnt;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        /*
         N = 1 : 1
         N = 2 : 00 11
         N = 3 : 100 001 111
         N = 4 : 0000 0011 1001 1100 1111
         N = 5 : 00001 00100 10000 00111 11001 10011 11100 11111
            ....
        점화식 T(n) = T(n-1) + T(n-2) n >= 3

        */
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        //초기값
        for (int i = 3; i < dp.length; i++) {
            dp[i] = -1;
        }

        System.out.println(zero_one(N));


    }

    static int zero_one(int n) {
        if (dp[n] == -1) {
            dp[n] = (zero_one(n-1) + zero_one(n - 2)) % 15746;
        }

        return dp[n];
    }
}
