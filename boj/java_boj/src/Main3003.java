import java.util.Scanner;
public class Main3003 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] tmp = {1, 1, 2, 2, 2, 8};
        int[] arr = new int[6];
        for (int i = 0; i < 6; i++){
            arr[i] = sc.nextInt();
        }

        int[] result = new int[6];
        for (int i = 0; i < 6; i++){
            result[i] = tmp[i] - arr[i];
            System.out.print(result[i] + " ");
        }
    }

}
