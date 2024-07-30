import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main1252 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String s1 = st.nextToken();
        String s2 = st.nextToken();

        //2의 80승은 long과 int의 범위를 넘어선다.
        BigInteger b1 = new BigInteger(s1, 2);
        BigInteger b2 = new BigInteger(s2, 2);
        //2는 radix를 의미, 즉 2진을 의미한다.

        BigInteger sum = b1.add(b2);
        String ans = sum.toString(2); //2진수ㄹ 변환
        System.out.print(ans);
    }
}
