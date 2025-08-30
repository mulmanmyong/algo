import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_22416_현정공주의보물검사_1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

/*
 * 보석이 있는 지 확인 후 보석이 있는 위치가 양쪽 거리가 같으면 화가나버린다. 
 * 
 * [입력]
 * 1. 테스트 케이스 개수 주어짐
 * 	1-1. 상자의 개수 N, 공주의 처음 위치 L 주어짐 
 * 	1-2. 이후로 상자의 정보 주어짐 (1 보석있음, 0 보석없음)
 * 
 * 1~N까지 시작하는 위치 완탐
 * 각 시작 위치마다 유효한 결과가 나오는 지 보고,
 * 유효하면 현위치가 몇칸 이동해서 시작했는 지 업데이트 -> 최소값 업뎃 
 */
	
	// 테스트 케이스
	static int testCase;
	// 상자의 개수 N, 공주의 처음위치 L
	static int N, L;
//	// 상자의 정보를 담을 배열 -> 풀다가 필요없어짐.. 시작 지점 어차피 완탐해야하니 
//	static int[] box;
	// 현정공주가 방문한 방인지 처리 
	static boolean[] visited;
	
	// 보석의 위치를 담을 배열
	static ArrayList<Integer> jewelry;
	// 결과를 담을 배열
	static int minMove;
	
	// 현정공주가 화 안내고 모든 보석 다 찾는가?
	public static boolean isValid(int start) {

		// 애초에 보석이 없을 경우 
		if (jewelry.isEmpty()) {
			return true;
		}

		int current = start;
		
		// 보석 있는 곳을 갔는 지 방문 처리 배열 
		boolean[] visited = new boolean[jewelry.size()];
		
		// 시작지점이 보석이면 바로 방문 처리 하기 
		for (int index = 0; index < jewelry.size(); index++) {
			if (jewelry.get(index) == start) {
				visited[index] = true;
				break;
			}
		}
		
		// 모든 보석 다 방문 할때 까지
		while (true) {
			
			// 가장 가까운 위치 부터 이동을 함
			// 근데 가장 가까운 거리가 2개 이상이면 현정공주가 화나버림!!!!
			int minDist = Integer.MAX_VALUE;
			int minDistCount = 0;
			int minIndex = -1;
			
			// 찾기 
			for (int index = 0; index < jewelry.size(); index++) {
				
				// 이미 갔던 위치면 패스
				if (visited[index])	continue;
				
				// 현 위치에서 보석까지의 거리
				int currentDist = Math.abs(current - jewelry.get(index));
				
				// 현재까지의 거리가 지금 최소보다 가깝냐?
				if (currentDist < minDist) {
					// 가까우면 갱신
					minDist = currentDist;
					minDistCount = 1;
					minIndex = index;
				}
				// 현재 최소 거리랑 가까운 경우가 있어버림
				else if (minDist == currentDist) {
					// 동일한 거리 개수 증가
					minDistCount++;
				}
			}
			
			// 가까운 거리가 2개 이상이면...
			if (minDistCount >= 2) {
				// 현정 공주 화남 !!!!!!!
				return false;
			}
			
			// minIndex가 -1인채로 와버리면..
			if (minIndex == -1) {
				// 모든 곳 방문 완료! 
				// 현정공주 행복함
				return true;
			}
			
			// 그렇지 않다면 방문 처리 하고 이동
			visited[minIndex] = true;
			current = jewelry.get(minIndex);
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 테스트 케이스 입력 
		testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			
			// N과 L 입력
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			// 박스의 정보를 입력 받음
			st = new StringTokenizer(br.readLine().trim());
			// 1~N번호 
//			box = new int[N+1];
			jewelry = new ArrayList<>();
			for (int index = 1; index <= N; index++) {
				// 0은빈칸, 1은보석이있는 것 
//				box[index] = Integer.parseInt(st.nextToken());
				
				// 보석이 있으면 보석 위치 좌표를 입력받음 
				if (Integer.parseInt(st.nextToken()) == 1) {
					jewelry.add(index);
				}
			}
			
			// 1~N까지 시작 지점 완탐 
			minMove = Integer.MAX_VALUE;
			for (int start = 1; start <= N; start++) {
				
				// 현 시작위치 유효한가?
				if (isValid(start)) {
					// 유효하면 최소 거리 갱신 
					minMove = Math.min(minMove, Math.abs(L - start));
				}
			}
			
			// 결과 출력
			sb.append('#').append(test_case).append(' ').append(minMove).append('\n');
		}
		System.out.println(sb);
	}
}
