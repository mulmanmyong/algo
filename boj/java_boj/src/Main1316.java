import java.util.Scanner;
public class Main1316 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int cnt = 0;
        for (int i = 0; i < n; i++){
            String s = sc.next();
            boolean[] check = new boolean[26];
            boolean tmp = true;

            for (int j = 0; j < s.length(); j++){
                int index = s.charAt(j) - 'a';
                if (check[index]){ //check[index]가 true이면 if문 동작
                    if (s.charAt(j) != s.charAt(j - 1)){
                        tmp  = false;
                        break;
                    }
                }
                else {
                    check[index] = true;
                }
            }
            if (tmp) {
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
