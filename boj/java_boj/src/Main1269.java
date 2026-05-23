import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main1269 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        HashMap<Integer, Integer> map1 = new HashMap<>();
        int[] arr1 = new int[n];
        HashMap<Integer, Integer> map2 = new HashMap<>();
        int[] arr2 = new int[m];

        for (int i = 0; i < n; i++){
            int num = sc.nextInt();
            map1.put(num, 0);
            arr1[i] = num;
        }
        for (int j = 0; j < m; j++){
            int num = sc.nextInt();
            map2.put(num, 0);
            arr2[j] = num;
        }

        ArrayList<Integer> tmp1 = new ArrayList<>();
        for (int value : arr2){
            if (!map1.containsKey(value)){
                tmp1.add(value);
            }
        }
        ArrayList<Integer> tmp2 = new ArrayList<>();
        for (int value : arr1){
            if (!map2.containsKey(value)){
                tmp2.add(value);
            }
        }
        //차집합, set을 이용하면 가능했다.

        System.out.println(tmp1.size() + tmp2.size());
    }
}
