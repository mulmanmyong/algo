import java.util.Scanner;

public class Main9184 {
    public static int[][][] dp = new int[21][21][21]; //동적계획법 dp는 이미 계산했던 것은 계산을 하지 않도록 해주는 것
    //0부터 음수가 나오게 되면 1을 바로 return 하고, 20이 넘어가면 값을 20으로 고정, 그리고 index는 1부터 20까지만 사용하기 때문에 해당 배열의 크기.


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();

            if (a == -1 && b == -1 && c == -1) {
                break;
            }

            System.out.printf("w(%d, %d, %d) = %d\n", a, b, c, w(a, b, c));

        }

    }

    static int w(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return 1; //뭐 하나라도 음수면 값은 1
        }

        if (a > 20 || b > 20 || c > 20) {
            return w(20, 20, 20); //뭐 하나라도 20 넘어가면 w(20, 20, 20)으로 고정
        }

        //위의 범위를 만족하면 연산 진행

        if (dp[a][b][c] != 0) {
            //dp는 계산 했던 것을 다시 계산을 안하게 도움을 준다. 즉, 이미 연산을 진행해서 값이 담겨 있는데 다시 호출될 경우 그냥 바로 계산 값 return
            return dp[a][b][c];
        }

        if (a < b && b < c) {
            return dp[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
        }

        return dp[a][b][c] = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);

    }
}
