import java.util.Scanner;
public class Main2908 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();

        A = Integer.parseInt(new StringBuilder().append(A).reverse().toString());
        B = Integer.parseInt(new StringBuilder().append(B).reverse().toString());

        //Integer.parseInt -> string을 int형으로 변환을 시켜주는 것
        //StringBuilder는 문자열을 다루는 클래스이고, append에 넣은 값을 StringBuilder 타입으로 바꿔주고, 이 바뀐 문자열을 reverse()를 통해 뒤접어주고,
        //toString을 통해 String 타입으로 바꿔줌
        System.out.println(A > B ? A : B); //A > B를 했을 때 참이면 A를 출력, 그렇지 않으면 B를 출력
    }
}
