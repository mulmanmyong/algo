import java.io.*;
import java.util.*;

public class BOJ_1916_최소비용구하기_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * 도시 1 ~ N까지 번호 매겨져 있음
 * 도시들 사이 버스 운행
 * 시작 도시 도착 도시, 그리고 비용으로 표현
 * 
 * 출발도시와 도착 도시가 주어질 때, 삼성이가 이동하는 최소 비용 구하기 
 * 
 * [입력]
 * 첫째 줄에 도시의 개수 N 주어짐
 * 둘째 줄에 버스의 개수 M 주어짐
 * 셋째 줄 부터 M개의 줄에 버스의 시작도시번호, 버스의 도착도시번호, 버스 이동에 필요한 비용이 주어짐
 * 마지막 줄에 출발 도시와 도착 도시 번호 주어짐
 * 
 * [출력]
 * 출발 도시에서 도착 도시까지 가는 최소 비용 출력
 * 
 * [엌]
 * 다익스트라를 합시다 
 */
	
	// 도시의 개수 N
	static int N;
	// 버스의 개수 M
	static int M;
	
	// 버스의 도착도시번호, 버스의 이동 비용을 담을 노드
	static class Node {
		int ed, w;

		public Node(int ed, int w) {
			super();
			this.ed = ed;
			this.w = w;
		}
	}
	
	// 연결을 확인할 인접리스트들
	static ArrayList<Node>[] nodeList;
	// 각 도시까지의 최소 비용을 저장할 배열
	static int[] dist;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 도시의 개수 입력
		N = Integer.parseInt(br.readLine().trim());
		// 버스의 개수 입력
		M = Integer.parseInt(br.readLine().trim());
		
		// 인접리스트 초기화
		nodeList = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			nodeList[i] = new ArrayList<>();
		}
		
		// M개의 버스 정보 입력
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			nodeList[start].add(new Node(end, weight));
		}
		
		// 출발지점과 도착지점 입력
		st = new StringTokenizer(br.readLine().trim());
		int startCity = Integer.parseInt(st.nextToken());
		int endCity = Integer.parseInt(st.nextToken());
		
		// 다익스트라 진행
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		pq.add(new int[] {startCity, 0});
		
		// 최소 비용 거리 배열 초기화
		dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[startCity] = 0;
		
		int ans = 0;
		// 진행
		while (!pq.isEmpty()) {
			int curCity = pq.peek()[0];
			int curWeight = pq.peek()[1];
			pq.poll();
			
			// 이미 처리된 노드이거나, 더 긴 경로면 무시
			if (dist[curCity] < curWeight) continue;
			
			// 제일 먼저 나온 curCity == endCity가 제일 작은 가중치 
			if (curCity == endCity) {
				// 결과 입력하고 종료 
				ans = curWeight;
				break;
			}
			
			// 이어져 있는 도시 찾기
			for (Node next : nodeList[curCity]) {
				int nextCity = next.ed;
				int nextWeight = next.w;
				
				// 그렇지 않다면 진행
				// 근데 기존의 최소 비용보다 더 짧은 경우만 추가
				if (dist[nextCity] > curWeight + nextWeight) {
					dist[nextCity] = curWeight + nextWeight;
					pq.add(new int[] {nextCity, dist[nextCity]});
				}
			}
		}
		
		// 다익스트라 종료 결과 출력
		sb.append(ans);
		System.out.println(sb);
	}
}
