import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14889 {
    public static int N;
    public static int[][] arr;
    public static int min = Integer.MAX_VALUE;
    public static boolean[] visit;

    static int start, link;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        visit = new boolean[N];

        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(min);
    }

    static void dfs(int index, int depth) {
        if (depth == N / 2) {
            diff();
            return;
        }

        for (int i = index; i < N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                dfs(i + 1, depth + 1);
                visit[i] = false;
            }
        }
    }

    static void diff() {
        start = 0;
        link = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                //위의 visit에서 true 반, false 반이기 때문에 이를 이용해서 start와 link 계산
                if (visit[i] == true && visit[j] == true) {
                    start += arr[i][j];
                    start += arr[j][i];
                } else if (visit[i] == false && visit[j] == false) {
                    link += arr[i][j];
                    link += arr[j][i];
                }
            }
        }

        int result = Math.abs(start - link);

        if (result == 0) { //0이면 최소가 0이기 때문에 0 출력하고 바로 종료
            System.out.println(result);
            System.exit(0);
        }

        min = Math.min(min, result);
    }
}
