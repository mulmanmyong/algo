// 17945. 통학의 신 -> 브론즈 3

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main17945 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		// A와 B는 x^2 + 2Ax + B =0의 계수 
		// 근의 공식을 이용 
		// -b 쁠마 루트b^2 - 4ac / 2 ->b =2A, a = B
		// 따라서 -A +- 루트(A^2 - B) 가 됨 근은 항상 정수라 했으므로 바로 계산값 하나씩 받음 
		int x1 = -A + (int)Math.sqrt(A*A - B);
		int x2 = -A - (int)Math.sqrt(A*A - B);
		
		if (x1 == x2) {
			// 중근이니 하나만 출력 
			System.out.println(x1);
		}
		else if (x1 > x2) {
			// 오름차순이니 x2먼저 출력  
			System.out.println(x2 + " " + x1);
		}
		else if (x1 < x2) {
			// 오름차순이니 x1먼저 출력  
			System.out.println(x1 + " " + x2);
		}
	}
}
