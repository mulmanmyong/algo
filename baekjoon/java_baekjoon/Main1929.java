import java.util.Scanner;

public class Main1929 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        boolean[] arr = new boolean[m+1];
        arr[0] = true;
        arr[1] = true;
        //0과 1은 소수에서 제외

        for (int i = 2; i <= (int)Math.sqrt(m); i++){
            for (int j = 2; j <= m / i; j++){
                if(arr[i*j] == true) {
                    continue;
                }
                else  {
                    arr[i*j] = true;
                }
            }
        }
        for(int i = n; i<= m; i++) {
            if(arr[i] == false) {
                System.out.println(i);
            }
        }
    }
}
