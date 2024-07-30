import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class Main25192 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        HashSet<String> hs = new HashSet<>();
        int cnt = 0;
        while (n --> 0){
            String s = br.readLine();
            if (s.equals("ENTER")){
                cnt += hs.size();
                hs = new HashSet<>();
            }
            else {
                hs.add(s);
            }
        }
        cnt += hs.size();
        System.out.println(cnt);

        br.close();
    }
}