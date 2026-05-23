import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main26069 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<String> hs = new HashSet<>();
        hs.add("ChongChong");

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();

            if (hs.contains(a) || hs.contains(b)) { //둘 중에 한명이라도 춤추고 있으면 다 춤춰야함.
                //중복제거를 위해 HashSet이용하는 것이니 다 추가
                hs.add(a);
                hs.add(b);
            }
        }
        System.out.println(hs.size());
        br.close();
    }
}
