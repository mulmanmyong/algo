import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2447 {
    static char[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        arr = new char[n][n];

        star(0, 0, n, false);

        //별 출력
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                sb.append(arr[i][j]);
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    public static void star (int x, int y, int len, boolean blank){
        //3의 거듭제곱의 사이즈 마다 가운데에 구멍이 있어야함 예를 들면, 3ㅌ3에서의 구멍, 9ㅌ9에서의 구멍 27ㅌ27에서의 구멍
        //3ㅌ3 에서는 구멍의 크기가 1 9ㅌ9에서는 구멍의 크기가 3, 27ㅌ27에서는 구멍의 크기가 9
        //인덱스로 치면 0, 2일 땐 1, 3, 5일 땐 4
        if (blank) {//공백이면, blank = true;
            for (int i = x; i < x + len; i++){
                for (int j = y; j < y + len; j++){
                    arr[i][j] = ' ';
                }
            }
            return;
        }

        //len은 한블럭의 최대 크기이므로.
        if (len == 1){
            //1은 쪼갤 수 없는 블록이므로 그냥 *을 삽입
            arr[x][y] = '*';
            return;
        }

        int size = len / 3; //한블록의 크기
        int cnt = 0; //누적 별의 개수
        //
        for (int i = x; i < x + len; i += size){
            for (int j = y; j < y + len; j+= size){
                cnt++;
                if (cnt == 5){
                    //규칙을 보면 블럭이 4번 출력되고, 5번째에 무조건 공백이다. 이는 왜그런가하면 전부 다 3의 거듭제곱이고, 가운데에 구멍이 뚫린 것
                    /*
                    1 2 3
                    4 5 6
                    7 8 9    이기 때문에 5번짜리에 구멍이 뚫려있다. 그래서 cnt가 5가 되면 무조건 공백.
                    */
                    star(i, j, size, true); //현위치가 i j, 블록의 크기 size, 빈칸이어야 하기에 true로 바꾸어준다.
                }
                else {
                    star(i, j, size, false); //이는 공백이 아님
                }
            }
        }
    }
}
