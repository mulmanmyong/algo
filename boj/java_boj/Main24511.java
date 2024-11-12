import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main24511 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        Deque<Integer> dq = new ArrayDeque<>();

        int t = Integer.parseInt(br.readLine());
        int[] arr = new int[t];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < t; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            //System.out.println(arr[i]);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < t; i++){
            int a = Integer.parseInt(st.nextToken());
            if (arr[i] == 0){
                //stack은 LIFO이기 때문에 기존에 들어있는 원소가 상관이 없다. 그래서 queue일 때만 덱에 삽입을 해주게 되면 FIFO에 따라
                //먼저 넣은 순 대로 차례대로 출력이 된다.
                dq.offerFirst(a);
            }
        }

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++){
            int a = Integer.parseInt(st.nextToken());

            //틀린코드
            /*if (arr[t-1] == 0){ //queue
                dq.offerLast(a);
                sb.append(dq.pollFirst()).append(' ');
            }
            else if (arr[t-1] == 1) { //stack
                dq.offerLast(a);
                sb.append(dq.pollLast()).append(' ');
            }*/

            //맞는 코드
            dq.offerLast(a);
            sb.append(dq.poll()).append(' ');
        }
        //반례 :
        System.out.print(sb);
    }
}
