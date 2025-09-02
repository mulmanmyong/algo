import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 백준시는 N개의 구역으로 나누어져 있음, 구역은 1~N까지 번호가 매겨져 있음 
 * 2개의 선거구로 나눠야 하며, 각 구역은 두 선거구 중 하나에 포함되어야 함 
 * 선거구는 구역을 적어도 하나 포함해야 하고, 한 선거구에 포함되어 있는 구역은 모두 연결되어 있어야 함.
 * A->B로 갈 수 있을 때 두 구역은 연결되어있다고 함.
 * 중간에 통하는 인접한 구역은 0개 이상이어야 하고, 모두 같은 선거구에 포함된 구역이어야 함 
 * 
 * [입력]
 * 구역의 개수 N 입력
 * 각 구역의 인구수 1~N 구역까지 순서대로 주어짐
 * N개의 줄에 각 구역과 인접한 구역의 정보 주어짐. => 첫번째 정수는 그 구역과 인접한 구역의 수, 이 후 인접한 구역의 번호 주어짐 
 * A와 B가 인접하면 B가 A가 인접함 
 */

public class BOJ_17471_게리멘더링_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int N;
	static int[] personCount;
	static ArrayList<Integer>[] adjList;
	static boolean[] visited;
	static int minDifference;

	// 선거구 조합을 나누는 부분
	public static void combination(int start, int currentdepth, int total) {
		// 가능한 조합을 선택했음
		if (currentdepth == total) {
			ArrayList<Integer> group1 = new ArrayList<>();
			ArrayList<Integer> group2 = new ArrayList<>();
			for (int i = 1; i <= N; i++) {
				// 선택한 부분
				if (visited[i]) {
					group1.add(i);
				}
				// 안한부분
				else {
					group2.add(i);
				}
			}
			// 현재의 선택지를 기점으로 해당 부분 유효한지
			// 해당 부분이 아닌 부분 유효한지 판단 
			if (isValid(group1, group2)) {
				// 유효하면 
				// 두 선거구의 인구 차이 구해서 최솟값 갱신 
				int group1Count = 0;
				for (int idx : group1) {
					group1Count += personCount[idx];
				}
				
				int group2Count = 0;
				for (int idx : group2) {
					group2Count += personCount[idx];
				}
				
				minDifference = Math.min(minDifference, Math.abs(group1Count - group2Count));
			}
			return;
		}

		// 근데 끝에 도달했음 종료
		if (start > N) {
			return;
		}

		// 해당 부분을 선택하는 경우 
		visited[start] = true;
		combination(start + 1, currentdepth + 1, total);
		// 안하는 경우
		visited[start] = false;
		combination(start + 1, currentdepth, total);
	}

	// 선거구가 유효한지 확인하는 함수
	public static boolean isValid(ArrayList<Integer> group1, ArrayList<Integer> group2) {
		// 둘 중 하나라도 비어있으면 안됨
		if (group1.isEmpty() || group2.isEmpty()) return false;

		// 각각의 그룹이 연결되어 있는지 확인
		return isConnected(group1) && isConnected(group2);
	}

	// BFS로 하나의 그룹이 연결되어 있는지 확인
	public static boolean isConnected(ArrayList<Integer> group) {
		Queue<Integer> q = new LinkedList<>();
		boolean[] check = new boolean[N + 1];

		q.add(group.get(0));
		check[group.get(0)] = true;

		int count = 1;

		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int next : adjList[cur]) {
				if (check[next] || !group.contains(next))	continue;
				check[next] = true;
				q.add(next);
				count++;
			}
		}

		return count == group.size();
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		N = Integer.parseInt(br.readLine().trim());
		personCount = new int[N+1];
		st = new StringTokenizer(br.readLine().trim());
		for (int index = 1; index <= N; index++) {
			personCount[index] = Integer.parseInt(st.nextToken());
		}

		adjList = new ArrayList[N+1];
		for (int index = 0; index <= N; index++) {
			adjList[index] = new ArrayList<>();
		}

		// 인접한 애들 입력
		for (int index = 1; index <= N; index++) {
			st = new StringTokenizer(br.readLine().trim());
			int count = Integer.parseInt(st.nextToken());

			for (int cnt = 0; cnt < count; cnt++) {
				adjList[index].add(Integer.parseInt(st.nextToken()));
			}
		}

		// 선거구를 2갈래로 나누기 
		// 각 시작을 기준으로 본인하고 다른 아이들이 모두 연결되는 지 확인
		// 본인과 연결된 아이들 중 다른 아이들이 다 연결되었는 지 
		minDifference = Integer.MAX_VALUE;
		visited = new boolean[N + 1];
		
		for (int select = 1; select <= N/2; select++) {
			// 각각의 개수를 선택하는 경우
			combination(1, 0, select);
		}

		// 한번도 갱신이 안됐으면 나눠진 것이 아님
		// 즉 아래의 조건이 참이면 나눠진 것이 아니니깐 -1 출력, 나눠졌으면 그대로 minDifference
		sb.append(minDifference == Integer.MAX_VALUE ? -1 : minDifference);
		System.out.println(sb);
	}
}
