import java.util.Scanner;

public class Main10870 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        System.out.println(Fibo(n));
    }

    public static long Fibo(int num){
        if (num == 0){
            return 0;
        }
        if (num == 1){
            return 1;
        }
        return Fibo(num - 1) + Fibo(num - 2);
    }
}
