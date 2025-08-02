// 1075. 나누기 -> 브론즈 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1075 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int F = Integer.parseInt(br.readLine());
		
		// N을 F로 나누어 떨어지게 하려고 함  
		// 근데 이때 뒤 두자리를 가능한 작게할것 
		// N의 뒷자리 2가지를 00으로 재구성  
		N = N/100*100; 
		
		// 0부터 99까지 돌며 나누어 떨어지는 지점 찾기  
		int num;
		for (num = 0; num <= 99; num++) {
			if ((N+num)%F == 0) {
				break;
			}
		}
		
		String ans = Integer.toString(num); // 문자열로 변환  
		if (ans.length() == 1) {
			// 한자리면앞에 0을 출략
			System.out.print("0");
		}
		System.out.println(ans);
	}
}
