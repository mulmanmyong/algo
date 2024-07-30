import java.util.Scanner;

public class Main1075 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int F = sc.nextInt();

        int tmp = (N/100)*100;
        while (true) {
            if (tmp % F == 0){
                break;
            }
            tmp++;
        }
        System.out.format("%02d", tmp%100);
    }
}
