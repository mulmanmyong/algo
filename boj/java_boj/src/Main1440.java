// 1440. 타임머신 -> 브론즈 2 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main1440 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), ":");
	
		int ans = 0;
		for (int i = 0; i < 3; i++) {
			// 현재의 숫자를 받아옴 
			int num = Integer.parseInt(st.nextToken());
			
			// 01:00:00은 2가 나옴, 즉, 시가 가능하면 그냥 답에 +2를 해주면 됨 
			// 시는 1 ~ 12만 가능 
			if (1 <= num && num <= 12) {
				ans += 2;
			}
			if (59 < num) {
				ans = 0;
				break; // 시간 범위에 해당하지 않음 !!! 문제있는 것임 
			}
		}
		System.out.println(ans);
	}
}
