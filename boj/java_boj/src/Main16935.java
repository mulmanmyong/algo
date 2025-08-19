import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main16935 {
	
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int N;
	public static int M;
	public static int commandCount;
	public static int[][] arr;
	public static int command;
	
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		commandCount = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int column = 0; column < M; column++) {
				arr[row][column] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine().trim());
		for (int cmdIndex = 0; cmdIndex < commandCount; cmdIndex++) {
			command = Integer.parseInt(st.nextToken());
			rotateArray(command);
		}
		
		// 정답 담기
		for (int row = 0; row < N; row++) {
			for (int column = 0; column < M; column++) {
				sb.append(arr[row][column]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	public static void rotateArray(int command) {
		switch (command) {
			case 1:
				command1();
				break;
			case 2:
				command2();
				break;
			case 3:
				command3();
				break;
			case 4:
				command4();
				break;
			case 5:
				command5();
				break;
			case 6:
				command6();
				break;
		}
	}
	
	public static void command1() {
		// 상하 반전
		int[][] tmpArr = new int[N][M];
		for (int row = 0; row < N; row++) {
			for (int column = 0; column < M; column++) {
				tmpArr[row][column] = arr[N-1-row][column];
			}
		}
		arr = tmpArr;
	}
	
	public static void command2() {
		// 좌우 반전
		int[][] tmpArr = new int[N][M];
		for (int row = 0; row < N; row++) {
			for (int column = 0; column < M; column++) {
				tmpArr[row][column] = arr[row][M-1-column];
			}
		}
		arr = tmpArr;
	}
	
	public static void command3() {
		// 오른쪽으로 90도 회전
		int[][] tmpArr = new int[M][N];
		for (int row = 0; row < N; row++) {
			for (int column = 0; column < M; column++) {
				tmpArr[column][N-1-row] = arr[row][column];
			}
		}
		arr = tmpArr;
		
		// N과M도 변경 
		int tmp = N;
		N = M;
		M = tmp;
	}
	
	public static void command4() {
		// 왼쪽으로 90도 회전
		int[][] tmpArr = new int[M][N];
		for (int row = 0; row < N; row++) {
			for (int column = 0; column < M; column++) {
				tmpArr[M-1-column][row] = arr[row][column];
			}
		}
		arr = tmpArr;
		
		// N과M도 변경 
		int tmp = N;
		N = M;
		M = tmp;
	}
	
	public static void command5() {
		// 4개의 부분배열로 나누기
		// 그 부분배열이 시계방향 회전
		int[][] tmpArr = new int[N][M];
		int halfN = N / 2;
		int halfM = M / 2;
		
		// 1 -> 2
		for (int row = 0; row < halfN; row++) {
			for (int column = 0; column < halfM; column++) {
				tmpArr[row][halfM+column] = arr[row][column];
			}
		}
		
		// 2 -> 3
		for (int row = 0; row < halfN; row++) {
			for (int column = halfM; column < M; column++) {
				tmpArr[halfN+row][column] = arr[row][column];
			}
		}
		
		// 3 -> 4
		for (int row = halfN; row < N; row++) {
			for (int column = halfM; column < M; column++) {
				tmpArr[row][column-halfM] = arr[row][column];
			}
		}
		
		// 4->1
		for (int row = halfN; row < N; row++) {
			for (int column = 0; column < halfM; column++) {
				tmpArr[row-halfN][column] = arr[row][column];
			}
		}
		
		arr = tmpArr;
	}
	
	public static void command6() {
		// 4개의 부분배열로 나누기
		// 그 부분배열이 반시계방향 회전		
		int[][] tmpArr = new int[N][M];
		int halfN = N / 2;
		int halfM = M / 2;
		
		// 1 -> 4
		for (int row = 0; row < halfN; row++) {
			for (int column = 0; column < halfM; column++) {
				tmpArr[row+halfN][column] = arr[row][column];
			}
		}
		
		// 4 -> 3
		for (int row = halfN; row < N; row++) {
			for (int column = 0; column < halfM; column++) {
				tmpArr[row][column+halfM] = arr[row][column];
			}
		}
		
		// 3 -> 2
		for (int row = halfN; row < N; row++) {
			for (int column = halfM; column < M; column++) {
				tmpArr[row-halfN][column] = arr[row][column];
			}
		}
		
		// 2 -> 1
		for (int row = 0; row < halfN; row++) {
			for (int column = halfM; column < M; column++) {
				tmpArr[row][column-halfM] = arr[row][column];
			}
		}
		
		arr = tmpArr;
	}
}
