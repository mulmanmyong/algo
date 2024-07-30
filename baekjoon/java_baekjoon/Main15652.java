import java.util.Scanner;

public class Main15652 {
    public static int[] arr;
    public static int N, M;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[M];

        dfs(1, 0);
        System.out.print(sb);
    }

    static void dfs(int start, int depth) {
        if (M == depth) {
            for (int val : arr) {
                sb.append(val + " ");
            }
            sb.append('\n');
            return;
        }

        for (int i = start; i <= N; i++) {
            arr[depth] = i;
            dfs(i, depth + 1);
        }
    }
}
