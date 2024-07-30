import java.util.Arrays;
import java.util.Scanner;
public class Main1427 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] arr = sc.next().toCharArray(); //아스키코드 값으로 받아오기에 정렬이 가능

        Arrays.sort(arr);
        for (int i = arr.length - 1; i >= 0; i--){
            System.out.print(arr[i]);
        }
    }
}
