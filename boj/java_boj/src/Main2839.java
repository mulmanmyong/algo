import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2839 {
	
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int sugarKg;
	public static int remainKg;
	public static int count;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 봉지는 5키로와 3키로가 있음. 최대한 적은 봉지를 사용하여 만드는 경우는 ? 
		// 그리디하게 5키로로 먼저 담아보면서 3키로가 담아지는 지 확인 
		sugarKg = Integer.parseInt(br.readLine().trim());
		
		count = -1;
		// 즉, 5키로를 담을 수 있는 최대 개수부터 시작하여 하나씩 줄여보기 
		for (int canFiveCount = sugarKg / 5; canFiveCount >= 0; canFiveCount--) {
			// 5키로 봉지로 담고 남은 KG
			remainKg = sugarKg - (canFiveCount*5);
			
			// 3키로로 나누어떨어지는 지 확인 
			if (remainKg % 3 == 0) {
				// 나누어떨어지면 가능 
				count = canFiveCount + (remainKg / 3);
				break;
			}
		}
		
		// 중간에 가능한 경우가 없으면 초기값 -1로 출력 
		sb.append(count);
		System.out.println(sb);
	}
}
