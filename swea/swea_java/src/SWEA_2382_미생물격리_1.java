import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 시뮬레이션으로 풀이,, 빡구현
public class SWEA_2382_미생물격리_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * 정사각형 구역 안에 K개의 미생물 군집 존재
 * 정가각형 구역은 N*N임. 근데 가장 바깥쪽 가장자리 부분은 특수한 약품이 칠해져 있어서 나갈 수 없음	
 * 
 * 최초 각 미생물 군집의 위치와 군집 내 미생물의 수, 이동방향이 주어짐. 이동 방향은 상, 하, 좌, 우 4 방향 중 하나 
 * 각 군집들은 1시간마다 이동방향에 있는 다음 셀로 이동
 * 미생물 군집 이동 후 가장자리에 도달하면 군집 내 미생물의 절반이 죽고, 이동 방향이 반대로 바뀜 
 * 		이 때 미생물이 홀수인 경우 : 2로 나구고 소수점 버림한 값 (그냥 /2 하면 됨)
 * 이동 후 두 개 이상의 군집이 한 셀에 모이는 경우 군집들이 합쳐짐
 * 		합쳐진 군집의 미생물 수는 군집들의 미생물 수의 합, 이동 방향은 가장 많은 군집의 이동방향이 됨 (미생물 수가 같은 경우 존재 x)
 * 
 * M 시간 동안 미생물 격리, M시간 후 남아있는 미생물의 총합은?
 * 
 * 입력
 * 테스트 케이스를 입력 받음
 * 각 테스트 케이스 마다
 * 		첫째줄 : 셀의 개수 N, 격리 시간 M, 미생물 군집의 개수 K
 * 		이후 K줄에 미생물 군집 정보 입력 (row, column, 미생물개수, 이동 방향
 */
	
	// 셀의 개수 N, 격리 시간 M, 미생물 군집의 개수 K
	static int N, M, K;
	// 각 미생물 군집에 번호를 붙이기 0~K-1
	// 그리고 해당 군집에 있는 미생물의 개수와 방향을 저장할 리스트 만들기
	static ArrayList<int[]> group;
	
	// 빈칸 상 하 좌 우
	static int[] deltaRow = {0, -1, 1, 0, 0};
	static int[] deltaColumn = {0, 0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++ test_case) {
			// 셀의 개수 N, 격리 시간 M, 미생물 군집의 개수 K를 입력 받음
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			// 미생물 군집의 번호가 0~K-1, 각 좌표도 입력받기
			group = new ArrayList<>();
			for (int index = 0; index < K; index++) {
				st = new StringTokenizer(br.readLine().trim());
				int row = Integer.parseInt(st.nextToken());
				int column = Integer.parseInt(st.nextToken());
				int count = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				
				// row, column, 미생물개수, 이동 방향
				group.add(new int[] {row, column, count, dir});
			}
			
			// M시간 동안 격리 -> M번 시뮬레이션 수행
			for (int time = 1; time <= M; time++) {
				
				// 각 군집마다 동작 수행
				for (int groupIndex = 0; groupIndex < K; groupIndex++) {
					// 만약 미생물의 수가 0이 되면 다른 곳과 합쳐졌거나, 소멸된 것임, 따라서 이동 수행 안함 
					if (group.get(groupIndex)[2] == 0) continue;
					
					// 미생물이 존재한다면 이동 수행
					move(groupIndex);
				}
				
				// 해당 이동 수행 후 같은 위치에 있는 미생물들 있는 지 확인 후, 있으면 병합 수행 
				check();
			}
			
			
			int totalSurviveCount = 0;
			// 최종 결과
			for (int[] current : group) {
				totalSurviveCount += current[2];
			}
			sb.append('#').append(test_case).append(' ').append(totalSurviveCount).append('\n');
		}
		System.out.println(sb);
	}
	
	// 이동 수행
	public static void move(int groupIndex) {
		int currentRow = group.get(groupIndex)[0];
		int currentColumn = group.get(groupIndex)[1];
		int surviveCount = group.get(groupIndex)[2];
		int direction = group.get(groupIndex)[3];
		
		// 이동할 위치 
		int nextRow = currentRow + deltaRow[direction];
		int nextColumn = currentColumn + deltaColumn[direction];
		// 이동 후 약품 바른 위치라면, 2로 나누고, 방향 전환
		if (nextRow == 0 || nextRow == N-1 || nextColumn == 0 || nextColumn == N-1) {
			surviveCount /= 2;
			direction = changeDirection(direction);
		}
		
		// 미생물이 다 죽어 버렸을 때, 모두 다 0으로 갱신 
		if (surviveCount == 0)	{
			group.set(groupIndex, new int[] {0, 0, 0, 0});
			return;
		}
		
		// 갱신
		group.set(groupIndex, new int[] {nextRow, nextColumn, surviveCount, direction});
	}
	
	// 동일 위치에 있는 군집이 존재하는 지 확인
	public static void check() {
		boolean[][] visited = new boolean[N][N];
		// 동일한 위치에 있는가?
		for (int groupIndex = 0; groupIndex < K; groupIndex++) {
			// 미생물이 0인 경우는 제외 패스
			if (group.get(groupIndex)[2] == 0) continue;
			
			// 중복이면 병합작업 수행
			if (visited[group.get(groupIndex)[0]][group.get(groupIndex)[1]]) merge(group.get(groupIndex)[0], group.get(groupIndex)[1]);
			
			// 아직 방문하지 않은 쌩쌩한 놈이면 방문처리
			visited[group.get(groupIndex)[0]][group.get(groupIndex)[1]] = true;
		}

	}
	
	// 병합작업
	public static void merge(int targetRow, int targetColumn) {
		int mergeCount = 0;
		int currentMaxIndex = -1;
		int currentMaxCount = 0;
		int currentMaxCountDirection = 0;
		
		for (int index = 0; index < K; index++) {
			int[] current = group.get(index);
			// 같은 위치면 합치기
			if (current[0] == targetRow && targetColumn == current[1]) {
				// 현재의 미생물 개수가 제일 많으면
				if (current[2] > currentMaxCount) {
					// 개수와 방향 갱신
					currentMaxCount = current[2];
					currentMaxCountDirection = current[3];
					// 인덱스도 갱신
					currentMaxIndex = index;
				}
				// 이미 작은 놈은 탈락 죽어라
				else {
					group.set(index, new int[]  {0, 0, 0, 0});
				}
				
				// 최종 합
				mergeCount += current[2];
			}
		}
		
		// 최종 1위는 최종 값으로 갱신, 그렇지 않으면 죽이기 
		for (int index = 0; index < K; index++) {
			int[] current = group.get(index);
			
			// 같은 위치 중에서
			if (current[0] == targetRow && targetColumn == current[1]) {
				// 인덱스가 같으면 최종값으로
				if (currentMaxIndex == index) {
					group.set(index, new int[] {targetRow, targetColumn, mergeCount, currentMaxCountDirection});
				}
				// 그렇지 않으면 0으로
				else {
					group.set(index, new int[] {0, 0, 0, 0});
				}
			}
		}
	}

	// 이동 방향 변경 메서드
	// 방향 배열에서는 빈칸 상 하 좌 우, 인덱스 일치
	// 입력으로는 이동 방향 상:1, 하:2, 좌:3, 우:4
	public static int changeDirection(int currentDirection) {
		switch (currentDirection) {
			case 1:
				return 2;
			case 2:
				return 1;
			case 3:
				return 4;
			case 4:
				return 3;
			default:
				return 0;
		}
	}
}
