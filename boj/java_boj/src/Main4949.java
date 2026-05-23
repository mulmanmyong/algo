import java.util.Scanner;
import java.util.Stack;

public class Main4949 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String s = sc.nextLine();
            if (s.equals(".")){
                break;
            }
            System.out.println(result(s));
        }

    }

    public static String result (String s){
        Stack<Character> stack = new Stack<>();
        for (int j = 0; j < s.length(); j++){
            char c = s.charAt(j);
            if (c == '(' || c == '['){
                stack.push(c);
            }
            else if (c == ')'){
                if (stack.empty() || stack.peek() != '('){
                    return "no";
                }
                else {
                    stack.pop();
                }
            }
            else if (c == ']'){
                if (stack.empty() || stack.peek() != '['){
                    return "no";
                }
                else {
                    stack.pop();
                }
            }
        }
        if (stack.empty()){
            return "yes";
        }
        else {
            return "no";
        }
    }
}
