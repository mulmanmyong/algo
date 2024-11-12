import java.util.Scanner;
public class Main24266 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextInt();
        /*3중 for문인데 반복횟수가 n^3회 반복, 즉, 시간복잡도는 n^3이며, 최고차항수는 3이다.*/
        System.out.println(n*n*n);
        System.out.println(3);
    }
}
