import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4014_활주로건설_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * 문제 파악하기
 * N*N 크기의 절벽지대에 활주로 건설
 * 각 셀의 숫자는 그 지형의 높이를 의미
 * 
 * 활주로를 가로 또는 세로 방향으로 건설할 수 있는 가능성을 확인
 * 	-> 활주로는 높이가 동일한 구간에서 건설 가능
 * 	-> 높이가 다른 구간의 경우 활주로가 끊어지기 때문에 경사로를 설치해야만 활주로 건설 가능
 * 		-> 경사로는 높이 차이가 1이고 낮은 지형의 높이가 동일하게 경사로의 길이만큼 연속되는 곳에 설치 가능
 * 
 * 
 * 입력
 * 1. 테스트 케이스 입력
 * 	1-1. 절벽지대의 크기 N, 경사로의 길이 X 주어짐
 * 	1-2. 절벽지대의 지형 정보 주어짐 
 * 
 * 출력
 * 활주로를 건설할 수 있는 경우의 수
 * 
 * 
 * 절벽지대가 주어진 <-> 이러한 방향으로 경사로를 통해 활주로 건설이 가능한 경우의 수
 * +
 * 위아래 방향으로 경사로를 통해 활주로 건설이 가능한 경우의 수
 * 
 * 열 순회, 행 순회 등을 이용하여 높이 1차이가 나면 길이가 X이상이 되는 지 파악하기 이런식으로 끝까지 이어지면 경우의 수 1 증가
 */
	
	// 테스트 케이스
	static int testCase;
	// 절벽지대의 크기 N, 경사로의 길이 X  
	static int N, X;
	// 절벽지대 입력
	static int[][] area;
	// 건설 개수
	static int count;
	
	public static void check1() {
		// 열을 이어 나가며 탐색
		for (int row = 0; row < N; row++) {
			int column = 0;
			int currentLength = 1;
			int currentHeight = area[row][column++];
			
			// 중복 건설이 될 수도 있으니 중복처리
			boolean[] visited = new boolean[N];
			// 종료 플래그
			boolean isPossible = true;
			
			// 한줄을 다 탐색할 때 까지
			while (column < N) {
				
				// 이전까지의 높이랑 현재까지의 높이가 다름!
				if (currentHeight != area[row][column]) {
					// 근데 높이가 1차이가 아님
					if (Math.abs(currentHeight-area[row][column]) > 1) {
						// 높이는 무조건 1, 그렇지 않으면 경사로 건설 불가능해서 활주로도 안됨
						isPossible = false;
						break;
					}
					// 높이는 1차이가 남
					else {
						// 이전까지의 높이보다 현재의 높이가 크면 (오르막)
						if (currentHeight < area[row][column]) {
							// 이전까지의 높이의 경사로 길이가 X 이상이어야 함
							if (currentLength >= X) {
								// 경사로를 놓을 자리가 비었는지 확인
								for (int i = 1; i <= X; i++) {
									// 현재 위치 이전에 X만큼 경사로가 있는 지 확인
									if (visited[column - i]) { 
										// 이미 다른 경사로가 있다면 건설불가 
										isPossible = false;
										break;
									}
								}
								if (!isPossible) break;

								// 경사로 설치가 가능함!
								// X만큼 경사로 설치하기 
								for (int i = 1; i <= X; i++) {
									visited[column - i] = true;
								}
								
								// 경사로 길이 다시 초기화 하고, 현재의 높이 갱신
								currentLength = 1;
								currentHeight = area[row][column];
							}
							// X이상이 아니면 경사로를 건설할 수 없는 경우임 break
							else {
								isPossible = false;
								break;
							}
						}
						// 이전까지의 높이가 현재의 높이보다 큼 (내리막)
						else if (currentHeight > area[row][column]) {
							
							int nextHeight = area[row][column];
							// 앞으로 X칸에 경사로를 놓을 수 있는지 확인
							for (int i = 0; i < X; i++) {
								// 범위를 벗어나거나, 높이가 다르거나, 이미 경사로가 있으면 불가
								if (column + i >= N || area[row][column + i] != nextHeight || visited[column + i]) {
									isPossible = false; 
									break;
								}
							}
							if (!isPossible) break;

							// 경사로 설치가 가능함!
							// X만큼 경사로 설치하기
							for (int i = 0; i < X; i++) {
								visited[column + i] = true;
							}
							
							// 현재 높이 갱신 
							currentHeight = nextHeight;
							// 경사로의 끝으로 이동 
							column += (X - 1);
							// 경사로 설치 후 길이는 초기화
							currentLength = 1;
						}
					}
				}
				// 높이가 같음
				else {
					currentLength++;
				}
				column++;
			}
			
			// 한줄을 성공적으로 탐색했으면
			if (isPossible) {
				count++;
			}	
		}
	}
 	
	public static void check2() {
		// 행을 이어 나가며 탐색
		for (int column = 0; column < N; column++) {
			int row = 0;
			int currentLength = 1;
			int currentHeight = area[row++][column];
			
			// 중복 건설이 될 수도 있으니 중복처리
			boolean[] visited = new boolean[N];
			// 종료 플래그
			boolean isPossible = true;
			
			// 한줄을 다 탐색할 때 까지
			while (row < N) {
				
				// 이전까지의 높이랑 현재까지의 높이가 다름!
				if (currentHeight != area[row][column]) {
					// 근데 높이가 1차이가 아님
					if (Math.abs(currentHeight-area[row][column]) > 1) {
						// 높이는 무조건 1, 그렇지 않으면 경사로 건설 불가능해서 활주로도 안됨
						isPossible = false;
						break;
					}
					// 높이는 1차이가 남
					else {
						// 이전까지의 높이보다 현재의 높이가 크면 (오르막)
						if (currentHeight < area[row][column]) {
							// 이전까지의 높이의 경사로 길이가 X 이상이어야 함
							if (currentLength >= X) {
								// 경사로를 놓을 자리가 비었는지 확인
								for (int i = 1; i <= X; i++) {
									// 현재 위치 이전에 X만큼 경사로가 있는 지 확인
									if (visited[row - i]) { 
										// 이미 다른 경사로가 있다면 건설불가 
										isPossible = false;
										break;
									}
								}
								if (!isPossible) break;

								// 경사로 설치가 가능함!
								// X만큼 경사로 설치하기 
								for (int i = 1; i <= X; i++) {
									visited[row - i] = true;
								}
								
								// 경사로 길이 다시 초기화 하고, 현재의 높이 갱신
								currentLength = 1;
								currentHeight = area[row][column];
							}
							// X이상이 아니면 경사로를 건설할 수 없는 경우임 break
							else {
								isPossible = false;
								break;
							}
						}
						// 이전까지의 높이가 현재의 높이보다 큼 (내리막)
						else if (currentHeight > area[row][column]) {
							
							int nextHeight = area[row][column];
							// 앞으로 X칸에 경사로를 놓을 수 있는지 확인
							for (int i = 0; i < X; i++) {
								// 범위를 벗어나거나, 높이가 다르거나, 이미 경사로가 있으면 불가
								if (row + i >= N || area[row + i][column] != nextHeight || visited[row + i]) {
									isPossible = false; 
									break;
								}
							}
							if (!isPossible) break;

							// 경사로 설치가 가능함!
							// X만큼 경사로 설치하기
							for (int i = 0; i < X; i++) {
								visited[row + i] = true;
							}
							
							// 현재 높이 갱신 
							currentHeight = nextHeight;
							// 경사로의 끝으로 이동 
							row += (X - 1);
							// 경사로 설치 후 길이는 초기화
							currentLength = 1;
						}
					}
				}
				// 높이가 같음
				else {
					currentLength++;
				}
				row++;
			}
			
			// 한줄을 성공적으로 탐색했으면
			if (isPossible) {
				count++;
			}	
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스 입력
		testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			// 절벽지대의 크기, 경사로의 길이 입력
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			// 절벽지대 입력
			area = new int[N][N];
			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int column = 0; column < N; column++) {
					area[row][column] = Integer.parseInt(st.nextToken());
				}
			}
			
			count = 0;
			// 가로 방향 활주로 건설가능한 지 파악하기
			check1();
			
			// 세로 방향 활주로 건설가능한 지 파악하기
			check2();
			
			// 결과 입력
			sb.append('#').append(test_case).append(' ').append(count).append('\n');
		}
		System.out.println(sb);
	}
}
