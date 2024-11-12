import java.util.Scanner;
public class Main1978 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int cnt = 0;
        for (int i = 0; i < n; i++){
            int k = sc.nextInt();
            int check = 0;
            for (int j = 1; j <= k; j++){
                if (k % j == 0){
                    check++;
                }
            }
            if (check == 2){
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
