import java.io.*;
import java.util.*;

public class SWEA_5607_조합_1 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static final int DIV = 1234567891;
    static int N, R;

    // combination(n, r) 계산
    public static int combination(int n, int r) {
        if (r > n) return 0;
        if (r == 0 || r == n) return 1;

        // 분자
        long up = 1;
        // 분모
        long down = 1;

        // 분자와 분모 계산
        for (int i = 1; i <= r; i++) {
            up = (up * (n - i + 1)) % DIV;
            down = (down * i) % DIV;
        }

        // 분모의 MOD 역원 계산
        long inv = pow(down, DIV - 2);
        long result = (up * inv) % DIV;

        return (int) result;
    }

    // a^b % DIV를 거듭제곱의 분할 정복으로
    public static long pow(long a, long b) {
    	// 지수가 0이면 1 반환
    	if (b == 0) return 1;
        
    	// 절반 거듭제곱 계산
    	long half = pow(a, b / 2);
    	// 제곱 후 MOD 적용
        long result = (half * half) % DIV;
        // b가 홀수면 한 번 더 곱함
        if (b % 2 == 1) result = (result * a) % DIV;
        
        return result;
    }


    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1; test_case <= testCase; ++test_case) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            int ans = combination(N, R);

            sb.append('#').append(test_case).append(' ').append(ans).append('\n');
        }
        
        System.out.println(sb);
    }
}