import java.util.Scanner;

public class Main1003 {
    //기존코드로 하면 에러가 발생, 시간초과, 그래서 다이나믹 프로그래밍을 이용
    static Integer[][] dp = new Integer[41][2];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        dp[0][0] = 1;
        dp[0][1] = 0;
        dp[1][0] = 0;
        dp[1][1] = 1;

        int t = sc.nextInt();
        for (int i = 0; i < t; i++){
            int n = sc.nextInt();
            fibo(n);
            sb.append(dp[n][0] + " " + dp[n][1] + "\n");
        }
        System.out.print(sb);
    }

    public static Integer[] fibo(int num){
        if (dp[num][0] == null || dp[num][1] == null){
            //0과 1의 출력이 없을때,
            dp[num][0] = fibo(num - 1)[0] + fibo(num - 2)[0];
            dp[num][1] = fibo(num - 1)[1] + fibo(num - 2)[1];
        }
        return dp[num]; //0과 1에 대한 값을 담고있는 dp값 return
    }
    /*static int cnt0;
    static int cnt1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int t = sc.nextInt();
        for (int i = 0; i < t; i++){
            int n = sc.nextInt();
            cnt0 = 0;
            cnt1 = 0;
            fibo(n);
            sb.append(cnt0).append(" ").append(cnt1).append('\n');
        }
        System.out.println(sb);
    }

    public static int fibo(int num) {
        if (num == 0){
            cnt0++;
            return 0;
        }
        else if (num == 1){
            cnt1++;
            return 1;
        }
        else {
            return fibo(num - 1) + fibo(num - 2);
        }
    }*/
}
