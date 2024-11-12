import java.util.Scanner;

public class Main9663 {
    public static int[] arr;
    public static int N;
    public static int cnt = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];

        dfs(0);
        System.out.println(cnt);
    }

    static void dfs(int depth) {
        if (N == depth) { //끝까지 도달하면(모두 다 놓으면)
            cnt++;
            return;
        }

        for (int i = 0; i < N; i++) {
            arr[depth] = i; //각 깊이(열)에서 행마다 놓아보는 것
            if (check(depth)) {
                dfs(depth + 1); //놓을 수 있으니, 놓고 다음 열로 이동
            }
        }
    }

    static boolean check(int col) {
        for (int i = 0; i < col; i++) { //해당 깊이까지 중복이 있는 지 확인해야함
            if (arr[col] == arr[i]) {
                return false; //한 행에 하나밖에 못 두기 때문에 같은 행에 존재하면 불가능
            } else if (Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
                return false; //대각선 상에 있어도 불가능. 기울기가 1또는 -1이면 대각선 상에 있는 것이기 때문에,
                              //행의 차와 열의 차의 절댓값이 같으면 대각선 상에 있는 것.
            }
        }

        return true; //위의 조건을 다 통과하면 놓을 수 있는 것
    }
}
