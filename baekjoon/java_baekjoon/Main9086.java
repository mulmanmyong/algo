import java.util.Scanner;
public class Main9086 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        for (int i = 0; i < n; i++){
            String[] arr = sc.next().split("");
            System.out.println(arr[0]+arr[arr.length-1]);
        }
    }
}
