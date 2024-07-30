import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
public class Main18870 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        HashMap<Integer, Integer> hs = new HashMap<>();
        int rank = 0;
        for (int value : sorted){
            if (!hs.containsKey(value)){ //정렬된 배열을 순위를 매겨서 hashmap에 저장, 중복 없이.
                hs.put(value, rank);
                rank++;
            }
        }

        //기존 배열 순으로 출력을 해야하므로, 기존 배열을 키로 삼아서 랭크 출력
        for (int value : arr){
            sb.append(hs.get(value)).append(' ');
        }
        System.out.println(sb);


    }
}
