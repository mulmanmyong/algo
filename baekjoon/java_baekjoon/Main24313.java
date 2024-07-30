import java.util.Scanner;
public class Main24313 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a1 = sc.nextInt();
        int a0 = sc.nextInt();
        int c = sc.nextInt();
        int n0 = sc.nextInt();

        if (a1*n0 + a0 <= c*n0 && a1 <= c){//c가 a1보다 작아버리면, n이 커지다보면 무한대쯤에서 만나게 되고, 역전이 당할 것이기 때문에,
                                            //a1 <= c라는 기울기 조건이 있다.
            System.out.println(1);
        }
        else {
            System.out.println(0);
        }
    }
}
