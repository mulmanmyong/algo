import java.util.Scanner;
public class Main2745 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.next();
        int N = sc.nextInt();
        int tmp = 1;
        int sum = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);

            if('A'<= c && c <= 'Z') {
                sum += (c - 'A' + 10) * tmp;
            }else {
                sum += (c - '0' ) * tmp;
            }
            tmp *= N;
        }
        System.out.println(sum);
    }
}
