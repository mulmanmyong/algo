import java.util.Scanner;

public class Main13909 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int cnt = 0; //열려있는 개수 count
        for (int i = 1; i*i <= n; i++){
            cnt++;
        }
        System.out.println(cnt);
    }
}
