import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main1159 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = "";

        int N = Integer.parseInt(br.readLine());
        char[] name = new char[N];
        for (int i = 0; i < N; i++){
            name[i] = (br.readLine()).charAt(0); // 성의 앞자리가 같은 것만 확인하면 되므로
        }

        Arrays.sort(name);
        for (int i = 0; i < N - 1; i++){
            int cnt = 0;
            for (int j = i + 1; j < N; j++){
                if (name[i] == name[j] && name[i] != '0'){
                    cnt++;
                    name[j] = '0';
                }
            }

            if (cnt >= 4){
                s += name[i];
            }
        }

        if (s.equals("")){
            System.out.println("PREDAJA");
        }
        else {
            System.out.println(s);
        }
    }
}
