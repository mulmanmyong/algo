import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1952_수영장_2 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static final int TICKET_COUNT = 4;
	public static final int TOTAL_MONTH = 12;
	
	public static int[] ticketPrice;	// 이용권 가격
	public static int[] visitPlan;	// 월별 수영장 이용 계획
	public static int minPrice;	// 최소 비용
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			st = new StringTokenizer(br.readLine().trim());
			ticketPrice = new int[TICKET_COUNT];
			for (int i = 0; i < TICKET_COUNT; i++) {
				ticketPrice[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine().trim());
			visitPlan = new int[TOTAL_MONTH+1];
			for (int i = 1; i <= TOTAL_MONTH; i++) {
				visitPlan[i] = Integer.parseInt(st.nextToken());
			}
			
			// 상향식 dp
			int[] dp = new int[TOTAL_MONTH+1];
			dp[0] = 0;
			for (int i = 1; i <= TOTAL_MONTH; i++) {
				// 1일권
				dp[i] = dp[i-1] + visitPlan[i]*ticketPrice[0];
				// 1달권, 1일권과 비교하여 뭐가 더 싼지 비교하기
				dp[i] = Math.min(dp[i], dp[i-1]+ticketPrice[1]);
				
				// 3달권
				if (i >= 3) {
					dp[i] = Math.min(dp[i], dp[i-3]+ticketPrice[2]);
				}
				
				// 1년권 
				if (i == TOTAL_MONTH) {
					dp[i] = Math.min(dp[i], ticketPrice[3]);
				}
			}
			
			sb.append('#').append(test_case).append(' ').append(dp[TOTAL_MONTH]).append('\n');
		}
		System.out.println(sb);
	}
}
