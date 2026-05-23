import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
public class Main1764 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        HashMap<String, String> nolisten = new HashMap<>();

        for (int i = 0; i < n; i++){
            String s1 = sc.next();
            nolisten.put(s1, "0");
        }

        int max = Math.max(n, m);
        String[] arr = new String[max];

        int cnt = 0;
        int index = 0;
        for (int i = 0; i < m; i++){
            String s2 = sc.next();
            if (nolisten.containsKey(s2)){
                cnt++;
                arr[index++] = s2;
            }
        }

        StringBuilder sb = new StringBuilder();
        String[] tmp = new String[index];
        for (int i = 0; i < index; i++){
            tmp[i] = arr[i];
        }
        Arrays.sort(tmp);
        for (int i = 0; i < index; i++){
            sb.append(tmp[i]).append('\n');
        }
        System.out.println(cnt);
        System.out.print(sb);
    }
}
