import java.util.Scanner;
import java.util.Arrays;
public class Main14215 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = new int[3];
        for (int i = 0; i < 3; i++){
            arr[i] = sc.nextInt();
        }
        int result = 0;
        Arrays.sort(arr);
        if (arr[2] >= arr[0] + arr[1]){
            result = 2 * (arr[0] + arr[1]) - 1;
        }
        else {
            result = Arrays.stream(arr).sum();
        }

        System.out.println(result);
    }
}
