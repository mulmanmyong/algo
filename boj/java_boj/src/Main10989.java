//counting 정렬을 사용하기를 추천한 거 같으니 counting정렬을 사용
//counting 정렬은 시간복잡도가 O(n)
//메모리도 절약하기 위해 최대한 가볍고 빠른 것으로 이용 scanner 대신 buffer 사용
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
public class Main10989 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] cnt = new int[10001]; //int 배열은 0으로 초기화

        for (int i = 0; i < n; i++){
            cnt[Integer.parseInt(br.readLine())] ++; //정렬할 숫자의 인덱스를 1만큼 증가시킴.
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 10001; i++){
            while (cnt[i] > 0){ //0을 초과할 때만 출력을 하므로, 카운트가 안된 부분은 출력이 안되게된다.
                sb.append(i).append('\n'); //count 배열의 index가 처음에 입력한 숫자. cnt 배열은 정렬을 해둔 상태인 것
                cnt[i]--;
            }
        }
        System.out.println(sb);
    }
}
