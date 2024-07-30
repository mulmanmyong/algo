import java.util.Scanner;
public class Main11653 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int f = 2;
        while (n != 1){
            if (n % f == 0){
                System.out.println(f);
                n /= f;
            }
            else {
                f++;
            }
        }
    }
}
