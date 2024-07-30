import java.util.Scanner;

public class Main27433 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        System.out.println(fact(n));
    }

    public static long fact(int num){
        if (num == 0 || num == 1) {
            return 1;
        }
        else {
            return num * fact(num-1);
        }
    }
}
