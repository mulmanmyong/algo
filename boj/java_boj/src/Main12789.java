import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;

public class Main12789 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();

        int t = sc.nextInt();
        for (int i = 0; i < t; i++){
            queue.add(sc.nextInt());
        }
        int start = 1;
        while (!queue.isEmpty()){
            if (queue.peek() == start){
                queue.poll();
                start++;
            }
            else if (!stack.isEmpty() && stack.peek() == start){
                stack.pop();
                start++;
            }
            else {
                stack.push(queue.poll());
            }
        }

        while (!stack.empty()){
            if (stack.peek() == start){
                stack.pop();
                start++;
            }
            else {
                System.out.println("Sad");
                break;
            }
        }
        if (start == t + 1){
            System.out.println("Nice");
        }
    }
}
