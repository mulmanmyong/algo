import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1009 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb= new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(Computer(a, b)).append('\n');
        }
        System.out.print(sb);
    }

    public static int Computer(int a, int b){
        if (a % 10 == 0){
            return 10;
        }
        int num = a % 10;
        for (int i = 1; i < b; i++){
            num = (num * a) % 10;
        }
        return num;
    }
}
