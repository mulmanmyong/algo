import java.util.Scanner;
import java.util.Arrays;
public class Main10814 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        //Stable sort 이용 -> insertion, merge, bubble, counting
        int n = sc.nextInt();
        String[][] arr = new String[n][2];
        for (int i = 0; i < n; i++){
            arr[i][0] = sc.next();
            arr[i][1] = sc.next();
        }

        Arrays.sort(arr, (o1, o2) -> {
            //나이순으로만 정렬을 하고, 그렇지 않을 때는 입력한 순대로 정렬이 되어야한다. 그래서 그냥 나이순대로 정렬을 해주게 된다면 정렬이된다.
            //배열의 크기가 작으므로 insertion 또는 counting이 적용된다. 아마 지금의 크기에서는 insertion이 적용이 되었기 때문에 stablesort이다.
            return Integer.parseInt(o1[0])-Integer.parseInt(o2[0]);
        });

        for (int i = 0; i < n; i++){
            sb.append(arr[i][0]).append(' ').append(arr[i][1]).append('\n');
        }
        System.out.print(sb);

    }
}
