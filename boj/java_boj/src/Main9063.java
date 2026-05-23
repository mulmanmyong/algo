import java.util.Scanner;
import java.util.Arrays;
public class Main9063 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++){
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }

        Arrays.sort(x);
        Arrays.sort(y);
        int x_min = x[0];
        int x_max = x[n-1];
        int y_min = y[0];
        int y_max = y[n-1];
        if (n == 1){
            System.out.println(0);
        }
        else {
            System.out.println((x_max - x_min) * (y_max - y_min));
        }

    }
}
