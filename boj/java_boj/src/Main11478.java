import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main11478 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        Set<String> set = new HashSet<>();

        //set은 똑같은 값을 한번이든 두번이든 세번이든 저장은 한번만 된다. 그러한 특성을 이용하여서 부분문자열을 다 입력을 시도!!
        //하지만 같은 값은 한번만 저장이 되므로 set에는 중복을 제외한 서로 다른 부분문자열들만 존재한다.
        //substring은 특정 문자열을 잘라내는데 사용, slice같은
        for (int i = 0; i < s.length(); i++){
            for (int j = i + 1; j <= s.length(); j++){
                set.add(s.substring(i, j)); //substring에서 범위가 i, j이면 i포함 i부터 j-1까지이다, 모든 인덱스 계산은 동일
            }
        }
        System.out.println(set.size());
    }
}
