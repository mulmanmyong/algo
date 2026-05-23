import java.util.Scanner;
public class Main11005 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int B = sc.nextInt();

        System.out.println(Integer.toString(N, B).toUpperCase());
    }
}
// 다음엔 직접 알고리즘을 구현해볼 것. 아마 리스트를 만들어서 리스트에 담아서 리스트를 역으로 출력하면 될 것.