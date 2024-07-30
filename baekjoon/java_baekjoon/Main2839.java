import java.util.Scanner;
public class Main2839 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int cnt = 0;

        while(true) {
            if (N % 5 == 0){
                cnt += N/5;
                System.out.println(cnt);
                break;
            }
            else {
                N -= 3;
                cnt++;
            }
            if (N < 0){
                System.out.println(-1);
                break;
            }
        }
    }
}
//5의 배수가 되면 항상 나누어 떨어지도록 설계를 하고, 이 때의 5로 나누었을 때의 몫과 3키로 봉지를 사용한 갯수를 더해주면 값이 된다.
//그리고 계속해서 뺴다보면, 음수가 될때가 있다. 만약에 3으로만으로 다 구성이 된다면, N이 0이되어도 5로 나누었을 때의 나머지가 0이므로, 출력이 되는 것이다.
//그래서 5로 나누어떨어지지 않아서 음수가 된다면 그것은 있는 봉투로 다 사용을 못하게 되는 것이므로 -1을 출력하게 되는 것이다.
//그리고 또한 봉투를 최소한으로 사용하기 위해선 5키로 봉투를 많이 사용해야하는데 3키로 봉투를 먼저 사용하면서 5의 배수가 되면, 5키로 봉투를 최대한으로 사용하는
//것이기 때문에 위와 같은 코드가 최소값을 출력하는 코드가 된다.