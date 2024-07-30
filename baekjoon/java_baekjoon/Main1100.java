import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1100 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cnt = 0;
        //[i, j]일 때 0, 0은 하얀 칸이므로 1, 0 과 0, 1은 검정색, 즉 i+j가 짝수일 때 하얀 칸
        for (int i = 0; i < 8; i++){
            String s = br.readLine();
            for (int j = 0; j < 8; j++){
                if ((i+j) % 2 == 0 && s.charAt(j) == 'F') {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }
}
