// 14568. 2017 연세대학교 프로그래밍 경시대회 -> 브론즈 3 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main14568 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		// 사탕의 총 개수 N
		// int N = sc.nextInt();
		int N = Integer.parseInt(br.readLine());
		
		// 택희, 영훈이, 남규 사탕 N개 나눠받음 
		// 남는 사탕은 없어야 함  
		// 남규는 영훈이보다 2개 이상 많은 사탕을 가져야 함  
		// 셋 중 사탕을 0개 받는 사람은 없어야 함  
		// 택희는 홀수개 받으면 안됨  
		// a 택희 b 영훈  c 남규 
		int ans = 0; // 경우의 수를 담을 변수 
		for (int a = 2; a <= N-4; a += 2) { // 택희는 짝수 개 
			for (int b = 1; b <= N-5; b++) { // 영훈은 남규보다 2개이상 적게 받음 
				if (N-a-b >= b+2) {
					// 남은 사탕의 개수가 남규의 사탕 개수
					// 그렇다는 건 영훈이의 사탕개수보다 2개이상 받아야하기에 그보다 많아야 함 
					ans++;
				}
			}
		}
		
		System.out.println(ans);
	}
}
