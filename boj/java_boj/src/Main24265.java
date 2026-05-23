import java.util.Scanner;
public class Main24265 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long n = sc.nextInt();
        /*2중 for문인데 반복횟수는 계산을 해보면 (n(n-1))/2이다. 즉, 시간복잡도는 n^2이며, 최고차항수는 2이다.*/
        System.out.println((n*(n-1))/2);
        System.out.println(2);
    }
}
