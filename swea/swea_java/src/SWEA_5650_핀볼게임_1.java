import java.io.*;
import java.util.*;

public class SWEA_5650_핀볼게임_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

/*
 * [문제설명]
 * NxN 필볼 게임판에 정사각형 블록과 4가지 형태 삼각형 블록들 섞여 있고,
 * 여기에 추가적으로 웜홀과 블랙홀 존재
 * 
 * 블록들은 1~5로 주어짐
 * 1 : 위에서 오면 우측으로 방향이 바뀌고, 우측에서 오면 위로 방향이 바뀜. 그 외에 평평한 면은 방향 반대로.
 * 2 : 아래에서 오면 우측으로 방향이 바뀌고, 우측에서 오면 아래로 방향이 바뀜. 그 외에 평평한 면은 방향 반대로.
 * 3 : 아래에서 오면 좌측으로 방향이 바뀌고, 좌측에서 오면 아래로 방향이 바뀜. 그 외에 평평한 면은 방향 반대로.
 * 4 : 위에서 오면 좌측으로 방향이 바뀌고, 좌측에서 오면 위로 방향이 바뀜. 그 외에 평평한 면은 방향 반대로.
 * 5 : 온 방향의 방대 방향으로 바뀜 
 * 
 * 웜홀은 6~10으로 주어짐
 * 한쌍의 숫자로 주어지며 6으로 들어가면 6으로 나오게 되는 구조 -> 방향은 바뀌지 않음.
 * 
 * 블랙홀은 -1로 주어지며 빠지면 핀볼이 사라지어 게임 끝남
 * 
 * 0은 빈칸이고, 여기서 시작가능
 * 출발지는 빈칸 위의 임의의 위치 임의의 방향으로 시작이 가능할 때 
 * 게임에서 얻을 수 있는 점수의 최댓값을 구하여라
 * 
 * 점수를 얻는 조건은 벽에 부딪히거나 블록에 부딪힌 횟수 
 * 종료조건은 출발지에 다시 돌아오거나, 블랙홀에 빠졌을 때임
 * 
 * [입력]
 * 첫 줄에 테스트 케이스 T 주어짐
 * 	각 테스트 케이스의 첫째 줄에 N주어짐
 * 	그 이후 N줄에 걸쳐 핀볼 게임판 주어짐
 * 
 * [출력]
 * 게임에서 얻을 수 있는 최대 점수 구하기
 * 
 * [딸깍]
 * 빈칸을 완탐으로 하여 출발지를 지정하고, 방향도 4방향을 모두 지정하여 시뮬레이션을 진행
 * 시뮬레이션 종료 조건을 다시 출발지점에 돌아오거나, 블랙홀(-1)을 마주했을 때 종료하고 점수를 return하는 방식
 * 
 * 빡구현이네 구현시작
 */
	
	// 한변의 길이 N
	static int N;
	// 게임판
	static int[][] board;
	// 시작 지점 저장하기ㅏ
	static ArrayList<int[]> start;
	
	// 방향배열 (상 우 하 좌)
	static int[] deltaRow = {-1, 0, 1, 0};
	static int[] deltaCol = {0, 1, 0, -1};
	
	// 게임 시작
	public static int game(int startRow, int startCol, int startDir) {
		// 초기값 세팅
		int score = 0;
		int row = startRow;
		int col = startCol;
		int dir = startDir;
		
		// 게임이 종료될 때까지
		while (true) {
			
			// 현재 방향으로 한 칸 이동
			row += deltaRow[dir];
			col += deltaCol[dir];
			
			// 범위를 벗어나면? 벽에 부딪힌 것임 방향 전환
			if (row < 0 || row >= N || col < 0 || col >= N) {
				// 벽 부딪혔으니 점수 증가
				score++;
				
				// 원래 자리로
				row -= deltaRow[dir];
				col -= deltaCol[dir];
				
				// 방향도 전환
				dir = (dir + 2) % 4;
			}
			
			// 블랙홀을 만나면 종료
			if (board[row][col] == -1) {
				break;
			}
			
			// 시작 지점에 돌아와도 종료
			if (row == startRow && col == startCol) {
				break;
			}
			
			// 종료조건이 아닌 무언가를 만났다면
			// 뭔지 봅시다
			// 방향은 상 후 하 좌
			switch (board[row][col]) {
				case 1:
					score++;
					// 1 : 위에서 오면 우측으로 방향이 바뀌고, 우측에서 오면 위로 방향이 바뀜
					if (dir == 2) dir = 1;
					else if (dir == 3) dir = 0;
					// 그 외에는 평평한 면을 마주하게 됨 고대로 변경
					else dir = (dir + 2) % 4;
					break;
				case 2:
					score++;
					// 2 : 아래에서 오면 우측으로 방향이 바뀌고, 우측에서 오면 아래로 방향이 바뀜
					if (dir == 0) dir = 1;
					else if (dir == 3) dir = 2;
					// 그 외에는 평평한 면을 마주하게 됨 고대로 변경
					else dir = (dir + 2) % 4;
					break;
				case 3:
					score++;
					// 3 : 아래에서 오면 좌측으로 방향이 바뀌고, 좌측에서 오면 아래로 방향이 바뀜
					if (dir == 0) dir = 3;
					else if (dir == 1) dir = 2;
					// 그 외에는 평평한 면을 마주하게 됨 고대로 변경
					else dir = (dir + 2) % 4;
					break;
				case 4:
					score++;
					// 4 : 위에서 오면 좌측으로 방향이 바뀌고, 좌측에서 오면 위로 방향이 바뀜
					if (dir == 2) dir = 3;
					else if (dir == 1) dir = 0;
					// 그 외에는 평평한 면을 마주하게 됨 고대로 변경
					else dir = (dir + 2) % 4;
					break;
				case 5:
					score++;
					// 5 : 온 방향의 방대 방향으로 바뀜
					dir = (dir + 2) % 4;
					break;
				case 6: case 7: case 8: case 9: case 10: 
					boolean flag = false;
					// 웜홀임. 다른 방향 찾아서 위치 변경
					for (int i = 0; i < N; i++) {
						for (int j = 0; j < N; j++) {
							// 같은 위치 패스
							if (i == row && j == col) continue;
							
							// 같은 위치가 아닌 동일한 숫자면 해당 위치로 변경
							if (board[i][j] == board[row][col]) {
								flag = true;
								row = i;
								col = j;
								break;
							}
						}
						if (flag) break;
					}
					break;
			}
		}
		
		return score;
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			
			// 한변의 길이 N을 입력받음
			N = Integer.parseInt(br.readLine().trim());
			
			// 게임판을 입력받음
			board = new int[N][N];
			start = new ArrayList<>();
			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < N; col++) {
					board[row][col] = Integer.parseInt(st.nextToken());
					
					// 0이면 시작할 수 있는 위치
					if (board[row][col] == 0) {
						start.add(new int[] {row, col});
					}
				}
			}
			
			// 최대 점수
			int maxScore = 0;
			
			// 미리 저장해둔 시작 지점을 돌기
			for (int[] point : start) {
				int row = point[0];
				int col = point[1];
				
				// 출발할 방향 지정하기
				for (int dir = 0; dir < 4; dir++) {
					// 해당 좌표, 해당 방향으로 시뮬레이션 시작
					maxScore = Math.max(maxScore, game(row, col, dir));
				}
			}

			sb.append('#').append(test_case).append(' ').append(maxScore).append('\n');
		}
		System.out.println(sb);
	}
}
