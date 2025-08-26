import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// union-find를 이용한 풀이 
public class SWEA_3289_서로소집합_1 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int n, m;
	static int[] parentArr;
	
	// make set
	public static void make() {
		parentArr = new int[n+1];
		for (int i = 0; i < n+1; i++) {
			parentArr[i] = i;
		}
	}
	
	// find set
	public static int find(int element) {
		// 부모노드가 곧 나이면 내가 부모다
		if (parentArr[element] == element)	return element;
		
		// 그렇지 않다면 경로압축+부모노드 찾기
		return parentArr[element] = find(parentArr[element]);
		
	}
	
	// union set
	public static void union(int element1, int element2) {
		int e1Parent = find(element1);
		int e2Parent = find(element2);
		
		// 부모가 같으면 합칠 수 없음
		if (e1Parent == e2Parent)	return;
		
		// 그렇지 않다면 합치기 
		// 조건이 딱히 없으므로 합치기
		// e1 밑에 e2합치기 
		parentArr[e2Parent] = e1Parent;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			st = new StringTokenizer(br.readLine().trim());
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			// m개의 연산 수행
			make(); // 초기화
			sb.append('#').append(test_case).append(' ');
			for (int i = 0; i < m; i++) {
				// 연산은 ? a b 형태로 주어지고
				// 0 a b는 a가 포함되어 있는 집합과 b가 포함되어 있는 집합을 합친다는 것
				// 1 a b는 같은 집합에 속해있는 지 확인하기 
				st = new StringTokenizer(br.readLine().trim());
				int cmd = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				// 합치기 
				if (cmd == 0) {
					union(a, b);
				}
				// 같은 집합인지 판단하기 
				else if (cmd == 1) {
					int aRoot = find(a);
					int bRoot = find(b);
					// 부모가 같으면 같은 집합
					if (aRoot == bRoot)	sb.append('1');
					else sb.append('0');
				}
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
