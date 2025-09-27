import java.io.*;
import java.util.*;

public class BOJ_9205_맥주마시면서걸어가기_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [섬령]
 * 맥주 마시면서 걸어간다
 * 출발은 상근이네 집에서 함. 맥주 한 박스를 들고 출발
 * 한 박스에 맥주 20병 들어 있음
 * 50미터애 한 병씩 마실거임
 * 50미터 가기 위해선 한 병 마셔야 함
 * 
 * 맥주 살 수 있는 편의점이 있긴 함.
 * 편의점을 나선 직후에도 50미터를 위해선 한병때려야함
 * 
 * 20병 제한이라 빙 병 버리고 새 병 사고 그래야 함
 * 편의점, 상근이네 집, 펜타포트 락 페스티벌의 좌표 주어짐. 상근이와 친구들 행복하게 페스티벌 도착할 수 있을 까?!
 * 
 * [입력]
 * 첫째 줄에 테스트 케이스의 개수 t 주어짐
 * 	각 테스트 케이스의 첫째 줄에 맥주를 파는 편의점의 개수 n 주어짐 
 * 	각 테스트 케이스의 n+2개 줄에 거쳐 상근이네 집, 편의점, 펜타포트 락 페스티벌 좌표 주어짐 
 * 
 * [출력]
 * 행복하게 페스티벌 갈 수 있으면
 *  "happy"
 *  아니면 "sad" 출력
 * 
 * [어캄]
 * 어카긴 플로이드 워셜로 잘 해봐야지
 * 근데 이게 최적 경로를 찾는게 아니라 그냥 행복하게 도착이 가능한 지 아닌지를 하는 거니깐..
 * 
 * 일단 편의점을 다 들려버려.
 * 근데, 이제 이 좌표 처리를 어떻게 하지ㅏ?
 * 
 * 모든 편의점을 들리며 최단 거리들로 이동하는 것이지요?
 * 그리고 그 최단 거리를 보고, 들고 있는 맥주로 이동이 가능한지를 보는 것이다.
 * 편의점에서 계속 20병씩 채우면 20*50 = 1000미터씩 이동이 가능 한거지 
 * 제한 거리 1000미터 
 * 
 * 일단 시작 지점, 입력받고, 편의점들 쭉 입력받고, 도착지점을 입력받고 
 * 흠 그냥 BFS로 해도 될 거 같은데 ㅋㅋㅋㅋㅋ
 * 
 * 근데 플로이드 워셜 배운 김에 굳이 플로이드 워셜로 해보면?
 * 인접 행렬을 이용해서 i->j 이동이 LIMIT 이하인지 판단해야함 
 * 
 * 플로이드 워셜을 통해서 모든 작업이 끝나면 0->n+1이 true면 도착할 수 있는 것이고ㅠ
 * false면 중간에 맥주 다 떨어져서 도착못하는 것이다!
 */
	
	// 20병 제한이니깐 20*50 = 1000미터가 제한임
	static final int LIMIT = 1000;
	
	// 테스트 케이스 t
	static int t;
	// 편의점의 개수 n
	static int n;
	
	// 좌표
	static class Point {
		int row, col;

		public Point(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	// 상근이네 집, 편의점들, 페스티벌 좌표들
	static Point[] pointArr;
	// 플로이드 워셜을 위한 인접행렬 0~n+1
	static boolean[][] adjMatrix;
	
	// 거리 계산
	public static int dist(Point p1, Point p2) {
		return Math.abs(p2.row-p1.row) + Math.abs(p2.col-p1.col);
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스 입력
		t = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= t; ++tc) {
			
			// 편의점의 개수 n 입력
			n = Integer.parseInt(br.readLine().trim());
			pointArr = new Point[n+2];
			
			// 상근이의 집 좌표 입력
			st = new StringTokenizer(br.readLine().trim());
			pointArr[0] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			// 편의점들의 좌표 입력
			for (int i = 1; i <= n; i++) {
				st = new StringTokenizer(br.readLine().trim());
				pointArr[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			// 페스티벌 좌표 입력
			st = new StringTokenizer(br.readLine().trim());
			pointArr[n+1] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
			// 인접 행렬 초기화
			adjMatrix = new boolean[n+2][n+2];
			for (int i = 0; i < n + 2; i++) {
				for (int j = 0; j < n + 2; j++) {
					// i와 j가 동일하면 패스 하기 
					if (i == j) continue;
					
					// 동일하지 않다면 움직일 수 있는 거리인지
					if (dist(pointArr[i], pointArr[j]) <= LIMIT) {
						// 움직일 수 있는 거리라면 true
						adjMatrix[i][j] = true;
					}
				}
			}
			
			// 플로이드 워셜 진행하기
			// 경유지
			for (int k = 1; k < n+1; k++) {
				// 출발지
				for (int i = 0; i < n; i++) {
					// 목적지
					for (int j = 2; j < n+2; j++) {
						// i->k k->j 가능하면 i->j로 이동이 가능한 것임
						if (adjMatrix[i][k] && adjMatrix[k][j]) {
							adjMatrix[i][j] = true;
						}
					}
				}
			}
			
			// 그렇게 해서 종합적으로 0->n+1이 true인지 false인지 판단
			// true인가요?
			if (adjMatrix[0][n+1]) {
				// true면 페스티벌 도착! 즐깁시다
				sb.append('h').append('a').append('p').append('p').append('y').append('\n');
			}
			// 아닌가요...
			else {
				// false면 페스티벌 도착 실패.. 절망적
				sb.append('s').append('a').append('d').append('\n');
			}
		}

		System.out.println(sb);
	}
}
