import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main1236 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int cnt = 0;
        String[] arr = new String[N];
        boolean[] check = new boolean[M];
        boolean[] noGuard = new boolean[N];
        for (int i = 0; i < N; i++){
            arr[i] = br.readLine();
            for (int j = 0; j < M; j++){
                if (arr[i].contains("X") == false){
                    //cnt += 1; //경비병이 없으면 count 증가, 반드시 있어야 하므로
                    noGuard[i] = true;
                }
                else if (arr[i].charAt(j) == 'X'){
                    check[j] = true; //가드가 존재
                }
            }
        }

        for (int i = 0; i < N; i++){
            if (noGuard[i] == true){
                cnt++;
            }
        }
        //System.out.println(cnt);

        int ans = cnt;
        int need = 0;
        for (int i = 0; i < M; i++){
            if (check[i] == false){
                need++; // 가드가 어디에 추가로 필요한가
            }
        }

        if (need > cnt) {
            ans += (need-cnt);

        }
        System.out.print(ans);
    }
}
