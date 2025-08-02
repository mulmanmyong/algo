// 1233. 주사위 -> 브론즈 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1233 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int S1 = Integer.parseInt(st.nextToken());
		int S2 = Integer.parseInt(st.nextToken());
		int S3 = Integer.parseInt(st.nextToken());
		
		// 합의 개수를 더할 배열
		int[] sumCount = new int[S1+S2+S3+1]; // 배열의 합을 저장할 배열 
		
		// 완전탐색으로 합한것의 값을 인덱스로 증가 시키기 
		for (int s1 = 1; s1 <= S1; s1++) {
			for (int s2 = 1; s2 <= S2; s2++) {
				for (int s3 = 1; s3 <= S3; s3++) {
					sumCount[s1+s2+s3]++;
				}
			}
		}
		
		// 가장 높은 빈도로 나온 주사위의 합 구하기  
		int maxCount = 0;
		int answer = 0;
		for (int sumIndex = 3; sumIndex < sumCount.length; sumIndex++) {
			if (sumCount[sumIndex] > maxCount) {
				maxCount = sumCount[sumIndex];
				answer = sumIndex;
				// 앞에서부터 보기 때문에, 같은 빈도수 일 경우 정답에는 더 작은 값이 저장 됨 
			}
		}
		
		System.out.println(answer);
	}
}
