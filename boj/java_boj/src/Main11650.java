import java.util.Scanner;
import java.util.Arrays;
public class Main11650 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < 2; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        //2차원 배열 정렬은 람다식을 이용, java8이상부터
        //x를 기준으로 정렬, 동일 선상에선 y 기준
        Arrays.sort(arr, (o1, o2) -> {
            // arr[0]이 동일하면
            if (o1[0] == o2[0]) {
                // arr[1] 기준으로 비교
                return o1[1]-o2[1]; //x의 값이 동일하게 되면 y의 값 순대로 정렬을 해야하기 때문, 그래서 이러한 조건을 세움
            }
            return o1[0] - o2[0];
        });

        for (int i = 0; i < n; i++){
            sb.append(arr[i][0]).append(' ').append(arr[i][1]).append('\n');
        }
        System.out.print(sb);
    }
}
