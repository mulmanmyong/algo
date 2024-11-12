import java.util.Scanner;

public class Main14888 {
    public static int N;
    public static int[] arr;
    public static int[] operator = new int[4];
    // + - * / 순

    public static int max = Integer.MIN_VALUE;
    public static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 0; i < 4; i++) {
            operator[i] = sc.nextInt();
        }

        dfs(arr[0], 1); //시작하는 초기값과 연산을 진행하는 다음 수는 arr[1]이기 때문에, 다음과 같은 dfs로 시작
        System.out.println(max);
        System.out.println(min);

    }

    static void dfs(int num, int index) {
        if (index == N) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operator[i] > 0) {
                operator[i]--; //operator 하나 사용, count 1 down

                switch (i) { //operator가 존재할 때 각 index에 따른 연산 진행, index는 1부터이기 때문에 다음 수와 연산하는 갓
                    case 0 :
                        dfs(num + arr[index], index + 1);
                        break;
                    case 1 :
                        dfs(num - arr[index], index + 1);
                        break;
                    case 2 :
                        dfs(num * arr[index], index + 1);
                        break;
                    case 3 :
                        dfs(num / arr[index], index + 1);
                        break;
                }
                operator[i]++; //다시 돌아와서 진행할 연산을 위해, 재귀가 끝나면 연산자의 갯수 복구
            }
        }
    }
}
