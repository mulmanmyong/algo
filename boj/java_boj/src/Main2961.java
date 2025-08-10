import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main2961 {
	
	public static int foodCount; // 재료 개수 
	public static int[] sour; // 신 맛
	public static int[] bitter; // 쓴 맛 
	public static long minDifference; // 차이를 담을 값, 최소값을 업데이트 함 
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();		
		
		foodCount = Integer.parseInt(br.readLine().trim());
		
		sour = new int[foodCount];
		bitter = new int[foodCount];
		for (int foodIndex = 0; foodIndex < foodCount; foodIndex++) {
			st = new StringTokenizer(br.readLine().trim());
			sour[foodIndex] = Integer.parseInt(st.nextToken());
			bitter[foodIndex] = Integer.parseInt(st.nextToken());
		}
		
		// 파라미터로 현재의 음식번호, 그리고 음식을 섞은 결과값을 담음  
		minDifference = Integer.MAX_VALUE;
		mixFood(0, 1, 0); // 음식번호, 신맛, 쓴맛 인데 신맛은 곱연산을 하므로 1 
		
		sb.append(minDifference);
		System.out.println(sb);
	}
	
	// 음식을 조합하여 만들기 => 조합의 방법과 부분집합의 방법
	public static void mixFood(int foodNum, long mixSourFood, long mixBitterFood) {
		
		// 기저 조건 : 음식을 끝까지 섞으면 최소값을 업데이트, 
		// 단 하나도 안섞은 경우는 제외(신맛은 1이 섞이면 1이지만, 쓴맛은 1이 섞이면 0이 될수 없다) 
		if (foodNum == foodCount) { // 끝까지 탐색, 끝까지 섞어봤다는 거지 
			if (mixBitterFood > 0l) { // 하나라도 섞이면 
				minDifference = Math.min(minDifference, Math.abs(mixSourFood-mixBitterFood));
			}
			return;
		}
		
		// 유도파트 : 음식을 섞을 지 말지 신 맛은 곱하고, 쓴맛은 더한다
		// 현재 음식을 섞지 않음 
		mixFood(foodNum+1, mixSourFood, mixBitterFood);
		// 현재 음식을 섞음 
		mixFood(foodNum+1, mixSourFood*sour[foodNum], mixBitterFood+bitter[foodNum]);
	}
}
