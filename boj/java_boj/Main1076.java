import java.util.HashMap;
import java.util.Scanner;

public class Main1076 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String first = sc.next();
        String second = sc.next();
        String third = sc.next();

        HashMap<String, Integer> hm1 = new HashMap<>();
        HashMap<String, Long> hm2 = new HashMap<>();

        hm1.put("black", 0);
        hm1.put("brown", 1);
        hm1.put("red", 2);
        hm1.put("orange", 3);
        hm1.put("yellow", 4);
        hm1.put("green", 5);
        hm1.put("blue", 6);
        hm1.put("violet", 7);
        hm1.put("grey", 8);
        hm1.put("white", 9);

        hm2.put("black", (long) Math.pow(10,0));
        hm2.put("brown", (long) Math.pow(10,1));
        hm2.put("red", (long) Math.pow(10,2));
        hm2.put("orange", (long) Math.pow(10,3));
        hm2.put("yellow", (long) Math.pow(10,4));
        hm2.put("green", (long) Math.pow(10,5));
        hm2.put("blue", (long) Math.pow(10,6));
        hm2.put("violet", (long) Math.pow(10,7));
        hm2.put("grey", (long) Math.pow(10,8));
        hm2.put("white", (long) Math.pow(10,9));

        System.out.println((hm1.get(first)*10 + hm1.get(second)) * hm2.get(third));
    }
}
