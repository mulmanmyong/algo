import java.util.Scanner;
public class Main1018 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();


        String[][] s = new String[N][M];
        for (int i = 0; i < N; i++){
            String arr = sc.next();
            for (int j = 0; j < M; j++) {
                if (arr.charAt(j) == 'W'){
                    s[i][j] = "W";
                }
                else {
                    s[i][j] = "B";
                }
            }
        }

        int min = 64;

        //각각의 경우의 수는 총길이 -7한 것들의 곱이다. 즉, 시작지점은 각각 N-7, M-7까지로 잡으면 된다.
        for (int i = 0; i < N-7; i++){
            for (int j = 0; j < M-7; j++){
                min = Math.min(min, count(i, j, s));
            }
        }
        System.out.println(min);
    }

    public static int count(int x, int y, String[][] arr) {
        int cnt = 0;

        String c;
        if (arr[x][y] == "W"){
            c = "W"; //"W"로 시작할 경우
        }
        else {
            c = "B"; //"B"로 시작할 경우
        }
        for (int i = x; i < x+8; i++){
            for (int j = y; j < y+8; j++){
                if (!arr[i][j].equals(c)){
                    cnt++;
                }
                //열마다 색깔변경
                if (c.equals("W")){
                    c = "B";
                }
                else{
                    c = "W";
                }
            }
            //행마다 색깔변경
            if (c.equals("W")){
                c = "B";
            }
            else{
                c = "W";
            }
        }
        cnt = Math.min(cnt, 64-cnt);
        return cnt;
    }
}

//8x8 체스판이기 때문에 8x8모양을 찾은 후 그 안에서 동작을 해야한다.
//시작이 w일지 b일지 모르기 때문에 각각 조건을 나누어 시작의 색을 찾아서 그 값으로 비교를 시작해나갔다.
//일종의 도움이 있었지만, 조금 더 침착하게 접근을 하면 더 가능 할 것이다.
//아직까지 메서드를 나누어서 코드를 작성하는 것이 어렵지만, 알고리즘과 자료구조를 해결하기 위해선 이러한 방식도 차근차근 해나아가야 한다.
//도움도 조금씩 줄여나가고, 열심히 풀어보도록 해보자
