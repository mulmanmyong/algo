import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main1233 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int S1 = sc.nextInt();
        int S2 = sc.nextInt();
        int S3 = sc.nextInt();

        //나올 수 있는 경우의 수 -> 3 2 3의 경우에는 3부터 8까지 즉 6가지 S1+S2+S3 - 2가지
        int[] arr = new int[(S1+S2+S3) - 2];//각 주사위의 합을 각 인덱스에 저장.
        for (int i = 1; i <= S1; i++){
            for (int j = 1; j <= S2; j++){
                for (int k = 1; k <= S3; k++){
                    int sum = i + j + k;
                    //최소의 값 1+1+1 = 3 이 젤 처음 인덱스에 저장
                    arr[sum-3]++; //제일 많이 나온 값이 출력, 그 중에서 제일 작은 값 출력해야함.
                }
            }
        }

        int max = 0; //제일 많이 나온 수
        int ans = 0; //정답은 인덱스에서 3더한 값.
        for (int i = 0; i < arr.length; i++){
            if (max < arr[i]){
                max = arr[i]; //빈도수
                ans = i + 3; //제일 많이 나온 합의 값
            }
        }

        System.out.println(ans);
    }
}
