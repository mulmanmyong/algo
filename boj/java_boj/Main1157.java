import java.util.Scanner;
public class Main1157 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = new int[26];
        String s = sc.next();

        for (int i = 0; i < s.length(); i++){
            if ('A' <= s.charAt(i) && s.charAt(i) <= 'Z'){
                arr[s.charAt(i) - 'A']++;
            }
            else {
                arr[s.charAt(i) - 'a']++;
            }
        }

        int a = -1;
        char c = '?';
        for (int i = 0; i < 26; i++) {
            if (arr[i] > a) {
                a = arr[i];
                c = (char) (i + 'A');
            }
            else if (arr[i] == a) {
                c = '?';
            }
        }

        System.out.print(c);
    }
}
