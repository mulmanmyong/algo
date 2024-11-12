import java.io.*;
import java.util.StringTokenizer;

//기하학과 관련된 문제, 간단하게 수학만 할 수 있고, 외접 내접에 대해서 생각을 하고, 이끌어낼 수 있으면 공식은 간단하게 생각해낼 수 있음
//이런 간단 수학은 코드로 짜내기 어려움이 없다.
public class Main1002 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            sb.append(location(x1, y1, r1, x2, y2, r2) + "\n");
        }
        br.close();;
        bw.write(String.valueOf(sb));
        bw.flush();
        bw.close();
    }

    public static int location(int x1, int y1, int r1, int x2, int y2, int r2){
        //점이 2개 주어졌으므로, 이 점의 중점사이의 거리 계산
        int distance = (int)Math.pow(x2-x1, 2) +(int)Math.pow(y2-y1, 2);

        //점이 무한대로 존재할 경우는 -> 중점이 동일하고, 반지름도 같을 경우 : 동일한 위치와 크기의 원이라는 뜻
        if (x1==x2 && y1==y2 && r1==r2){
            return -1;
        }

        //원이 접하지 않으면 존재하지가 않음
        //두 점 사이의 거리가 반지름의 합보다 길때, 외접하지 않을 때
        else if (distance > Math.pow(r1+r2, 2)){
            return 0;
        }
        //큰 원 안에 작은 원이 존재하지만, 내접하지 않을 때
        else if (distance < Math.pow(r2-r1, 2)){
            return 0;
        }

        //원이 한점에서만 접함, 내접또는 외접
        //외접할 때
        else if (distance == Math.pow(r1+r2, 2)){
            return 1;
        }
        //내접할 때
        else if (distance == Math.pow(r2-r1, 2)){
            return 1;
        }

        //이 모든 조건의 만족하지 않을 경우에는 두 곳이 존재
        else {
            return 2;
        }
    }
}
