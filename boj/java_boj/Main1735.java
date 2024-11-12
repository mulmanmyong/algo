import java.util.Scanner;

public class Main1735 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] a = new int[2];
        int[] b = new int[2];
        for (int i = 0; i < 2; i++){
            a[i] = sc.nextInt();
            b[i] = sc.nextInt();
        }

        if (b[0] > b[1]) {
            int j = 1;
            while (true) {
                if (b[0] * j % b[1] == 0){
                    int newa = (a[0] * j) + (a[1] * (b[0]*j/b[1]));
                    int newb = (b[0]*j);
                    int[] result = divide(newa, newb);
                    System.out.println(result[0] + " " + result[1]);
                    break;
                }
                else{
                    j++;
                }
            }
        }
        else if (b[0] < b[1]) {
            int j = 1;
            while (true) {
                if (b[1] * j % b[0] == 0){
                    int newa = (a[1] * j) + (a[0] * (b[1]*j/b[0]));
                    int newb = (b[1]*j);
                    int[] result = divide(newa, newb);
                    System.out.println(result[0] + " " + result[1]);
                    break;
                }
                else{
                    j++;
                }
            }
        }
        else if (b[0] == b[1]){
            int newa = a[0] + a[1];
            int newb = b[0];
            int[] result = divide(newa, newb);
            System.out.println(result[0] + " " + result[1]);
        }
    }

    public static int gcd (int a, int b) {
        while (b != 0){
            int tmp = a % b;
            a = b;
            b = tmp;
        }

        return Math.abs(a);
    }

    public static int[] divide (int a, int b){
        int[] frac = new int[2];
        frac[0] = a;
        frac[1] = b;

        if (frac[1] == 0) { // 분모가 0일 경우에 에러 반환
            frac[0] = 0;
            frac[1] = 0;
            return frac;
        }

        int gcd_result = gcd(frac[0], frac[1]);

        frac[0] = frac[0] / gcd_result;
        frac[1] = frac[1] / gcd_result;

        return frac;
    }

}
