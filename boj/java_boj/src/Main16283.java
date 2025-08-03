// 16283. Farm -> 브론즈 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main16283 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
	
		// 양은 정확히 a그램 먹음, 염소는 정확히 b그램 먹음 
		// n은 양과 염소의 총 마리 수, 소비한 전체 사료양이 w그램 
		// 양의 수를 n까지 증가 시키기 
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		
		// 가능한 경우가 2개 이상이거나 없으면 -1 리턴
		int ansSheep=0, ansGoat=0;
		int chk=0;
		
		// 양과 염소는 최소 1마리 이상 
		for (int sheep = 1; sheep < n; sheep++) {
			if (chk >= 2)	break; // 가능한 경우 2개 이상이면 안됨 
			
			int goat = n-sheep;
			if (a*sheep + b*goat == w) {
				ansSheep = sheep;
				ansGoat = goat;
				chk++; // 가능한 경우의 수 확인 
			}
		}
		
		if (chk == 1) {
			System.out.println(ansSheep+" "+ansGoat);
		}
		else {
			System.out.println(-1);
		}
	}
}
