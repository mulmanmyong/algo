import java.io.*;
import java.util.*;

public class BOJ_2583_영역구하기_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [설명]
 * 눈금의 간격이 1인 MxN 크기의 모눈 종이가 있음.
 * 모눈종이 위에 눈금에 맞추어 K개의 직사각형을 그릴 것임
 * K개의 직사각형의 내부를 제외한 나머지 부분이 몇 개의 분리된 영역으로 나누어짐
 * 
 * 분리된 영역의 개수와 분리된 각 영역의 넓이 오름차순 구하기
 * 
 * [입력]
 * 첫째 줄에 직사각형의 크기  M, N, 그리고 직사각형의 개수 K 주어짐 
 * 둘째 줄에 K개의 줄에 한 줄에 하나씩 직사각형의 왼쪽 아래 좌표, 오른쪽 위 좌표 입력
 * 
 * [출력]
 * 첫째 줄에 분리되어 나누어지는 영역의 개수 출력,
 * 둘째 줄에 각 영역의 넓이 오름차순 출력
 * 
 * [ㄱㄱ]
 * 배열을 arr[n][m]으로 생성하고, arr[x][y] ~ arr[x][y]로 채워서 영역 구해보자잉
 * 
 */
	
	// 배열의 크기 M, N, 직사각형의 개수 K
	static int M, N, K;
	// 배열
	static int[][] arr;
	
	// 영역의 개수를 담을 변수
	static int count;
	// 영역의 크기들을 담을 리스트
	static ArrayList<Integer> sizeList;
	
	// 방향 배열
	static int[] deltaX = {-1, 1, 0, 0};
	static int[] deltaY = {0, 0, -1, 1};
	
	public static int bfs(int x, int y) {
		int size = 1;
		
		Queue<int[]> q = new LinkedList<>();
		q.add(new int[] {x, y});
		arr[x][y] = -1;
		
		while (!q.isEmpty()) {
			int curX = q.peek()[0];
			int curY = q.peek()[1];
			q.poll();
			
			for (int dir = 0; dir < 4; dir++) {
				int newX = curX + deltaX[dir];
				int newY = curY + deltaY[dir];
				
				if (newX < 0 || newX >= N || newY < 0 || newY >= M) continue;
				if (arr[newX][newY] == -1) continue;
				
				size++;
				arr[newX][newY] = -1;
				q.add(new int[] {newX, newY});
			}
		}
		
		return size;
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 배열의 크기 M, N, 직사각형의 개수 K 입력
		st = new StringTokenizer(br.readLine().trim());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 직사각형 채우기, 직사각형의 영역 -1로 채우기
		arr = new int[N][M];
		for (int t = 0; t < K; t++) {
			st = new StringTokenizer(br.readLine().trim());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			// 채우기 (왼쪽 아래와 오른쪽 위기 때문에 1보다 2가 무조건적으로 좌표가 큼
			for (int x = x1; x < x2; x++) {
				for (int y = y1; y < y2; y++) {
					arr[x][y] = -1;
				}
			}
		}
		
		// 직사각형 다 채움 이제 영역의 개수와 그 영역의 크기를 저장
		// 0인 부분이 있으면 자동 시작
		count = 0;
		sizeList = new ArrayList<>();
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < M; y++) {
				if (arr[x][y] == 0) {
					count++;
					sizeList.add(bfs(x, y));
				}
			}
		}
		
		// 크기들 오름 차순 정렬
		sizeList.sort((a, b) -> Integer.compare(a, b));
		sb.append(count).append('\n');
		for (int size : sizeList) {
			sb.append(size).append(' ');
		}
		System.out.println(sb);
	}
}
