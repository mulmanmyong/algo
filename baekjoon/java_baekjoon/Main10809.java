import java.util.Scanner;
public class Main10809 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = new int[26];
        for (int i = 0; i < 26; i++){
            arr[i] = -1;
        }

        String s = sc.nextLine();
        for (int i = 0; i < s.length(); i++){
            int tmp = s.charAt(i);

            if (arr[tmp - 'a'] == -1){
                arr[tmp - 'a'] = i;
            }
        }

        for (int i : arr){
            System.out.print(i + " ");
        }
    }
}
