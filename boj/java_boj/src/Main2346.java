import java.io.*;
import java.util.*;

public class Main2346 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Deque<int[]> dq = new ArrayDeque<>();

        int t = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[t];
        for (int i = 0; i < t; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        sb.append("1 "); //시작은 1이므로
        int move = arr[0];
        for (int i = 1; i < t; i++){
            dq.offer(new int[] {(i+1), arr[i]});
        }
        while (!dq.isEmpty()){
            if (move > 0){
                for (int i = 1; i < move; i++){
                    dq.offer(dq.poll());
                }

                int[] next = dq.poll();
                move = next[1];
                sb.append(next[0]).append(" ");
            }
            else {
                for (int i = 1; i < -move; i++){
                    dq.offerFirst(dq.pollLast());
                }

                int [] next = dq.pollLast();
                move = next[1];
                sb.append(next[0]).append(" ");
            }
        }
        System.out.print(sb);
    }
}