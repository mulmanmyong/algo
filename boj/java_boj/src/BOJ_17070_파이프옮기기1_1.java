import java.io.*;
import java.util.*;

public class BOJ_17070_파이프옮기기1_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * 집은 NxN의 격자판으로 나타낼 수 있고, 1x1크기의 정사각형 칸으로 나누어져 있음 (
 * (1, 1)부터 시작
 * 
 * 파이프는 회전시킬 수 있고, 3가지 방향 가능
 * 파이프는 매우 무겁기 때문에 밀어서 이동 시킴 
 * 우 우하 하 이런식의 방향으로만 이동 가능 
 * 
 * 초기 위치에서 한쪽 끝을 N,N으로 옮기게 하는 경우 
 */
	
	static int N;
	static int[][] home;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());
		// padding을 하여 1,1 부터 시작할 수 있게 하기
		home = new int[N+1][N+1];
		for (int row = 1; row <= N; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int col = 1; col <= N; col++) {
				home[row][col] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 파이프의 상태와 이동할 수 있는 경우의 수를 저장하면서 이동 
		// 3차원 dp를 이용하면 됨
		// 0 : 우측, 1 : 하단, 2 : 대각선
		int[][][] dp = new int[N+1][N+1][3];
		// 끝이 도달하는 경우를 작성할 것이기 때문에 끝 지점의 상태를 초기화하기
		// 초기 상태 1,2 에 우측방향 상태로 존재 
		dp[1][2][0] = 1;
		
		// 파이프의 현재 상태에 따라서 이동할 수 있는 상태가 달라짐 
		// 우측인 경우 우측, 대각선만 가능
		// 대각선인 경우 우측 대각선 하단 가능
		// 하단인 경우 하단, 대각선만 가능 
		
		// 모든 좌표를 탐색하며 진행
		// 0이 빈칸 1이 벽
		// 끝지점이 (1,2) 시작이니
		// 파이프를 어느 방향으로 밀든 (1,3), (2,3), 이고 이로부터 퍼져 나갈것 
		for (int row = 1; row <= N; row++) {
			for (int col = 3; col <= N; col++) {
				// 빈칸이면 파이프가 올 수 없으니 패스하기
				if (home[row][col] == 1)	continue;
				
				// 경우의 수가 계속 누적되어야함
				
				// 우측으로 밀 경우
				// 이전 상태가 우측이거나 대각선이어야 함
				dp[row][col][0] += (dp[row][col-1][0] + dp[row][col-1][2]);
				
				// 하단으로 밀 경우
				// 이전 상태가 하단이거나 대각선 이어야함
				dp[row][col][1] += dp[row-1][col][1] + dp[row-1][col][2];
				
				// 대각선으로 밀경우
				// 이전 상태 모두 다 가능
				// 근데 (row-1, col) (row, col-1)에도 장애물이 없어야 함
				if (home[row-1][col] == 0 && home[row][col-1] == 0) {
					dp[row][col][2] += dp[row-1][col-1][0] + dp[row-1][col-1][1] + dp[row-1][col-1][2];
				}
			}
		}
		
		// 결과는 끝에 지점 도달한 것
		int result = dp[N][N][0] + dp[N][N][1] + dp[N][N][2];
		sb.append(result);
		System.out.println(sb);
	}
}