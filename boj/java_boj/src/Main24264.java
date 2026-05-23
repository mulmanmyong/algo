import java.util.Scanner;
public class Main24264 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextInt();
        /*2중 for문인데 둘 다 n*n회 반복, 즉, 시간복잡도는 n^2이며, 최고차항수는 2이다.*/
        System.out.println(n*n);
        System.out.println(2);
    }
}
