import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759_암호만들기_조합 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int L;
	static int C;
	static char[] alphabet;
	static char[] password;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 암호를 만들기 위해 알파벳을 입력받기
		// 이를 정렬
		// 그 후 조합으로 암호 생성
		
		st = new StringTokenizer(br.readLine().trim());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// 알파벳은 C개
		alphabet = new char[C];
		// 암호는 L개
		password = new char[L];
		
		// 알파벳 입력받기
		st = new StringTokenizer(br.readLine().trim());
		for (int index = 0; index < C; index++) {
			alphabet[index] = st.nextToken().charAt(0);
		}
		
		// 사전순으로 오름차순 정렬
		Arrays.sort(alphabet);
		
		// 조합으로 암호만들기 
		makePassword(0, 0);
		
		// 출력
		System.out.println(sb);
	}
	
	public static void makePassword(int start, int depth) {		
		// 종료조건 : 암호가 L만큼 만들어지면 (depth == L)
		if (depth == L) {
			// 해당 암호가 유효한지 판단
			if (isValid()) {
				// 유효하면 암호저장
				for (char c : password) {
					sb.append(c);
				}
				sb.append('\n');
			}
			return;
		}
		
		// 구현 
		// 조합을 이용
		for (int index = start; index < C; index++) {
			// 만약 남은 원소의 개수가 채워야 할 개수보다 적다면, 더 이상 조합을 만들 수 없음
			if (C - index < L - depth) {
		        break;
		    }
			
			// 지금 문자를 암호에 추가
			password[depth] = alphabet[index];
			// 다음으로 이동, 현재 선택한 알파벳 다음부터 선택 
			makePassword(index + 1, depth + 1);
		}
	}
	
	public static boolean isValid() {
		// 유효한 조건
		// 최소 한개의 모음, 최소 두개의 자음, 오름차순이어야함
		// 오름 차순은 정렬하고 조합을 시작했기 때문에 무조건 오름차순임 
		int aeiouCount = 0;
		int NOaeiouCount = 0;
		
		// 암호를 확인하여 개수 파악
		for (char c : password) {
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				aeiouCount++;
			}
			else {
				NOaeiouCount++;
			}
		}
		
		// 모음 1개이상, 자음 2개이상
		if (aeiouCount >= 1 && NOaeiouCount >= 2) {
			return true;
		}
		// 아니라면
		return false;
	}
}
