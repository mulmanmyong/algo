import java.util.Scanner;
public class Main11022 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.printf("Case #%d: %d + %d = %d\n", i, a, b, a + b);
        }
    }
}
