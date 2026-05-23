import java.util.Scanner;
public class Main2869 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();
        int V = sc.nextInt();

        if ((V - B) % (A - B) != 0){
            System.out.println((V-B) / (A-B) + 1);
        }
        else {
            System.out.println((V-B) / (A-B));
        }

        //재귀의 동작 확인하기
        //아랴의 코드는 동작은 하지만, 반복문이기 때문에, case가 커지게 되면 너무 오랜 시간이 걸려서 문제 풀이에서 시간초과가 나게 된다.
        //이래서 재귀를 쓸 수 있으면, 반복문이 아닌 재귀를 사용해야한다.
        /*int cnt = 1;
        int h = 0;
        while (true) {
            h = h + A;
            if (V > h) {
                h = h - B;
                cnt++;
            }
            else {
                break;
            }
        }
        System.out.println(cnt);*/
    }
}
