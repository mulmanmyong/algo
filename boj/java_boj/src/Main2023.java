import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main2023 {
	
	public static int N;
	public static StringBuilder sb;
	
	// 소수만으로 이루어진 소수 찾기 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// N자리 숫자 중에서 소수인 수 찾기 
		N = Integer.parseInt(br.readLine());
		
		// 각 자리가 모두 소수여야 하므로 앞자리 수는 2 3 5 7만 가능 
		findPrimeNum(2, 1);
		findPrimeNum(3, 1);
		findPrimeNum(5, 1);
		findPrimeNum(7, 1);
		
		// 결과 출력
		System.out.println(sb);
	}
	
	public static void findPrimeNum(int primeNum, int digit) {
		
		// 기저 조건 : digit이 N에 도달하면 
		if (digit == N) {
			sb.append(primeNum).append("\n"); // 저장 
			return;
		}
		
		// 유도파트(구현부) : 다음자리 소수 만들어보기 
		// 짝수는 모두 2로 나누어 지므로 소수가 될 수 없음 ,
		// 뒷자리가 5인 경우는 무조건 5로 나누어지므로 소수가 될 수 없음
		// 따라서 5를 제외한 홀수를 뒤에 붙여 확인 
		for (int checkPrimeNum = 1; checkPrimeNum <= 9; checkPrimeNum += 2) {
			if (checkPrimeNum == 5)	continue; // 5가 붙으면 소수가 될 수 없음 
			int newPrimeNum = primeNum*10 + checkPrimeNum; // 새로운 소수를 만들어 봄 
			if (isPrime(newPrimeNum)) { // 소수가 맞으면 
				findPrimeNum(newPrimeNum, digit + 1); // 다음으로 
			}
		}
	}
	
	// 소수 확인하기 
	public static boolean isPrime(int num) {
		// 1은 소수가 아님 
		if (num < 2) {
			return false;
		}
		
		// 소수를 찾아내는 법은 2부터 자기자신의 제곱근까지 나누어보며 나누어떨어지는 지 확인하기
		// 제곱근까지 하는 이유는 제곱근 이상으로는 나누어떨어질 수가 없기 때문 ! 
		for (int divideNum = 2; divideNum <= (int)Math.sqrt(num); divideNum++) {
			if (num % divideNum == 0) {
				return false; // 나누어 떨어지면 소수가 아님!
			}
		}
		
		// 모든조건을 통과하면 소수임 
		return true;
	}
}
