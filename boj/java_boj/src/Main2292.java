import java.util.Scanner;
public class Main2292 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int a = 1;
        int cnt = 1;
        //1 , 7 , 19 , 37 , 61
        while (true) {
            if (n <= a){
                break;
            }
            else {
                a = a + (cnt*6);
                cnt++;
            }
        }
        System.out.print(cnt);
    }
}
