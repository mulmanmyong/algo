import java.util.Scanner;
import java.util.Arrays;
public class Main11651 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int n = sc.nextInt();
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++){
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }

        Arrays.sort(arr, (o1, o2) -> {
            //arr[1] 기준으로 정렬을 하는데 이 값이 같으면
            if (o1[1]==o2[1]){
                return o1[0]-o2[0]; //arr[0]기준으로 정렬
            }
            return o1[1]-o2[1];
        });

        for (int i = 0; i < n; i++){
            sb.append(arr[i][0]).append(' ').append(arr[i][1]).append('\n');
        }
        System.out.println(sb);
    }
}
