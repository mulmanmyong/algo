import java.util.Scanner;
public class Main25314 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int tmp = n / 4;

        for (int i = 0; i < tmp; i++){
            System.out.printf("long ");
        }
        System.out.println("int");
    }
}
