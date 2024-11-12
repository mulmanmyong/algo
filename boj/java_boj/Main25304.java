import java.util.Scanner;
public class Main25304 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        int n = sc.nextInt();
        int result = 0;
        for (int i = 0 ; i < n; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            result += a * b;
        }
        if (x == result){
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }
    }
}
