import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main11659 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[] S = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++){
            S[i] = S[i-1] + Integer.parseInt(st.nextToken()); //각 인덱스까지의 누적합에 대한 것. S[1]부터 시작. S[0] = 0이므로 상관X
        }

        for (int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());

            sb.append(S[j] - S[i - 1]).append('\n');
            //1-3까지면 인덱스가 1 2 3의 수를 다 합친 것이다. 즉, S[3] - S[0]이어야 한다.
        }

        System.out.print(sb);
    }
}
