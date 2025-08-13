import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main16435 {
	
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int foodCount;
	public static int initLength;
	public static int[] fruitBox;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		foodCount = Integer.parseInt(st.nextToken());
		initLength = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine().trim());
		fruitBox = new int[foodCount];
		for (int fruitIndex = 0; fruitIndex < foodCount; fruitIndex++) {
			fruitBox[fruitIndex] = Integer.parseInt(st.nextToken());
		}
		
		// 정렬을 해봅시다 
		Arrays.sort(fruitBox);
		for (int length : fruitBox) {
			// 현재의 길이보다 컸으면, 더이상 먹을 수 있는 과일없음
			if (initLength < length) {
				break;
			}
			else { // 그 외에는 작거나 같은것이므로 과일을 먹을 수 있음 
				initLength++;
			}
		}
		
		sb.append(initLength);
		System.out.println(sb);
	}
}
