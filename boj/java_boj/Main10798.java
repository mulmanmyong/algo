import java.util.Scanner;
public class Main10798 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int max = 0;

        char[][] arr = new char[5][15];
        for (int i = 0; i < 5; i++){
            String s = sc.next();
            max = Math.max(max, s.length());
            for (int j = 0; j < s.length(); j++){
                arr[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < max; i++){
            for (int j = 0; j < 5; j++) {
                if (arr[j][i] == '\0') {// \0이 char 배열의 초기값
                    continue; //제일 큰 배열 기준으로 반복을 함, 시간복잡도 때문에, 그리고 또한 입력이 되지 않는 부분이 나오면 패스를 하도록 설정
                }
                System.out.print(arr[j][i]);
            }
        }
    }
}
