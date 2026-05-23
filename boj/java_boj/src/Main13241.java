import java.util.Scanner;

public class Main13241 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long a = sc.nextLong();
        long b = sc.nextLong();

        if (a > b) {
            int j = 1;
            while (true) {
                if (a * j % b == 0) {
                    System.out.println(a * j);
                    break;
                } else {
                    j++;
                }
            }
        } else if (a < b) {
            int j = 1;
            while (true) {
                if (b * j % a == 0) {
                    System.out.println(b * j);
                    break;
                } else {
                    j++;
                }
            }
        } else if (a == b) {
            System.out.println(a);
        }
    }
}
