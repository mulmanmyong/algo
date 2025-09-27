import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20040_사이클게임_1 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

/*
 * 유적지 복원 프로젝트 진행
 * 유적지 고유한 번호로 식별, 유적지 사이를 통로로 연결하여 네트워크 구축
 * 무작정 이어 나가면 순환구조 생겨서 꼬임
 * 
 * 고고학자들의 작업 방식
 * 1. 유적지는 총 n개 있으며, 번호는 0부터 n-1까지 정해짐
 * 2. 계획한 m개의 유적지 연결 정보에 따라 통로를 설치하여 서로다른 유적지를 순차적 연결
 * 3. 각 통로는 한 번에 하나씩 설치, 이미 존재하는 통로를 중복설치 X
 * 4. 싸이클 형성될 수 있음. 싸이클 최초 발생시 프로젝트 중단
 * 
 *  [입력]
 *  유적지의 개수 n, 설치될 통로의 수 m
 *  다음 m줄에 걸쳐 유적지 번호 주어짐
 *  
 *  인접리스트를 이용해서 연결, 들어오는 차수에 따라서 진행
 *  
 */
	
	static int n, m;

    // Union-Find를 위한 parent 배열 선언 (각 노드의 부모를 저장)
    static int[] parent;

    public static void make() {
    	// parent 배열 초기화. 각 노드는 초기에 자기 자신을 부모로 가짐.
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }
    
    // x가 속한 집합의 루트 노드를 찾는 연산
    public static int find(int x) {
        // 자기 자신이 부모이면 루트 노드이므로 반환
        if (parent[x] == x) {
            return x;
        }
        // 경로 압축(Path Compression): 루트를 찾는 과정의 모든 노드가 직접 루트를 가리키도록 변경
        return parent[x] = find(parent[x]);
    }

    // a와 b가 속한 두 집합을 합치는 연산
    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        // 루트가 다를 경우에만 한쪽을 다른 쪽의 자식으로 붙여 합침
        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// n, m 입력받음
		st = new StringTokenizer(br.readLine().trim());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		make();
		
        int cycleTurn = 0; // 사이클이 발생한 차례를 저장할 변수

		// m개의 통로를 순서대로 설치 시도
		for (int i = 1; i <= m; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 두 유적지의 루트 노드가 같다면, 연결 시 사이클이 발생함
			if (find(a) == find(b)) {
				cycleTurn = i; // 사이클이 발생한 현재 차례를 기록
				break;         // 프로젝트 중단
			} else {
				// 사이클이 발생하지 않으면 두 유적지를 연결 (집합을 합침)
				union(a, b);
			}
		}
		
        // for문이 끝까지 돌았다면 cycleTurn은 0, 중간에 중단되었다면 해당 차례가 저장됨
		sb.append(cycleTurn);
		System.out.println(sb);
	}
}