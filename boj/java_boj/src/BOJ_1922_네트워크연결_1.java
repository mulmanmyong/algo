import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//유니온파인드를 이용하여 크루스칼로 풀어보기, 배열로 간선처리 
public class BOJ_1922_네트워크연결_1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	// 컴퓨터의 수 N
	static int N;
	// 연결할 수 있는 선의 수 M
	static int M;
	// 부모 배열
	static int[] parentArray;
	// 배열을 이용해서 간선 처리, 클래스 만드는 대신!!
	static int[][] edgeList;
	
	// 부모 배열 생성
	static void make() {
		parentArray = new int[N+1];
		for (int i = 0;  i <= N; i++) {
			parentArray[i] = i;
		}
	}
	
	// 루트 노드를 찾는 메서드
	static int find(int element) {
		
		// 내가 곧 부모면 바로 리턴
		if (parentArray[element] == element)	return element;
		//그렇지 않으면 부모 찾아오기
		// 경로압축
		return parentArray[element] = find(parentArray[element]);
	}
	
	// 합치기
	static boolean union(int element1, int element2) {
		// 각각의 루트를 찾아옴
		int e1Parent = find(element1);
		int e2Parent = find(element2);
		
		// 루트가 같으면 같은 트리니깐 합칠 수 없음
		if (e1Parent == e2Parent)	return false;
		//그렇지 않으면 합치기
		// a와 b가 연결되어있다는 것은 a에서 b로 연결하는 선이 있고, b와 c를 연결하는 선이 있으면 a와c도 ㅇ뎐결되어있다
		// b의 부모가 a가 되도록 합치기
		parentArray[e2Parent] = e1Parent;
		return true; // 합치기 성공
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
	
		N = Integer.parseInt(br.readLine().trim());
		M = Integer.parseInt(br.readLine().trim());
		
		// M개의 줄에 거쳐 컴퓨터 연결 정보가 주어짐
		// 무향그래프에, 가중치 정보까지 담기 
		// 컴퓨터를 연결하는데 드는 비용 a b c가 주어지면 a와 b가 연결되는 데의 비용 c가 든다.
		
		// 배열을 이용하여 간선의 가중치 처리
		// a -> b 형태로 연결처리
		edgeList = new int[M][3]; // M개 있고, from to weight(가중치)
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			edgeList[i][0] = Integer.parseInt(st.nextToken()); // from
			edgeList[i][1] = Integer.parseInt(st.nextToken()); // to
			edgeList[i][2] = Integer.parseInt(st.nextToken()); // weight
		}
		
		// 가중치를 기준으로 오름차순 정렬 ,, 앞에꺼가 더 작아야함ㅇㅇ
		Arrays.sort(edgeList, (a, b) -> a[2] - b[2]);
		
		// 최소비용구하기
		// 부모배열 생성
		make();
		
		// 최소신장크리 비용
		int minWeight = 0;
		// 처리된 간선 수
		int edgeCount = 0;
		
		for (int[] edge : edgeList) {
			// union 시도 -> 실패하면 싸이클 발생
			if (!union(edge[0], edge[1]))	continue;
            
			// 그렇지 않으면 성공!
			// 비용누적
            minWeight += edge[2];
            // 간선의 개수는 노드의 개수 N개보다 하나 적으니깐
            // 간선의 개수가 N-1개에 도달하면 종료 
            if (++edgeCount == N - 1) break;
        }
		sb.append(minWeight);
        System.out.println(sb);
	}
}
