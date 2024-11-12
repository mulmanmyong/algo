import java.util.Scanner;

public class Main1110 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cnt = 0;
        int N = sc.nextInt();
        int tmp = N;
        while (true) {
            N = ((N % 10) * 10) + (((N / 10) + (N % 10)) % 10);
            cnt++;

            if (tmp == N){
                break;
            }
        }
        System.out.print(cnt);
    }
}
