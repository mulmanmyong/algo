import java.io.*;
import java.util.*;

public class BOJ_2034_반음 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	// 악보의 길이
	static int sheetLength;
	// 악보
	static int[] sheet;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		sheetLength = Integer.parseInt(br.readLine());
		sheet = new int[sheetLength];
		int move = 0; // 누적움직임을 담을 변수 
		for (int index = 0; index < sheetLength; index++) {
			// 근데 각각의 움직임보다 누적 움직임을 담기
			move += Integer.parseInt(br.readLine());
			// 우선 건반이 12개 있으니깐 12개를 넘어가면 모듈러 해서 자르기 
			sheet[index] = move % 12;
			// 근데 이제 음수보단 양수로 해야 계산하기 편함 
			sheet[index] = sheet[index] + 12;
			// 양수 입력의 경우 또 12를 넘어가니 또 다시 모듈러
			sheet[index] = sheet[index] % 12;
		}
		
		// 이제 이 떨어진 공간이 검은 건반이면 안되는 것임 
		// 인덱스가 0 ~ 11이라고 치면 검은 건반은 1, 3, 6, 8, 10 이다.
		// CDE FGAB는 0, 2, 4, 5, 7, 9, 11 이다.
		
		// 시작음으로 반복돌릴 흰색 건반 배열
		// 근데여러가지 경우의 수가 가능하면 알파벳순이랬으니,, 
		int[] whiteKey = {9, 11, 0, 2, 4, 5, 7};
		// 건반을 옮겼을 때 검은 건반인지 확인할 배열 
		boolean[] isBlackKey = new boolean[12];
		isBlackKey[1] = isBlackKey[3] = isBlackKey[6] = isBlackKey[8] = isBlackKey[10] = true;
		
		// 인덱스 기준으로 알파벳
		char[] alpha = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		// 피아노 기준
		char[] note = new char[12];
		for (int keyIndex = 0; keyIndex < whiteKey.length; keyIndex++) {
			note[whiteKey[keyIndex]] = alpha[keyIndex];
		}
		
		// 악보판단해보기 
		for (int keyIndex = 0; keyIndex < whiteKey.length; keyIndex++) {
			// 괜찮은 악보 
			boolean good = true;
			for (int sheetIndex = 0; sheetIndex < sheetLength; sheetIndex++) {
				// 검은건반이면 괜찮지 않은 악보 
				int chk = whiteKey[keyIndex] + sheet[sheetIndex];
				if (isBlackKey[chk % 12]) {
					good = false;
					break;
				}
			}
			
			// 괜찮은 악보면 악보 생성
			if (good) {
				char start = note[whiteKey[keyIndex]];
				char end = note[(whiteKey[keyIndex] + sheet[sheetLength-1]) % 12];
				sb.append(start).append(' ').append(end).append('\n');
			}
		}
		
		System.out.println(sb);
	}
}
