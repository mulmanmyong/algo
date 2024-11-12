import java.io.*;
import java.util.StringTokenizer;

public class Main1004 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int cnt = 0; //진입 또는 이탈 발생 횟수
            int n = Integer.parseInt(br.readLine());
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                int cx = Integer.parseInt(st.nextToken());
                int cy = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                boolean check1 = false; //시작점이 원 안에 있는가?
                boolean check2 = false; //도착점이 원 안에 있는가?
                //최소의 행성계 진입/이탈 횟수 count, 행성들을 최대한 피한다고 함.
                // 그러면 시작점이나 도착점이 원에 포함되어있는 지만 확인해서
                // 둘 다 포함되어있으면, 진입과 이탈이 없고, 둘다 밖에 있으면 피해가고, 둘 중 하나만 포함 되어있으면 어쩔 수 없이 진입또는 이탈 발생
                if (Math.pow(x1 - cx, 2) + Math.pow(y1 - cy, 2) < Math.pow(r, 2)) {
                    //시작점이 원 안에 있으면?
                    check1 = true;
                }
                if (Math.pow(x2 - cx, 2) + Math.pow(y2 - cy, 2) < Math.pow(r, 2)) {
                    //도착점이 원 안에 있으면?
                    check2 = true;
                }

                if ((check1 == false && check2 == true) || (check1 == true && check2 == false)) {
                    //시작점과 도착점 중 둘 중 하나만 원 안에 포함되어 있으면, 진입 또는 이탈 발생
                    cnt++;
                }
            }
            sb.append(cnt).append('\n');
        }
        bw.write(String.valueOf(sb));

        br.close();
        bw.flush();
        bw.close();
    }
}
