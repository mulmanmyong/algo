import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1212 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        StringBuilder sb = new StringBuilder();
        String[] arr = {"000", "001", "010", "011", "100", "101", "110", "111"};

        for (int i = 0; i < s.length(); i++){
            int a = s.charAt(i) - '0';
            sb.append(arr[a]); //각 인덱스에 맞게 8진수로 수가 배열에 저장되어있음. 8진수의 작은 진법이라 가능
        }

        if (s.equals("0")){
            System.out.println(s);
        }
        else {
            while (sb.charAt(0) == '0'){ //맨 처음 숫자가 0이면
                sb = new StringBuilder(sb.substring(1)); //맨 앞 숫자를 제거하고 그 다음 숫자로 구성.
                //이 때 이 while문은 맨 앞이 0이 아닐 때 까지 동작
            }
            System.out.println(sb);
        }
    }
}
