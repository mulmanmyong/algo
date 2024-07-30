import java.util.Scanner;
public class Main10988 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        int mid = s.length()/2;

        int a = 1;
        for (int i = 0; i < mid; i++){
            if (s.charAt(i) != s.charAt(s.length() - 1-i)){
                a = 0;
            }
        }
        System.out.println(a);
    }
}
