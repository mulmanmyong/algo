import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//union find를 이용해서 크루스칼로 풀이, 클래스 대신 리스트와 배열로 처리
public class SWEA_1251_하나로_1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	// 섬의 개수 N
	static int N;
	// 좌표를 담을 배열
	static List<int[]>	position;
	// 노드를 담을 배열
	static long[] parentArray;
	// 간선을 담을 배열
	static List<long[]> edgeList;
	// 세율 E
	static double E;
	// 최소 길이
	static long minLength;
	
	// make set
	public static void make() {
		// 이번에는 땅이 0번 부터 시작 
		parentArray = new long[N];
		for (int i = 0; i < N; i++) {
			parentArray[i] = i;
		}
	}
	
	// find
	public static long find(long element) {
		// 내가 곧 부모면 나를 return
		if (parentArray[(int) element] == element)	return element;
		// 그렇지 않으면 경로압축 및 부모를 찾아와라
		return parentArray[(int) element] = find(parentArray[(int) element]);
	}
	
	// union
	public static boolean union(long element1, long element2) {
		long e1Parent = find(element1);
		long e2Parent = find(element2);
		
		// 부모가 같으면 같은 트리 
		if (e1Parent == e2Parent) return false;
		
		// 랭크 관리 대신 편향되지 않게만
		if (e1Parent > e2Parent)	parentArray[(int) e2Parent] = e1Parent;
		else	parentArray[(int) e1Parent] = e2Parent;
		
		return true;
	}
	
	public static long calculate() {
		// 초기 거리계산에서 ^2 상태 그대로 유지
		return (long) Math.round(E*minLength);
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
	
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			N = Integer.parseInt(br.readLine().trim());
			
			position = new ArrayList<>();
			// x좌표 입력하기 
			StringTokenizer stX = new StringTokenizer(br.readLine().trim());
			// y좌표 입력하기 
			StringTokenizer stY = new StringTokenizer(br.readLine().trim());
			for (int index = 0; index < N; index++) {
				int x = Integer.parseInt(stX.nextToken());
				int y = Integer.parseInt(stY.nextToken());
				position.add(new int[] {x, y});
			}
			
			// 세율 입력받기
			E = Double.parseDouble(br.readLine().trim());
			
			
			// 가중치는 좌표간의 거리
			// 모든 땅들의 거리들을 계산함
			// 좌표 배열에 있는 인덱스 그대로 땅의 번호로 인지하여 from to weight계산
			edgeList = new ArrayList<>();
			for (int from = 0; from < N-1; from++) {
				for (int to = from+1; to < N; to++) {
					// from 좌표
					int fromX = position.get(from)[0];
					int fromY = position.get(from)[1];
					// to 좌표
					int toX = position.get(to)[0];
					int toY = position.get(to)[1];
					
					// 현재의 from과 to의 거리 계산 후 입력
					// (toX-fromX)^2 + (toY-fromY)^2 -> 어차피 추후에 ^2을 하니깐 이대로 저장
					edgeList.add(new long[] {from, to, (long) (toX-fromX)*(toX-fromX) + (long) (toY-fromY)*(toY-fromY)});
				}
			}
			
			// 간선의 길이 짧은 순으로 오름차순 정렬
			edgeList.sort((a, b) -> Long.compare(a[2], b[2]));
			
			// 초기화
			make();
			
			// 길이 짧은 간선부터 트리에 추가하기 
			int edgeCount = 0;
			minLength = 0;
			for (long[] edge : edgeList) {
				// 싸이클이 만들어지지 않았으면 패스
				if (!union(edge[0], edge[1]))	continue;
				// 만들어 졌으면 길이에 더하고
				minLength += edge[2];
				// 연결됐으니 edgeCount 증가하고 간선의 수 최대 도달했는 지 확인
				// 도달했으면 break
				if (++edgeCount == N-1)	break;
			}
			
			sb.append('#').append(test_case).append(' ').append(calculate()).append('\n');
		}
		System.out.println(sb);
	}
}
