import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main10926 {
	
	public static BufferedReader br;
	public static StringBuilder sb;
	public static String sameName;
	
	public static void main(String[] args) throws IOException{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 이미 존재하는 아이디가 입력으로 주어짐 
		// 중복되는 아이디는 중복된 아이디 뒤에 ??!라고 놀람표시가 붙어서 출력됨 
		// 이를 구하시오 
		
		// 준복되는 이름을 입력받기 
		sameName = br.readLine().trim();
		// 중복되는 이름 뒤에 놀람표시 추가 
		sb.append(sameName).append("??!");
		
		// 출력
		System.out.println(sb);
	}
}
