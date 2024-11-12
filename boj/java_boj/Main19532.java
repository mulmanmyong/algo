import java.util.Scanner;
public class Main19532 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int d = sc.nextInt();
        int e = sc.nextInt();
        int f = sc.nextInt();

        int x = 0;
        int y = 0;
        for (int i = -999; i <= 999; i++){
            for (int j = -999; j <= 999; j++){
                int r1 = a*i + b*j;
                int r2 = d*i + e*j;
                if (r1 == c && r2 == f){
                    x = i; y = j;
                }
            }
        }
        System.out.println(x + " " + y);
    }
}
