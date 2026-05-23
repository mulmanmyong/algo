import java.util.Scanner;

public class Main1225 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] str = new String[2];
        str[0] = sc.next();
        str[1] = sc.next();

        long ans = 0;
        for (int i = 0; i < str[0].length(); i++){
            for (int j = 0; j < str[1].length(); j++){
                ans += ((str[0].charAt(i) - '0') * (str[1].charAt(j) - '0'));
            }
        }
        System.out.println(ans);
    }
}
