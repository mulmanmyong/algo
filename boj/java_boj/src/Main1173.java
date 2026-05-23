import java.util.Scanner;

public class Main1173 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int m = sc.nextInt();
        int M = sc.nextInt();
        int T = sc.nextInt();
        int R = sc.nextInt();

        int cnt = 0;
        int move = 0;
        int pulse = m;
        while (move != N) {
            cnt++;

            if (pulse + T <= M){
                pulse += T;
                move++;
            }
            else {
                pulse -= R;

                if (pulse < m) {
                    pulse = m;
                }
            }

            if ((pulse + T > M) && (pulse == m)){
                break; //운동을 해서 최대 맥박을 넘기는데, 이 때 맥박이 초기맥박이랑 같을 때 멈춤. 이는 운동시간을 더 이상 채울 수 없음을 의미.
            }
        }

        if (move != N) {
            //운동시간을 채우지 못한 경우
            System.out.println(-1);
        }
        else {
            //운동시간을 채웠으며, 이 때 걸린 최소 시간은?
            System.out.println(cnt);
        }
    }
}
