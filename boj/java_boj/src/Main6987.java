import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main6987 {
	
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int[][] gameResults;
	public static int[][] playGame;
	public static int matchCount;
	public static int checkCount;
	public static boolean isPossible;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 각 팀별로 게임 대진표를 미리 만들어두기 
		playGame = new int[15][2]; // 총 진행할 매치의 수는 6C2 15가지 
		matchCount = 0;
		for (int team1 = 0; team1 < 6; team1++) {
			for (int team2 = team1+1; team2 < 6; team2++) {
				playGame[matchCount][0] = team1;
				playGame[matchCount][1] = team2;
				matchCount++;
			}
		}
		
		// 총 4개의 경우의 수를 입력받음 
		for (int test_case = 1; test_case <= 4; ++test_case) {
			// 게임의 결과를 입력받음 
			gameResults = new int[6][3]; // 6개의 팀, (승 무 패)
			st = new StringTokenizer(br.readLine().trim());
			boolean checkFlag = false;
			for (int teamIndex = 0; teamIndex < 6; teamIndex++) {
				checkCount = 0; // 경기 수가 충족됐는지 확인 
				for (int resultIndex = 0; resultIndex < 3; resultIndex++) {
					gameResults[teamIndex][resultIndex] = Integer.parseInt(st.nextToken());
					checkCount += gameResults[teamIndex][resultIndex];
				}
				
				// 한 팀당 경기수 5게임 충족됐는지 확인 
				if (checkCount != 5) {
					// 충족안됐으면 불가능한 경우의 수임 0 출력 
					sb.append('0').append(' ');
					checkFlag = true;
					break;
				}
			}
			
			if (checkFlag) {
				continue;
			}
			
			// 0번째 경기부터 가능한지 판단 
			isPossible = false;
			isPossibleGame(0);
			
			// 결과 입력 
			if (isPossible) {
				sb.append('1').append(' ');
			}
			else {
				sb.append('0').append(' ');
			}
		}
		
		System.out.println(sb);
	}
	
	public static void isPossibleGame(int matchIndex) {
		
		// 이미 모든 경우의 수를 찾았다면 return
		if (isPossible) {
			return;
		}
		
		// 끝까지 도달했다는 것은 모든 경기가 다 가능하다는 것 
		if (matchIndex == matchCount) {
			isPossible = true;
			return;
		}
		
		// 경기에 참여하는 팀 
		int team1 = playGame[matchIndex][0];
		int team2 = playGame[matchIndex][1];
		// 승 0, 무 1, 패 2 
		// team1 승, team2 패 
		if (gameResults[team1][0] > 0 && gameResults[team2][2] > 0) {
			// 해당 경우의 수는 감소 
			gameResults[team1][0]--;
			gameResults[team2][2]--;
			// 다음 매치 진행 
			isPossibleGame(matchIndex + 1);
			// 원복 
			gameResults[team1][0]++;
			gameResults[team2][2]++;
		}
		// team1 무, team2 무
		if (gameResults[team1][1] > 0 && gameResults[team2][1] > 0) {
			// 해당 경우의 수는 감소 
			gameResults[team1][1]--;
			gameResults[team2][1]--;
			// 다음 매치 진행 
			isPossibleGame(matchIndex + 1);
			// 원복 
			gameResults[team1][1]++;
			gameResults[team2][1]++;
		}
		// team1 패, team2 승
		if (gameResults[team1][2] > 0 && gameResults[team2][0] > 0) {
			// 해당 경우의 수는 감소 
			gameResults[team1][2]--;
			gameResults[team2][0]--;
			// 다음 매치 진행 
			isPossibleGame(matchIndex + 1);
			// 원복 
			gameResults[team1][2]++;
			gameResults[team2][0]++;
		}
	}
}
