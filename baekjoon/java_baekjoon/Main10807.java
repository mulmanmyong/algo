import java.util.Scanner;
public class Main10807 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int cnt = 0;
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int check = sc.nextInt();

        for (int i = 0; i < n; i++){
            if (arr[i] == check) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
