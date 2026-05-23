import java.util.HashMap;
import java.util.Scanner;

public class Main10816 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        HashMap<Integer, Integer> hs = new HashMap<>();
        for (int i = 0; i < n; i++){
            int num = sc.nextInt();
            hs.put(num, hs.getOrDefault(num, 0) + 1);
        }

        int m = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++){
            int find = sc.nextInt();
            if (hs.containsKey(find)){
                sb.append(hs.get(find)).append(' ');
            }
            else {
                sb.append('0').append(' ');
            }
        }
        System.out.println(sb);
    }
}
