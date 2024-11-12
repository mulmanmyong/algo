import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main4779 {
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s;
        while((s = br.readLine()) != null){
            int n = Integer.parseInt(s);
            int len = (int) Math.pow(3, n);
            sb = new StringBuilder();
            for (int i = 0; i < len; i++){
                sb.append('-');
            }

            func(0, len);
            System.out.println(sb);
        }
    }

    public static void func (int start, int size){
        if (size == 1){
            return;
        }
        int newsize = size / 3;
        for(int i=start+newsize; i<start+2*newsize; i++) {
            sb.setCharAt(i, ' ');
        }

        func(start, newsize); // 3등분한 문자열에서 첫번째 부분
        func(start+2*newsize, newsize); // 3등분한 문자열의 세번째 부분 
    }
}
