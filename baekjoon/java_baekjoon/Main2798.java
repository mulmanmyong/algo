import java.util.Scanner;
public class Main2798 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++){
            arr[i] = sc.nextInt();
        }

        //브루트포스 알고리즘은 모든 경우의 수를 탐색하는 것이기 때문에, 걱정했던 것과 달리 모든 경우의 수 3가지를 고르는 경우의 수를 고르는 것으로 하겠다.
        int max = 0;
        int sum = 0;
        for (int i = 0; i < N-2; i++){
            for (int j = i+1; j < N-1; j++){
                for (int k = j+1; k < N; k++){
                    sum = arr[i] + arr[j] + arr[k];
                    if (sum < M){
                        max = Math.max(max, sum);//둘 중에 더 큰값 찾기, 기존에 들어있는 max와 새롭게 근접하는 값 max 비교해서 더 큰 수 저장
                    }
                    else if (sum == M){
                        max = sum; //최대치와 동일하게 되면 바로 max에 담고, 종료
                        break;
                    }
                }
            }
        }
        System.out.println(max);
    }
}
