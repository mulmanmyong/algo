import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;

public class Main2751 {
    public static void main(String[] args) {
        //Arrays.sort()를 쓰면 시간초과가 난다. 이는 Scanner의 문제가 아니라 Arrays.sort()의 정렬 시간복잡도의 문제이다.
        //dual pivot quicksort이다.
        //Collections.sort()를 사용해야한다. 이는 timsort로 합병 및 삽입정렬 알고리즘을 합친 하이브리드 정렬이다.
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int n = sc.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++){
            arr.add(sc.nextInt());
        }
        Collections.sort(arr);

        for (int i : arr){
            sb.append(i).append('\n');
        }
        System.out.println(sb);
        //Arraylist에서 각 인덱스별로 삽입하는 것은 .add메서드, 인덱스로 출력하는 것은 .get(index) 메서드

        //System.out.println()도 시간이 오래 걸리기 때문에 여러번 사용을 하게 되면 시간초과가 나게 된다. 그래서 한번에 출력을 하기 위해서
        //하나의 스트링으로 만들어서 한번만 출력을 하게 하면 된다.

    }
}
