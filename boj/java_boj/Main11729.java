import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main11729 {
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        sb.append((int)Math.pow(2, n) - 1).append('\n');
        Hanoi(n, 1, 2, 3);
        System.out.println(sb);
    }

    public static void Hanoi(int n, int start, int mid, int arrive){
        if (n == 1){//이동할 장판이 하나 남았을 경우, 마지막 장판
            sb.append(start).append(' ').append(arrive).append('\n');
            return;
        }

        Hanoi(n - 1, start, arrive, mid); //젤 밑에 있는 원판을 젤 우측으로 이동시켜야하기에, 나머지는 중간지점으로 이동;
        sb.append(start).append(' ').append(arrive).append('\n');
        Hanoi(n - 1, mid, start, arrive); //나머지도 이동
    }
}
