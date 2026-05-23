import java.util.Scanner;
import java.util.Arrays;
public class Main5073 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true){
            int[] arr = new int[3];
            for (int i = 0; i < 3; i++){
                arr[i] = sc.nextInt();
            }
            if (arr[0] == 0 && arr[1] == 0 && arr[2] == 0){
                break;
            }
            else {
                int max = Arrays.stream(arr).max().getAsInt();
                int sum = Arrays.stream(arr).sum();
                if (max >= sum - max){
                    System.out.println("Invalid");
                }
                else{
                    if (arr[0] == arr[1] && arr[1] == arr[2]){
                        System.out.println("Equilateral");
                    }
                    else if (arr[0] == arr[1] || arr[1] == arr[2] || arr[2] == arr[0]){ //왜 자꾸 마지막에 2 == 1로 해서 틀리지
                        //정신 똑바로 차리고 실수를 바로잡자
                        System.out.println("Isosceles");
                    }
                    else {
                        System.out.println("Scalene");
                    }
                }
            }
        }
    }
}
