import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 완탐과 백트래킹을 이용하여 풀이 
public class BOJ_15686_치킨배달_1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	// 도시의 정보를 입력받을 크기 N, 폐업시키지 않을 치킨집을 M개 고르기
	static int N, M;
	// 도시의 정보를 입력받을 배열
	static int[][] city;
	// 최소 치킨거리
	static int minimumChickenRoad;
	
	// 치킨집의 위치
	static List<int[]> chickenPoint;
	// 집의 위치
	static List<int[]> homePoint;
	// 치킨집 선정여부
	static boolean[] selectChickenHouse;
	
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		// 도시 배열의 크기
		N = Integer.parseInt(st.nextToken());
		// 폐업 시키지 않을 치킨집의 수 (생존 치킨집)
		M = Integer.parseInt(st.nextToken());
		
		// 도시의 정보를 입력받음 
		city = new int[N][N];
		chickenPoint = new ArrayList<>();
		homePoint = new ArrayList<>();
		for (int row = 0; row < N; row++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int column = 0; column < N; column++) {
				city[row][column] = Integer.parseInt(st.nextToken());
				
				// 집은 1
				if (city[row][column] == 1) homePoint.add(new int[] {row, column});
				// 치킨집은 2
				if (city[row][column] == 2) chickenPoint.add(new int[] {row, column});
			}
		}
		
		selectChickenHouse = new boolean[chickenPoint.size()];
		// 최소 치킨 거리 담을 변수
		minimumChickenRoad = Integer.MAX_VALUE;
		// 치킨집을 M개만 남겨 두기, 현재의 치킨집들 중에서 M개 고르기 combination
		bestChickenHouse(0, 0);
		
		// 출력
		sb.append(minimumChickenRoad);
		System.out.println(sb);
	}
	
	public static void bestChickenHouse(int selectIndex, int selectCount) {
		// 기저 조건 : 끝까지 탐색했을 경우
		if (selectIndex == chickenPoint.size()) {
			// M개의 치킨집을 모두 골랐을 경우
			if (selectCount == M) {
				// 최소 치킨 거리 계산 후 갱신
				minimumChickenRoad = Math.min(minimumChickenRoad, calculateChickenRoad());
			}
			return;
		}
		
		// 구현 부분
		// 현재의 치킨집을 선택했을 경우
		selectChickenHouse[selectIndex] = true;
		bestChickenHouse(selectIndex + 1, selectCount + 1);
		// 현재의 치킨집을 선택안했을 경우 
		selectChickenHouse[selectIndex] = false;
		bestChickenHouse(selectIndex + 1, selectCount);
	}
	
	// 선택받은 치킨집의 거리 계산
	public static int calculateChickenRoad() {
		int currentChickenRoad = 0;
		
		// 모든 집과 치킨집과의 거리를 게산하기
		for (int[] home : homePoint) {
			int minDistance = N*N;
			for (int index = 0; index < chickenPoint.size(); index++) {
				// 치킨집을 골랐으면
				if (selectChickenHouse[index]) {
					// 가장 가까운 치킨집 찾기
					minDistance = Math.min(minDistance, calculateDistance(home[0], home[1], chickenPoint.get(index)[0], chickenPoint.get(index)[1]));
				}
			}
			
			// 현재 집과 가장 가까운 치킨집에 대해서 거리 계산
			currentChickenRoad += minDistance;
		}
	
		return currentChickenRoad;
	}
	
	// 거리 계산
	public static int calculateDistance(int r1, int c1, int r2, int c2) {
		return Math.abs(r1-r2) + Math.abs(c1-c2);
	}
}
