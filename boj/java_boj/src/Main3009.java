import java.util.Scanner;
public class Main3009 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] x = new int[3];
        int[] y = new int[3];
        for (int i = 0; i < 3; i++){
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }

        int rx = 0;
        if (x[0] == x[1]){
            rx = x[2];
        }
        else if (x[1] == x[2]){
            rx = x[0];
        }
        else if (x[2] == x[0]){
            rx = x[1];
        }

        int ry = 0;
        if (y[0] == y[1]){
            ry = y[2];
        }
        else if (y[1] == y[2]){
            ry = y[0];
        }
        else if (y[2] == y[0]){
            ry = y[1];
        }

        System.out.println(rx + " " + ry);
    }
}
