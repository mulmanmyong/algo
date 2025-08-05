// 18532. 수학은 비대면강의입니다 -> 브론즈 2 

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main19532 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 연립방적식  
		// ax+by=c , dx+ey=f
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int f = Integer.parseInt(st.nextToken());
		
		// 완탐으로 풀면 시간초과 안나고 풀 수 있을까?
		// 해는 유일하고, -999이상 999 이하 
		for (int x = -999; x <= 999; x++) {
			for (int y = -999; y <= 999; y++) {
				if (a*x + b*y == c && d*x + e*y == f) {
					System.out.println(x+" "+y);
					return;
				}
			}	
		}
	}
}
