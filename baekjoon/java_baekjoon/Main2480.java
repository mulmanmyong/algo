import java.util.Scanner;
public class Main2480 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();

        if (a == b && b == c){
            System.out.println(1000*a + 10000);
        }
        else if (a == b && b != c){
            System.out.println(100*a + 1000);
        }
        else if (b == c && c != a){
            System.out.println(100*b + 1000);
        }
        else if (a == c && a != b){
            System.out.println(100*c + 1000);
        }
        else {
            int max;

            if (a > b) {
                if (c > a) {
                    max = c;
                }
                else {
                    max = a;
                }
            }
            else {
                if (c > b) {
                    max = c;
                }
                else {
                    max = b;
                }
            }
            System.out.println(max * 100);
        }
    }
}
