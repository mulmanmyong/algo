import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1973 {
	
	public static int numCount; // 확인할 숫자의 개수
	public static int primeNumCount; // 소수의 개수를 계산   
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 확인할 숫자의 개수를 입력 받음 
		numCount = Integer.parseInt(br.readLine());
		primeNumCount = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < numCount; i++) {
			if (isPrime(Integer.parseInt(st.nextToken()))) { // 소수
				primeNumCount++;
			}
		}
		
		System.out.println(primeNumCount);
	}
	
	// 소수 판단  
	public static boolean isPrime(int num) {
		if (num < 2) {
			return false;
		}
		
		for (int i = 2; i <= (int)Math.sqrt(num); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		
		return true;
	}
}
