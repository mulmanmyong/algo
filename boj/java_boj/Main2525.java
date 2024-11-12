import java.util.Scanner;
public class Main2525 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        int tmp = a * 60 + b + c;
        int H = tmp / 60;
        int M = tmp % 60;

        if (H > 23) {
            H -= 24;
        }
        System.out.println(H + " " + M);
    }
}