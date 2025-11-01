import java.io.*;
import java.util.*;

public class BOJ_9663_Nqueen {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	// N의 크기
	static int N;
	// 정답
	static int ans;
	
	// 열에 놓았는 지 확인할 배열 
	static boolean[] colChk;
	// 대각선
	static boolean[] diagChk1; // / 방향 => r+c로 확인 가능 
	static boolean[] diagChk2; // \ 방 => r-c를 하면 음수가 나오니깐 여기서 배열의 크기를 더하는 것으로 확인 가능 
	
	static void nqueen(int r) {
		// 종료 조건 : 끝까지 놓으면!
		if (r == N) {
			ans++;
			return;
		}
		
		// 열 확인 
		for (int c = 0; c < N; c++) {
			// 열에 이미 놓은 적이 있으면!
			if (colChk[c])	continue; // 안댐
			// 대각선은 차가 같으면 안되는 듯 
			else if (diagChk1[r+c] || diagChk2[r-c+N-1])	continue; // 안댐
			
			// 괜찮다면 여기에 놓자
			colChk[c] = true; 
			diagChk1[r+c] = true;
			diagChk2[r-c+N-1] = true;
			
			nqueen(r+1);
			
			colChk[c] = false; 
			diagChk1[r+c] = false;
			diagChk2[r-c+N-1] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// N을 입력 받음 
		N = Integer.parseInt(br.readLine().trim());
		
		// queen은 상 하 좌 우 대각선 모두 공격가능하다
		// 이를 못 놓게 하려면 열에 대한 확인배열
		// 그리고 해당 기준으로 대각선에 있는 지 확인하기
		// 행은 확인안하는 이유는 어차피 끝까지 도달하기 때문 ㅋㅋ 
		
		// 열 확인 
		colChk = new boolean[N];
		// 대각선 확인
		diagChk1 = new boolean[2*N];
		diagChk2 = new boolean[2*N];
		
		ans = 0;
		nqueen(0);
		
		sb.append(ans);
		System.out.println(sb);
	}
}
