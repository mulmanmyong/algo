import java.util.Scanner;
import java.util.Stack;

public class Main28278 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int t = sc.nextInt();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < t; i++){
            int n = sc.nextInt();
            if (n == 1){
                int x = sc.nextInt();
                stack.push(x);
            }
            else if (n == 2){
                if (stack.empty()){
                    //System.out.println(-1);
                    sb.append(-1).append('\n');
                }
                else{
                    //System.out.println(stack.pop());
                    sb.append(stack.pop()).append('\n');
                }
            }
            else if (n == 3){
                //System.out.println(stack.size());
                sb.append(stack.size()).append('\n');
            }
            else if (n == 4){
                if (stack.empty()){
                    //System.out.println(1);
                    sb.append(1).append('\n');
                }
                else {
                    //System.out.println(0);
                    sb.append(0).append('\n');
                }
            }
            else if (n == 5){
                if (stack.empty()){
                    //System.out.println(-1);
                    sb.append(-1).append('\n');
                }
                else {
                    //System.out.println(stack.peek());
                    sb.append(stack.peek()).append('\n');
                }
            }
        }
        System.out.print(sb);
    }
}
//각 조건마다 System.out.println()을 불러서 해주면 조건문과 반복문에 시간이 오래걸리게 된다. 그래서 Stringbuilder를 이용하여 담아두고, 한번에 출력을
//해준다.