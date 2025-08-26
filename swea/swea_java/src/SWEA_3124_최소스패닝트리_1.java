import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//union find를 이용해서 크루스칼로 풀이, 클래스 대신 리스트와 배열로 처리
public class SWEA_3124_최소스패닝트리_1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	// 정점의 개수 V, 간선의 개수 E
	static int V, E;
	// 노드의 부모를 저장
	static int[] parentArray;
	// 간선의 정보를 저장
	static int[][]	edgeList;
	
	// make set
	public static void make() {
		parentArray = new int[V+1];
		for (int index = 0; index <= V; index++) {
			parentArray[index] = index;
		}
	}
	
	// find parent
	public static int find(int element) {
		if (parentArray[element] == element)	return element;
		return parentArray[element] = find(parentArray[element]);
	}
	
	// union
	public static boolean union(int element1, int element2) {
		int e1Parent = find(element1);
		int e2Parent = find(element2);
		
		if (e1Parent == e2Parent) 	return false;
		
		if (e1Parent > e2Parent)	parentArray[e2Parent] = e1Parent;
		else	parentArray[e1Parent] = e2Parent;
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			st = new StringTokenizer(br.readLine().trim());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			edgeList = new int[E][3];
			for (int index = 0; index < E; index++) {
				st = new StringTokenizer(br.readLine().trim());
				edgeList[index][0] = Integer.parseInt(st.nextToken());
				edgeList[index][1] = Integer.parseInt(st.nextToken());
				edgeList[index][2] = Integer.parseInt(st.nextToken());
			}
			
			// 오름차순 정렬
			Arrays.sort(edgeList, (a, b) -> a[2]-b[2]);
			
			// 초기화
			make();
			
			// 최소 가중치
			long minWeight = 0;
			// 연결된 간선의 개수
			int edgeCount = 0;
			// union-find 알고리즘을 이용한 kruskal
			for (int[] edge : edgeList) {
				// union 불가 하면 패스! 싸이클발생
				if (!union(edge[0], edge[1]))	continue;
				// union 성공! 가중치 더하기
				minWeight += (long) edge[2];
				// 간선이 연결되었으니 간선의 개수 증가시키고, 노드-1은 간선의 개수. 간선의 개수가 최대 도달할 때 까지
				if (++edgeCount == V-1)	break;
			}	
			
			sb.append('#').append(test_case).append(' ').append(minWeight).append('\n');
		}
		System.out.print(sb);
	}
}
