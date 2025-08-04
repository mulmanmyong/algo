// 16561. 3의 배수 -> 브론즈 2 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main16561 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		// N은 임의의 3의 배수 자연수 
		// 3의 배수 3개로 쪼갤 수 있는 것의 개수를 구하는 것
		int ans = 0;
		
		/*
		 // 완전탐색으로 돌리기 -> 오래 걸림 
		for (int i = 3; i <= N-6; i += 3) {
			for (int j = 3; j <= N-6; j += 3) {
				for (int k = 3; k <= N-6; k += 3) {
					if (i+j+k == N) {
						ans++;
					}
				}
			}
		}
		*/
		
		// 최소 다른 2개의 수의 합 최소 6, 따라서 i는 N-6까지 
		for (int i = 3; i <= N - 6; i += 3) {
			// j는 i 숫자와 나머지 숫자 3까지 N-i-3 
			for (int j = 3; j <= N - i - 3; j += 3) {
				// 남은 수는 지금까지 나온수, 이게 3이상이고 3으로 나누어떨어지면 됨 
				int k = N - i - j;
				if (k >= 3 && k % 3 == 0) {
					ans++;
				}
			}
		}

		System.out.println(ans);
	}
}
