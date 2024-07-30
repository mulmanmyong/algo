import java.util.Arrays;
import java.util.Scanner;

public class Main1015 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt();
        int[] A = new int[N];
        int[] cnt = new int[N];
        for (int i = 0; i < N; i++){
            int p = sc.nextInt();
            A[i] = p;
            cnt[i] = p;
        }
        Arrays.sort(cnt);
        for (int i = 0 ; i < N; i++){
            for (int j = 0; j < N; j++){
                if (A[i] == cnt[j]){
                    sb.append(j).append(" ");
                    //배열의 원소는 자연수, 자연수는 1이상인 정수, 즉, 이미 출력한 index는 겹칠일 없게 자연수가 아닌 수로 설정
                    cnt[j] = 0;
                    break;
                }
            }
        }
        System.out.print(sb);
    }
}
