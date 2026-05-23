import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main1247 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++){
            int N = sc.nextInt();
            BigInteger sum = new BigInteger("0");
            for (int j = 0; j < N; j++){
                sum = sum.add(sc.nextBigInteger());
            }
            sb.append(check(sum)).append('\n');
        }
        System.out.print(sb);
    }

    static String check (BigInteger sum) {
        BigInteger zero = BigInteger.ZERO;
        if (sum.compareTo(zero) ==1){
            return "+";
        }
        else if (sum.compareTo(zero) == -1){
            return "-";
        }
        else {
            return "0";
        }
    }
}
