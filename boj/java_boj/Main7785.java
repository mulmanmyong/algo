import java.io.*;
import java.util.*;

public class Main7785 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int n = sc.nextInt();
        HashMap<String, String> hs = new HashMap<>();
        for (int i = 0; i < n; i++){
            String name = sc.next();
            String state = sc.next();
            if (hs.containsKey(name)){
                hs.remove(name);
            }
            else {
                hs.put(name, state);
            }
        }

        List<String> ll = new ArrayList<>(hs.keySet());
        Collections.sort(ll , ((o1, o2) -> {
            return o2.compareTo(o1);
        }));
        for (String s : ll){
            sb.append(s).append('\n');
        }
        System.out.print(sb);
    }
}
