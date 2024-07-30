import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2559 {
    public static int N, K;
    public static int[] arr;
    public static int[] new_arr;
    public static int index;
    public static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        index = N - K;
        new_arr = new int[index + 1];

        for (int i = 0; i <= index; i++) {
            for (int j = i; j < i + K; j++) {
                new_arr[i] += arr[j];
            }
            max = Math.max(max, new_arr[i]);
        }

        System.out.println(max);
    }
}
