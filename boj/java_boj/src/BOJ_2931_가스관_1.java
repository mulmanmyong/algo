import java.io.*;
import java.util.*;

public class BOJ_2931_가스관_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [설명]
 * R행 C열 지도가 있음
 * 각 칸은 일곱가지 기본 블록으로 이루어져 있음
 * '|' : 위아래 통로
 * '-' : 좌우 통로
 * '+' : 십자 통로
 * '1' : 우측과 하단을 이어주는 통로 
 * '2' : 우측과 상단을 이어주는 통로
 * '3' : 좌측과 상단을 이어주는 통로 
 * '4' : 좌측과 하단을 이어주는 통로 
 * 
 * M에서 Z로 흐르고 있으며 십자 통로는 위아래도 흐르고 좌우도 흘러야 함 
 * 
 * 근데 해커가 어떤 칸을 지웠고, 그 칸에는 어떤 블록이 있었는 지 구하기 
 * 
 * [입력]
 * 첫째 줄에 유럽의 크기 R과 C 주어짐
 * 빈칸은 '.' 블록은 위에 언급한 것들 로 지도가 주어짐 
 * 
 * [출력]
 * 지워진 블록의 행과 열 위치를 출력하고, 어떤 블록이었는 지 출력 
 * 
 * [어캄]
 * 시뮬레이션을 진행해보면 될 것 같다.
 * 만약에 진행 도중에 가는 통로에 블록이 없다면 없어진 곳을 기준으로 하여 어떤 블록인 지 판단 
 * 그 후에 그 블록을 도출 해내는 방식 
 * 
 * 블록이 없는데 왼쪽에서 오른쪽으로 오고 있고 그다음 블록도 오른쪽에 이어져있는 상태면
 * '-' 블록을 하면 되는 느낌으로다가 
 */
	
	// 좌표 클래스
	static class Point {
		int row, col;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
	}
	
	// 유럽의 크기 R과 C
	static int R, C;
	// 유럽 
	static char[][] europe;
	
	// 시작 위치
	static Point start;
	// 도착 위치
	static Point end;
	
	// 방향 배열 
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaCol = {0, 0, -1, 1};
	
	public static void simulation() {
		// BFS로 탐색하기
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(start.row, start.col));
		
		// 큐가 빌 때 까지
		while (!q.isEmpty()) {
			Point cur = q.poll();
			
			// 현재 기준 주변 탐색 
			for (int dir = 0; dir < 4; dir++) {
				Point next = new Point(cur.row+deltaRow[dir], cur.col+deltaCol[dir]);
				
				// 범위 벗어나면 안됨  
				if (next.row < 0 || next.row >= R || next.col < 0 || next.col >= C) continue;
				
				// 다음 
			}
		}
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 유럽의 크기 R과 C 입력
		st = new StringTokenizer(br.readLine().trim());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		// 유럽 지도 입력 받기
		europe = new char[R][C];
		for (int row = 0; row < R; row++) {
			String str = br.readLine().trim();
			for (int col = 0; col < C; col++) {
				europe[row][col] = str.charAt(col);	
				
				// 시작 위치 
				if (europe[row][col] == 'M') {
					start = new Point(row, col);
				}
				
				// 도착 위치  
				if (europe[row][col] == 'Z') {
					end = new Point(row, col);
				}
			}
		}
		
		// 시뮬 시작  
		simulation();
		
		
		System.out.println(sb);
	}
}
