import java.util.Scanner;

public class Main1145 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = new int[5];
        for (int i = 0; i < 5; i++){
            arr[i] = sc.nextInt();
        }

        int x = 0;
        int cnt;
        while (true) {
            x++;
            cnt = 0;
            for (int i = 0; i < 5; i++){
                if (x % arr[i] == 0) {
                    cnt++;
                }
            }

            if (cnt >= 3){
                System.out.println(x);
                break;
            }
        }
    }
}
