import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main28279 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        Deque<Integer> dq = new ArrayDeque<>();

        int t = sc.nextInt();
        for (int i = 0; i < t; i++){
            int n = sc.nextInt();
            switch (n) {
                case 1 :
                    dq.offerFirst(sc.nextInt());
                    break;
                case 2 :
                    dq.offerLast(sc.nextInt());
                    break;
                case 3 :
                    if (dq.isEmpty()){
                        sb.append(-1).append('\n');
                    }
                    else {
                        sb.append(dq.pollFirst()).append('\n');
                    }
                    break;
                case 4 :
                    if (dq.isEmpty()){
                        sb.append(-1).append('\n');
                    }
                    else {
                        sb.append(dq.pollLast()).append('\n');
                    }
                    break;
                case 5 :
                    sb.append(dq.size()).append('\n');
                    break;
                case 6 :
                    if (dq.isEmpty()) {
                        sb.append(1).append('\n');
                    }
                    else {
                        sb.append(0).append('\n');
                    }
                    break;
                case 7 :
                    if (dq.isEmpty()){
                        sb.append(-1).append('\n');
                    }
                    else {
                        sb.append(dq.getFirst()).append('\n');
                    }
                    break;
                case 8 :
                    if (dq.isEmpty()){
                        sb.append(-1).append('\n');
                    }
                    else {
                        sb.append(dq.getLast()).append('\n');
                    }
                    break;
            }
        }
        System.out.print(sb);
    }
}
