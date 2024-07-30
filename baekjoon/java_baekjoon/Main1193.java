import java.util.Scanner;
public class Main1193 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int max_index = 0;
        int line = 0;

        while (n > max_index){
            line ++;
            max_index += line;
        }

        int find = n - (max_index - line);
        if (line % 2 == 0){
            System.out.printf("%d/%d", find , line - find + 1);
        }
        else {
            System.out.printf("%d/%d", line - find + 1, find);
        }
    }
}
