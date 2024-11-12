import java.util.Scanner;
import java.util.Arrays;
public class Main1181 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String[] arr = new String[n];
        for (int i = 0; i < n; i++){
            arr[i] = sc.next();
        }

        Arrays.sort(arr, (o1, o2) -> {
            //길이가 짧은 순부터 정렬
            if (o1.length() == o2.length()){ //길이가 같은 경우 비교해서 사전순으로
                return o1.compareTo(o2);
            }
            else {
                return o1.length()-o2.length();
            }
        });

        //중복되는 것은 한번만 출력이 되도록
        System.out.println(arr[0]);
        for (int i = 1; i < n; i++){
            if (!arr[i].equals(arr[i-1])){
                System.out.println(arr[i]);
            }
        }
    }
}
