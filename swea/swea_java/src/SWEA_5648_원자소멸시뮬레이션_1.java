import java.io.*;
import java.util.*;

public class SWEA_5648_원자소멸시뮬레이션_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * 모든 원자들 서로 충돌 계산하기
 * 1. 모든 원자들의 조합을 따지며 충돌리스트 만들기 : 1000C2
 * 2. 충돌리스트를 충돌시간 기준으로 오름차순 정렬 
 *    - 같은 시간에 2개, 3개, 4개 충동가능한 상황들을 고려하며 충돌처리
 *    - 에너지 누적 
 * => 시간복잡도는 조합하는 시간 + 정렬하는 시간 
 * => 자료구조 : 원자List(충돌리스트)
 */
	
	
	// 원자들의 x위치 y위치 이동방항 보유에너지
	static class Atom implements Comparable<Atom> {
		int x, y, direction, energy;

		public Atom(int x, int y, int direction, int energy) {
			super();
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.energy = energy;
		}

		// 입력 순서를 지켜야 하는가? 아님. -> 정렬 -> 
		// 정렬방식은  x좌표 기준 오름 차순, y좌쵸 기준 오름 차순
		@Override
		public int compareTo(Atom o) {
			// 나의 x좌표가 상대의 x좌표보다 작은 순
			// 같다면 y좌표가 작은 순
			if (this.x == o.x)	return Integer.compare(this.y, o.y);
			return Integer.compare(this.x, o.x);
		}
		
	}
	
	// 충돌리스트에 담을 충돌하는 두 원자와 그 때의 시간 정보 
	static class Pair implements Comparable<Pair> {
		int i, j, time;

		public Pair(int i, int j, int time) {
			super();
			this.i = i;
			this.j = j;
			this.time = time;
		}

		@Override
		public int compareTo(Pair o) {
			return Double.compare(this.time, o.time);
		}
		
		
	}
	// 원자의 개수
	static int N;
	// 원소들의 정보를 저장할 리스트 
	static ArrayList<Atom> list;
	
	public static int makeBoomPair() {
		// 원자리스트를 좌표 기준으로 정렬
		Collections.sort(list);
		
		// 터지는 것들
		ArrayList<Pair> boomList = new ArrayList<>();
		
		// 원자 2개씩 조합 생성
		// 선택할 첫번째 원자
		for (int i = 0; i < N; i++) {
			// 선택할 두번째 원자 
			for (int j = i+1; j < N; j++) {
				Atom a = list.get(i);
				Atom b = list.get(j);
				
				// 상하좌우 => 0 1 2 3
				
				// 같은 x좌표를 갖는 원자들이 수직선에서 만날 때
				if (a.x == b.x) {
					// 정렬을 했기 때문에 y 가 작은 순으로 나옴. 따라서 a가 위로가고 b가 아래로 가야 만나서 터짐
					if (a.direction == 0 && b.direction == 1) {
						boomList.add(new Pair(i, j, Math.abs(b.y - a.y)/2));
					}	
				}
				
				// 같은 y좌표를 갖는 원자들이 수평선에서 만날 때 
				if (a.y == b.y) {
					if (a.direction == 3 && b.direction == 2) {
						boomList.add(new Pair(i, j, Math.abs(b.x - a.x)/2));
					}	
				}
				
				// / 대각선 라인에 있는 대상들이 만날 때
				if (a.x - a.y == b.x - b.y) {
					if ((a.direction == 3 && b.direction == 1) || (a.direction == 0 && b.direction == 2)) {
						boomList.add(new Pair(i, j, Math.abs(b.x - a.x)));
					}
				}
				
				// \ 대각선 라인에 있는 대상들이 만날 때 
				if (a.x + a.y == b.x + b.y) {
					if ((a.direction == 1 && b.direction == 2) || (a.direction == 3 && b.direction == 0)) {
						boomList.add(new Pair(i, j, Math.abs(b.x - a.x)));
					}
				}
			}
		}
		
		return getTotalEnergy(boomList);
	}

	public static int getTotalEnergy(ArrayList<Pair> boomList) {
		// 터지는 시간 기준 오름 차순 정렬
		Collections.sort(boomList);

		// 이미 터진 원자 체크용
		boolean[] visited = new boolean[N];

		int totalEnergy = 0;

		// 충돌 리스트 순회
		for (int i = 0; i < boomList.size(); ) {
			// 현재의 시간
			int currentTime = boomList.get(i).time;

			// 동시에 터질 원자들을 일괄 처리하기 위해 리스트로 수집
			ArrayList<Integer> toBoom = new ArrayList<>();

			// 같은 시간 동안 터질 수 있는 것들을 찾기 
			int j = i;
			while (j < boomList.size() && boomList.get(j).time == currentTime) {
				int idx1 = boomList.get(j).i;
				int idx2 = boomList.get(j).j;

				// 두 원자 모두 아직 터지지 않았을 경우만 처리
				// 하나라도 방문처리가 되어있으면 그것은 이미 터져있기에 터지지 않음 
				if (!visited[idx1] && !visited[idx2]) {
					toBoom.add(idx1);
					toBoom.add(idx2);
				}
				j++;
			}

			// 중복 제거를 위해 Set 사용. 터진 인덱스를 저장하기 
			HashSet<Integer> uniqueAtoms = new HashSet<>(toBoom);

			// 각 원자의 에너지를 누적하고 visited 체크
			for (int idx : uniqueAtoms) {
				totalEnergy += list.get(idx).energy;
				visited[idx] = true;
			}

			// i를 처리한 마지막 j로 이동
			i = j;
		}

		return totalEnergy;
	}

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= testCase; test_case++) {
			
			N = Integer.parseInt(br.readLine().trim());
			list = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				list.add(new Atom(x, y, d, e));
			}
			
			int result = makeBoomPair();
			sb.append('#').append(test_case).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
}