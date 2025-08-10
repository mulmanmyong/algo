import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main15652 {
	
	public static int N;
	public static int M;
	public static int[] sequence;
	public static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		// 1~N까지의 자연수  
		N = Integer.parseInt(st.nextToken());
		// M개를 고름 
		M = Integer.parseInt(st.nextToken());
		
		// 수열을 M개 담을 배열    
		sequence = new int[M];
		// 중복 불가 수열 
		// 다음에 오는 수는 현재의 수보다 크거나 같다 .
		// 중복 조합임 
		combination(1, 0);
		
		System.out.println(sb);
	}
	
	// 중
	public static void combination(int startNum, int depth) {
		
		// 기저 조건 : depth가 M과 동일해지면, 다 골랐다는 것 
		if (depth == M) {
			for (int val : sequence) {
				sb.append(val).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		// 수는 1에서 N까지 
		for (int num = startNum; num <= N; num++) {
			sequence[depth] = num;
			combination(num, depth + 1);
		}
	}
}
