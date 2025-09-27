// 12891. DNA 비밀번호 - 실버 2

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main12891 {
	
	public static final int DNA_STRING_SIZE = 4;
	
	public static int dnaStringLength; // DNA 문자열의 길이  
	public static int subStringLength; // 부분 집합의 길이
	public static char[] dnaString; // DNA 문자열
	public static int[] minACGTArr; //  ACGT 최소 포함 개수 
	public static int[] checkACGTCount; // 현재 부분집합에서 ACGT의 개수 판단 
	public static int makePasswordCount;


	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 각 길이를 입력 받음 
		dnaStringLength = Integer.parseInt(st.nextToken());
		subStringLength = Integer.parseInt(st.nextToken());
	
		// DNA 문자열을 입력 받음
		dnaString = br.readLine().toCharArray();
		// ACGT가 존재해야하는 최소 개수
		minACGTArr = new int[DNA_STRING_SIZE];
		st = new StringTokenizer(br.readLine());
		for (int minACGTIndex = 0; minACGTIndex < DNA_STRING_SIZE; minACGTIndex++) {
			minACGTArr[minACGTIndex] = Integer.parseInt(st.nextToken());
		}
		
		// 0:A , 1:C, 2:G, 3:T 로 각각 인덱스에 연결 
		makePasswordCount = 0;
		
		// 초기 크기 세팅 
		checkACGTCount = new int[DNA_STRING_SIZE];
		for (int intervalIndex = 0; intervalIndex < subStringLength; intervalIndex++) {
			// ACGT가 나오는 개수 확인하기
			// DNA 문자열을 주어지기 때문에 ACGT 외엔 나오지 않음 
			checkACGTCount[ACGTtoNum(dnaString[intervalIndex])]++;
		}
		
		// 초기값 비밀번호로 사용가능한 지 확인 
		if (goodPassword()) { // 암호로 사용 가능하면?
			makePasswordCount++; // 만들 수 있는 개수 증가 
		}
		
		// for문을 통해 초기값의 다음 인덱스부터 시작 해서 끝까지 진행
		for (int addIndex = subStringLength; addIndex < dnaStringLength; addIndex++) {
			int removeIndex = addIndex-subStringLength;
			
			// addIndex는 추가해야하는 인덱스
			// removeIndex는 빼야하는 인덱스 
			checkACGTCount[ACGTtoNum(dnaString[addIndex])]++;
			checkACGTCount[ACGTtoNum(dnaString[removeIndex])]--;
			
			// 새로운 암호 가능한지 
			if (goodPassword()) {
				makePasswordCount++;
			}
		}
		
		// 최종 결과 출력
		System.out.println(makePasswordCount);
	}
	
	public static int ACGTtoNum(char acgt) {
		if (acgt == 'A') {
			return 0;
		}
		else if (acgt == 'C' ) {
			return 1;
		}
		else if (acgt == 'G' ) {
			return 2;
		}
		else {
			return 3;
		}
	}
	
	public static boolean goodPassword() {		
		for (int checkIndex = 0; checkIndex < DNA_STRING_SIZE; checkIndex++) {
			// 최소값보다 적게 나오면 만들 수 없음 
			if (checkACGTCount[checkIndex] < minACGTArr[checkIndex]) {
				return false;
			}
		}	
		return true;
	}
}
