// 14697. 방 배정하기 -> 브론즈 2 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14697 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		// 방의 정원을 나타내는 A, B, C 전체 학생 수 N 
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] roomType = {A, B, C};
		int N = Integer.parseInt(st.nextToken());
		
		// 방 정원 == 방 안 침대 수
		// 방 안 침대 수 모두 꽉차도록 배정하기, 가능하면 1, 불가능하면 0 
		// 한 가지의 종류의 방 타입이나 두 가지의 방 타입만 사용하는 것도 가능  
		// 반복을 돌며 계산하기  
		// 각 방을 사용할 지 안할지로,,
		// 3중 for문으로 A가 가능한 개수까지 B가 가능한 개수까지 C가 가능한 개수까지
		// 완전탐색으로 비교 
		for (int AIndex = 0; AIndex <= N / A; AIndex++) {
			for (int BIndex = 0; BIndex <= N / B; BIndex++) {
				for (int CIndex = 0; CIndex <= N / C; CIndex++) {
					if (AIndex*A + BIndex*B + CIndex*C == N) {
						System.out.println(1);
						return; // 계산 가능 
					}
				}
			}
		}
		
		// 종료가 안되었으면 계산 불가능 
		System.out.println(0);
	}
}
