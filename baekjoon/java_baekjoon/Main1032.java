import java.util.Scanner;

public class Main1032 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();
        String[] s = new String[T];
        for (int i = 0; i < T; i++){
            s[i] = sc.next();
        }

        boolean isSame;
        for (int i = 0; i < s[0].length(); i++){
            char c = s[0].charAt(i);

            isSame = true; //초기화,
            for (int j = 1; j < T; j++){
                if (c != s[j].charAt(i)){
                    isSame = false; //깉지 않다
                    break; //두번쨰와 첫번째가 이미 다르면 그 뒤에가 같아도 소용x
                }
            }

            if (isSame == true){
                sb.append(c);
            }
            else {
                sb.append('?');
            }
        }
        System.out.print(sb);
    }
}
