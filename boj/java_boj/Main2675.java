import java.util.Scanner;
public class Main2675 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        for (int i = 0; i < n; i++){
            int r = sc.nextInt();
            String[] s = sc.next().split("");

            for (String c : s){
                for (int j = 0; j < r; j++){
                    System.out.print(c);
                }
            }
            System.out.println();
        }
    }
}
