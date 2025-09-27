import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class SWEA_4013_특이한자석_1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	// 자석을 회전시키는 횟수
	static int K;
	// 자석의 정보를 담을 것임
	static ArrayDeque<Integer>[] magnetic;
	// 회전 방향을 정할 것임
	static int[] rotateArray;
	// 결과값을 저장하기
	static int totalScore;
	
	// 결과값을 계산
	public static void calculate() {
		// 0번 인덱스를 보고 계산하기
		// N극이면 0점 S극이면 2^magneticNum
		// N극이 0, S극이 1
		for (int magneticNum = 0; magneticNum < 4; magneticNum++) {
			if (magnetic[magneticNum].peekFirst() == 1) {
				totalScore += (int) Math.pow(2, magneticNum);
			}
		}
	}
	
	// 정해진 회전 방향을 기반으로 자석을 회전시키는 메서드
	public static void rotateMagnetic() {
		for (int magneticNum = 0; magneticNum < 4; magneticNum++) {
			// 회전 방향이 시계
			if (rotateArray[magneticNum] == 1) {
				// 젤 뒤에껄 맨 앞으로
				magnetic[magneticNum].addFirst(magnetic[magneticNum].pollLast());
			}
			// 회전 방향이 반시계
			else if (rotateArray[magneticNum] == -1) {
				// 젤 앞에껄 맨 뒤로
				magnetic[magneticNum].addLast(magnetic[magneticNum].pollFirst());
			}
		}
	}
	
	// 자석의 회전 방향을 결정하는 메서드
	public static void setDirection(int magneticNum, int rotateDirection) {
		// 양끝의 자석은 한쪽만 확인하기
		// 자석날의 인덱스 0~7
		// 0번 자석의 경우 : 0번 자석의 2번인덱스와 1번자석의 6번 인덱스 확인
		// 1번 자석의 경우 : 0번 자석의 2번인덱스와 1번자석의 6번 인덱스 확인, 1번 자석의 2번 인덱스와 2번 자석의 6번 인덱스 확인
		// 2번 자석의 경우 : 1번 자석의 2번 인덱스와 2번 자석의 6번 인덱스 확인, 2번 자석의 2번 인덱스와 3번 자석의 6번 인덱스 확인
		// 3번 자석의 경우 : 2번 자석의 2번 인덱스와 3번 자석의 6번 인덱스 확인
		
		// 연쇄적으로 회전할수 도 있음 
		// 따라서 현재 입력된 자석과 회전 방향을 기준으로 모든 자석의 회전 방향 정하기 
		rotateArray[magneticNum] = rotateDirection;
		
		// 현재 자석 기준으로 왼쪽 확인, 1번 자석은 왼쪽 확인 안함
		// 즉, 현재의 자석 -1 이 1번자석 이상이어야 함
		// 그리고 회전 방향이 결정되지 않았다면
		if (0 <= magneticNum-1 && rotateArray[magneticNum-1] == 0) {
			// 왼쪽 자석의 2번과 현재 자석의 6번 비교
			// 극이 다르면 회전 시킴, 방향은 반대 
            if (magnetic[magneticNum - 1].toArray()[2] != magnetic[magneticNum].toArray()[6]) {
            	setDirection(magneticNum - 1, -rotateDirection);
            }
		}
		
		// 현재 자석 기준으로 오른쪽 확인, 4번 자석은 오른쪽 확인 안함
		// 즉, 현재의 자석 +1 이 4번 자석 이하여야함
		// 그리고 회전 방향이 결정되지 않아야 함
		if (magneticNum+1 <= 3 && rotateArray[magneticNum+1] == 0) {
			// 현재 자석의 2번과 오른쪽 자석의 6번 비교
			// 극이 다르면 회전 시킴, 방향은 반대 
			if (magnetic[magneticNum].toArray()[2] != magnetic[magneticNum + 1].toArray()[6]) {
				setDirection(magneticNum + 1, -rotateDirection);
            }
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스를 입력받음
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			// 자석을 회전 시킬 횟수 입력 받기
			K = Integer.parseInt(br.readLine().trim());
			
			// 자석의 초기 상태를 입력 받음
			// 자석은 4개, 톱니는 8개
			magnetic = new ArrayDeque[4];
			for (int index = 0; index < 4; index++) {
				magnetic[index] = new ArrayDeque<>();
			}
			for (int magneticCount = 0; magneticCount < 4; magneticCount++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int index = 0; index < 8; index++) {
					magnetic[magneticCount].add(Integer.parseInt(st.nextToken()));
				}
			}
			
			// K 번 회전
			for (int rotateCount = 0; rotateCount < K; rotateCount++) {
				// 자석의 번호는 1~4 -> 0~3으로 대응하기
				// 1은 시계방향회전, -1은 반시계 방향 회전
				// 자석의 번호, 회전방향 순으로 입력 주어짐
				st = new StringTokenizer(br.readLine().trim());
				
				// 회전 시킬 자석의 번호 -> 0~3으로 대응하기
				int magneticNum = Integer.parseInt(st.nextToken())-1;
				// 회전하는 방향
				int rotateDirection = Integer.parseInt(st.nextToken());
				
				// 회전 방향 저장할 배열
				rotateArray = new int[4];
				
				// 자석을 회전 시키기
				setDirection(magneticNum, rotateDirection);
				
				// 모든 자석의 회전 방향이 결정되면 회전 시키기 
				rotateMagnetic();
			}
			
			// 모든 작업이 완료됨 -> 계산
			totalScore = 0;
			calculate();
			sb.append('#').append(test_case).append(' ').append(totalScore).append('\n');
		}
		System.out.println(sb);
	}
}
