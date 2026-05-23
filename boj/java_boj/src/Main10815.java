//테스트의 개수가 많으므로 완전탐색을 진행하면 시간초과가 난다.
//이진탐색을 이옹할 것
//최대한 빠르게 하기 위해 buffer를 사용
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Main10815 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m;
    static int[] arr1;

    public static void main(String[] args) throws IOException{
        n = Integer.parseInt(br.readLine());
        arr1 = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++){
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr1);
        m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine()); //오버라이팅
        int num;
        for (int i = 0; i < m; i++){
            num = Integer.parseInt(st.nextToken());
            if (binarySearch(num)){
                bw.write("1 ");
            }
            else {
                bw.write("0 ");
            }
        }
        bw.close();
        br.close();
    }

    //이진탐색 메서드
    private static boolean binarySearch(int num){
        int lindex = 0;
        int rindex = n - 1;
        int mindex;

        while(lindex<= rindex){
            mindex = (lindex + rindex) / 2;
            int mid = arr1[mindex];

            if(num < mid) {
                rindex = mindex - 1;
            }
            else if(num > mid) {
                lindex = mindex + 1;
            }
            else {
                return true;
            }
        }
        return false;
    }
}
