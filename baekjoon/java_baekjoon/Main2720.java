import java.util.Scanner;
public class Main2720 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = 0, b = 0, c = 0, d = 0;

        int t = sc.nextInt();
        for (int i = 0; i < t; i++){
            int m = sc.nextInt();

            a = m / 25;
            m %= 25;

            b = m / 10;
            m %= 10;

            c = m / 5;
            m %= 5;

            d = m;

            System.out.println(a + " " + b + " " + c + " " + d + " ");
        }
    }
}
