import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main15894 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        System.out.println(n*4);
        //int의 범위를 넘어서게 되므로 Long을 사용해주어야 한다.
    }
}
