import java.util.Scanner;

public class Main1934 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int a;
        int b;
        for (int i = 0; i < n; i++){
            a = sc.nextInt();
            b = sc.nextInt();
            if (a > b){
                int j = 1;
                while (true){
                    if (a*j % b == 0){
                        System.out.println(a*j);
                        break;
                    }
                    else {
                        j++;
                    }
                }
            }
            else if (a < b){
                int j = 1;
                while (true){
                    if (b*j % a == 0){
                        System.out.println(b*j);
                        break;
                    }
                    else {
                        j++;
                    }
                }
            }
            else if (a == b){
                System.out.println(a);
            }
        }
    }
}
