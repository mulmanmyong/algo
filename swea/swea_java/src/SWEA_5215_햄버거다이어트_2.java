import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5215_햄버거다이어트_2{
	
	public static int N;
	public static int L;
	public static int[] flavorScore;
	public static int[] calorie;
	public static int bestPerformance;
	public static int[][] area;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; ++test_case) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken()); // 제한 칼로리
			
			// 맛과 칼로리를 담을 각각의 배열
			flavorScore = new int[N];
			calorie = new int[N];
			for (int index = 0; index < N; index++) {
				st = new StringTokenizer(br.readLine());
				flavorScore[index] = Integer.parseInt(st.nextToken());
				calorie[index] = Integer.parseInt(st.nextToken());
			}
			
			// 최고의 만족도를 담을 변수, 초기값은 0
			bestPerformance = 0;
			// 부분집합으로 시작, 시작 인덱스 0, 맛점수 합 칼로리 합 으로 진행
			subset(0, 0, 0);
			
			// 결과 담기 
			sb.append("#"+test_case+" "+bestPerformance+"\n");
		}
		
		// 결과 출력
		System.out.println(sb);
	}
	
	public static void subset(int startIndex, int totalFlavorScore, int totalCalorie) {
		
		// 기저 조건, 제한칼로리L을 넘으면 안됨
		if (totalCalorie > L) { // 얘로 오면 안되는 구나.. 라는 것
			return;
		}
		
		// 어떻게서든 조합을 생성해서 끝에 도달
		if (startIndex == N) {
			// 현재 조합한 맛이 더 좋은 점수면
			if (bestPerformance < totalFlavorScore) {
				bestPerformance = totalFlavorScore;
			}
			return;
		}
		
		// 재료 조합을 시작 , 최대 칼로리가 있으니 현재의 재료를 넣는 경우와 안넣는 경우가 존재
		
		// 현재의 재료를 포함하지 않는 경우
		subset(startIndex + 1, totalFlavorScore, totalCalorie);
		
		// 현재의 재료를 넣는 경우
		subset(startIndex + 1, totalFlavorScore+flavorScore[startIndex], totalCalorie+calorie[startIndex]);
	}
}
