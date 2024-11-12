import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main14425 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        //hashmap사용
        HashMap<String, Integer> hs = new HashMap<>();
        for (int i = 0; i < n; i++){
            hs.put(br.readLine(), 0); //Integer자리에 아무거나 넣음, 0으로 초기화인 느낌, 찾는 값이 있으면 cnt를 증가를 시키는 것이기 때문이다.
        }

        int cnt = 0;
        for (int i = 0; i < m; i++){
            if (hs.containsKey(br.readLine())){
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
