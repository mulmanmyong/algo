import java.util.Scanner;
import java.util.Stack;

public class Main9012 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int t = sc.nextInt();
        for (int i = 0; i < t; i++){
            sb.append(result(sc.next())).append('\n');
        }
        System.out.print(sb);
    }

    public static String result (String s){
        Stack<Character> stack = new Stack<>();
        for (int j = 0; j < s.length(); j++){
            char c = s.charAt(j);
            if (c == '('){
                stack.push(c);
            }
            else if (c == ')'){
                if (stack.empty()){
                    return "NO";
                }
                else {
                    stack.pop();
                }
            }
        }
        if (stack.empty()){
            return "YES";
        }
        else {
            return "NO";
        }
    }
}
