import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1012 {
    static int M;
    static int N;
    static int K;
    static int[][] farm;
    static boolean[][] check;
    static int[] dx = { 0, -1, 0, 1 };
    static int[] dy = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++){
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            int cnt = 0;

            farm = new int[M][N];
            check = new boolean[M][N];
            for (int j = 0; j < K; j++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                farm[a][b] = 1; //해당 위치에 배추가 심어져 있다.
            }

            for (int x = 0; x < M; x++){
                for (int y = 0; y < N; y++){
                    if (farm[x][y] == 1 && !check[x][y]){
                        dfs(x, y);
                        cnt++;
                    }
                }
            }
            sb.append(cnt).append('\n');
        }
        System.out.print(sb);
    }

    static void dfs(int x, int y){
        check[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int cx = x + dx[i];
            int cy = y + dy[i];

            if (cx >= 0 && cy >= 0 && cx < M && cy < N) {
                if (!check[cx][cy] && farm[cx][cy] == 1) {
                    dfs(cx, cy);
                }
            }
        }
    }
}
