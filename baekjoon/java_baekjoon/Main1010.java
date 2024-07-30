import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

//dynamic programmin에 분류가 되어있어도 그냥 무시하고 풀어봤는데, dp를 사용해야할 것 같다.
public class Main1010 {
    static int[][] dp = new int[30][30]; //dynamic programming을 통하여 중복이 안일어나도록
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < t; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            sb.append(Combination(m, n)).append('\n');
        }
        System.out.print(sb);
    }

    public static int Combination (int n, int k){
        if (dp[n][k] > 0){
            //0으로 초기화가 되어있으므로, 이미 거쳐간 곳은 값이 바뀌어서 0이 아니게 된다. 그래서 0보다 크게 되면 이미 거쳐간 곳이므로,
            //값을 반환
            return dp[n][k];
        }
        if (n == k || k == 0){
            return dp[n][k] = 1;
        }
        else {
            return dp[n][k] = Combination(n - 1, k - 1) + Combination(n - 1, k);
        }
    }
}
