import java.util.HashMap;
import java.util.Scanner;

public class Main1620 {
    static int num;
    public static void main(String[] args) {
        //map을 이용하여 이름과 수를 연관짓기
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        int m = sc.nextInt();

        HashMap<String, Integer> strhs = new HashMap<>(); //key가 이름
        HashMap<Integer, String> inths = new HashMap<>(); //key가 번호
        for (int i = 1; i <= n; i++) {
            String name = sc.next();
            strhs.put(name, i);
            inths.put(i, name);
        }

        for (int i = 1; i <= m; i++){
            String find = sc.next();
            if (isNum(find)){
                sb.append(inths.get(Integer.parseInt(find))).append('\n');
            }
            else {
                sb.append(strhs.get(find)).append('\n');
            }
        }
        System.out.print(sb);
    }

    //입력한 것이 Integer인지 String인지
    public static boolean isNum (String find){
        if (find == null){
            return false;
        }
        try {
            Double.parseDouble(find);
            return true;
        }
        catch (NumberFormatException nfe){
            return false;
        }
    }
}
