// 6131. 완전 제곱수 -> 브론즈 3

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main6131 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		// Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// int N = sc.nextInt(); //A의 제곱과 B의 제곱의 간격
		int N = Integer.parseInt(br.readLine()); // Scanner와 BufferedReader를 보면 BufferedReader가 더 빠름   
		
		int ans = 0; // 정답 count 
		//A >= B이고, 1 ~ 500까지
		for (int numB = 1; numB <= 500; numB++ ) {
			// A는 B이상 
			for (int numA = numB; numA <= 500; numA++) {
				if (numA*numA == numB*numB + N) {
					// A의 제곱은 B의 제곱보다 N만큼 크다.
					// 단순 완전탐색으로 구하기 
					ans++; 
				}
			}
		}
		
		System.out.println(ans);
	}
}
