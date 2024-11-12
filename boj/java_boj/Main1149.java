import java.util.Scanner;

public class Main1149 {

    final static int red = 0;
    final static int green = 1;
    final static int blue = 2;
    static int dp[][];
    static int cost[][];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); //number of house

        cost = new int[N][3]; //cost
        dp = new int[N][3]; //dp

        for (int i = 0; i < N; i++) {
            cost[i][red] = sc.nextInt();
            cost[i][green] = sc.nextInt();
            cost[i][blue] = sc.nextInt();
        } //각 최소값 찾기

        dp[0][red] = cost[0][red];
        dp[0][green] = cost[0][green];
        dp[0][blue] = cost[0][blue];

        System.out.print(Math.min(paint(N - 1, red), Math.min(paint(N - 1, green), paint(N - 1, blue))));
    }

    static int paint(int N, int color) {

        if (dp[N][color] == 0) {
            if (color == red) {
                dp[N][red] = Math.min(paint(N - 1, green), paint(N - 1, blue)) + cost[N][red];
            }
            else if (color == green) {
                dp[N][green] = Math.min(paint(N - 1, red), paint(N - 1, blue)) + cost[N][green];
            }
            else {
                dp[N][blue] = Math.min(paint(N - 1, red), paint(N - 1, green)) + cost[N][blue];
            }
        }

        return dp[N][color];
    }
}
