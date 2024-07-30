import java.util.Scanner;

public class Main11050 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();
        System.out.println(Combination(n, k));

    }

    public static int Combination (int n, int k){
        if (n == k || k == 0){
            return 1;
        }
        else {
            return Combination(n - 1, k - 1) + Combination(n - 1, k);
        }
    }
}
