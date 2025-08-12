import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main17281 {
	
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static final int MAX_INNING = 9;
	public static int innings; // 이닝의 수  
	public static int currentScore;
	public static int[][] hittersState; // 타자들의 행동  
	public static int maxScore; // 얻을 수 있는 최대 점수 
	public static int outCount;
	public static int baseState;
	public static int[] LineUp;
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 이닝의 수를 입력
		innings = Integer.parseInt(br.readLine().trim());
		
		// 각 이닝별로 할 수 있는 행동 
		hittersState = new int[innings][MAX_INNING];
		for (int currentInning = 0; currentInning < innings; currentInning++) {
			// 타자들의 상태를 입력 받음 
			st = new StringTokenizer(br.readLine().trim());
			for (int hitter = 0; hitter < MAX_INNING; hitter++) {
				hittersState[currentInning][hitter] = Integer.parseInt(st.nextToken());
			}
		}
		
		LineUp = new int[9];
		for (int backnumber = 0; backnumber < 9; backnumber++) {
			LineUp[backnumber] = backnumber;
		}
		
		maxScore = 0;
		// 라인업을 생성하고, 라인업 다 짜면 각 라인업마다 경기 진행 
		// nextPermutation을 이용해서 할 수도 있을 듯?
		do {
			// 1번선수는 4번 타자 고정  
			if (LineUp[3] == 0) { // 1번선수가 4번타자면 
				// 게임 진행
				playBall();
				maxScore = Math.max(maxScore, currentScore);
			}
			
		} while (nextPermutation());
		
		sb.append(maxScore);
		System.out.println(sb);
	}
	
	public static boolean nextPermutation() {

		// 꼭대기 찾기 
        int i = LineUp.length - 1;
        while (i > 0 && LineUp[i - 1] >= LineUp[i]) {
            i--;
        }

        if (i == 0) {
            return false;
        }

        // 꼭대기보다 약간 큰 값 찾기  
        int j = LineUp.length - 1;
        while (LineUp[i - 1] >= LineUp[j]) {
            j--;
        }

        // 교환하기 
        swap(i - 1, j);

        // 순서 뒤집기 
        int k = LineUp.length - 1;
        while (i < k) {
            swap(i, k);
            i++;
            k--;
        }

        return true;
    }

    public static void swap(int i, int j) {
        int temp = LineUp[i];
        LineUp[i] = LineUp[j];
        LineUp[j] = temp;
    }
	
	public static void playBall() {
		// 아웃 안타(1루타) 2루타  3루타  홈런
		// 0 1 2 3 4 
		
		currentScore = 0;
		int hitterIndex = 0;
		
		for (int currentInning = 0; currentInning < innings; currentInning++) {
			// 이닝이 바뀌면 아웃카운트도 초기
			outCount = 0;
			// 베이스에 있는 주자를 어떻게 처리할 것인가? -> 비트 마스킹 
			baseState = 0;
			
			// 아웃카운트가 3개가 되면 이닝 바뀜 
			while (outCount < 3) {
				int currentHitter = LineUp[hitterIndex];
				int hitResult = hittersState[currentInning][currentHitter];
				
				// 0이면 아웃카운트 증가 
				if (hitResult == 0) {
					outCount++;
				}
				else {
					// 타자가 타석에 섬 - 0번비트에 1을 킴!
					baseState |= 1;
					// 타격을 함!!!!!! shift 연산 
					baseState <<= hitResult;
					
					// 0 1 2 3 까지가 점수가 안나는 비트,
					// 3루에 있을 때 홈런을 맞으면 7번비트까지 밀림 
					// 따라서 4 5 6 7 비트에 불을 켜져있는 지 확인하기. 켜져있으면 득점으로 인정 
					for (int bit = 4; bit < 8; bit++) {
						// 확인해봤을 때 해당 비트가 1이 켜져있으면 점수 증가
						if ((baseState & (1 << bit)) != 0) { // 비교했는데 0이 아니라는 건 1이 켜져있다는 것!
							currentScore++; // 점수 증가 
							// 들어왔으니 주자 삭세
							baseState &= ~(1 << bit);
						}
					}
				}
				
				// 다음 타자로 이동 
				hitterIndex = (hitterIndex + 1) % 9;
			}
		}
	}
}
