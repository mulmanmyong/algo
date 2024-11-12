import java.util.Scanner;

public class Main17103 {
    public static boolean[] prime = new boolean[1000001];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        get_prime();

        int t = sc.nextInt();
        for (int i = 0; i < t; i++){
            int cnt = 0;
            int n = sc.nextInt();
            for (int j = 2; j <= n/2; j++){
                if (!prime[j] && !prime[n - j]){
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }

    public static void get_prime() {
        // 0 과 1 은 소수가 아니므로 ture
        prime[0] = prime[1] = true;

        for(int i = 2; i <= Math.sqrt(prime.length); i++) {
            if(prime[i]) continue;
            for(int j = i * i; j < prime.length; j += i) {
                prime[j] = true;
            }
        }
    }
}
