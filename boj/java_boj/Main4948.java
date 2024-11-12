import java.util.Scanner;

public class Main4948 {
    public static boolean[] prime = new boolean[123456*2 + 1];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);;

        get_prime();
        while (true){
            int n = sc.nextInt();
            if (n == 0){
                break;
            }

            int cnt = 0;
            for (int i = n+1; i <= 2*n; i++){
                if (!prime[i]){ //소수면 false이기 때문에 !prime하면 true일 때 동작, 즉, 소수일 때 카운트함
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
