import java.io.*;
import java.util.*;

public class BOJ_16118_달빛여우_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

/*
 * 관악산에 1~N까지 번호가 붙은 N개의 나무 그루터기가 있음. 그루터기들 사이에는 M개의 오솔길이 나 있음
 * 오솔길은 어떤 방향으로든 지나갈 수 있으며, 어떤 두 그루터기 사이에 두 개 이상의 오솔길이 나 있는 경우는 없음 
 * 달빛 여우와 달빛 늑대는 1번 나무 그루터기에 살고 있음 
 * * 보름달이 뜨면 나무 그루터기들 중 하나가 달빛을 받아 밝게 빛남. 그러면 여우와 늑대는 먼저 달빛 독차지를 위해 오솔길을 따라서 달려나감 
 * 여우는 일정한 속도로 달려감. 늑대는 여우의 하나 여우의 2배빠르게, 하나 여우의 2배 느리게..  이를 반복함 
 * 각각 가장 빠르게 달빛이 비치는 그루터기까지 다다를 수 있는 경로로 이동함. 따라서 둘의 이동 경로가 다를 수도 있음 
 * * 여우를 조금 더 사랑해주기로 해서 여우가 늑대보다 먼저 도착할 수 있는 그루터기는 몇개 있는가?? 
 * * [입력]
 * 그루터기의 개수 N, 오솔길의 개수 M
 * M개에 거쳐 a, b, d 입력하고 a그루터기와 b그루터기 사이에 길이가 d인 오솔길이 나있음을 의미 	
 * * 이것은 다익스트라일것 
 */
	
	static int N, M;
	static ArrayList<double[]>[] adjList;
	
	// 여우의 최단거리
	static double[] foxMinDistance;
	// 늑대의 최단거리 [][0]: 빠르게 달릴 차례, [][1]: 느리게 달릴 차례
	static double[][] wolfMinDistance;
	
	public static void dijkstraFOX(int startTree) {
		// 우선 순위큐를 이용하여 최소 거리를 계산하기
		// 도착할 노드와 가중치 
		PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(a[1], b[1]));
		
		// 시작점 초기화
		foxMinDistance[startTree] = 0;
		pq.add(new double[] {startTree, 0});
		
		// pq가 빌 때 까지
		while (!pq.isEmpty()) {
			int currentTree = (int) pq.peek()[0];
			double currentWeight = pq.peek()[1];
			pq.poll();

			// 이미 더 짧은 경로로 방문했다면 건너뛰기
			if (currentWeight > foxMinDistance[currentTree]) {
				continue;
			}
			
			// 현재 노드와 연결된 다음 노드 탐색
			for (double[] next : adjList[currentTree]) {
				int nextTree = (int) next[0];
				double nextWeight = next[1];
				
				// 더 짧은 경로를 발견하면 갱신하고 pq에 추가
				if (foxMinDistance[nextTree] > currentWeight + nextWeight) {
					foxMinDistance[nextTree] = currentWeight + nextWeight;
					pq.add(new double[] {nextTree, foxMinDistance[nextTree]});
				}
			}
		}
	}
	
	public static void dijkstraWOLF(int startTree) {
		// 우선 순위큐를 이용하여 최소 거리를 계산하기
		// 도착할 노드, 가중치, 빠르게/느리게 flag (1: 다음이 빠름, -1: 다음이 느림)
		PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(a[1], b[1]));
		
		// 시작점 초기화 (첫 걸음은 빠르게)
		wolfMinDistance[startTree][0] = 0;
		pq.add(new double[] {startTree, 0, 1});
		
		// pq가 빌 때 까지
		while (!pq.isEmpty()) {
			int currentTree = (int) pq.peek()[0];
			double currentWeight = pq.peek()[1];
			int currentFlag = (int) pq.peek()[2];
			pq.poll();

			int flagIndex = (currentFlag == 1) ? 0 : 1;
			// 이미 더 짧은 경로로 방문했다면 건너뛰기
			if (currentWeight > wolfMinDistance[currentTree][flagIndex]) {
				continue;
			}

			// 현재 노드와 연결된 다음 노드 탐색
			for (double[] next : adjList[currentTree]) {
				int nextTree = (int) next[0];
				double nextWeight = next[1];
				
				double cost = 0;
				int nextFlag = -currentFlag; // 다음 스텝의 상태는 현재와 반대
				int nextFlagIndex = (nextFlag == 1) ? 0 : 1;

				// 2배 빠르게
				if (currentFlag == 1) {
					cost = currentWeight + (nextWeight / 2.0);
				}
				// 2배 느리게
				else {
					cost = currentWeight + (nextWeight * 2.0);
				}
				
				// 더 짧은 경로를 발견하면 갱신하고 pq에 추가
				if (wolfMinDistance[nextTree][nextFlagIndex] > cost) {
					wolfMinDistance[nextTree][nextFlagIndex] = cost;
					pq.add(new double[] {nextTree, cost, nextFlag});
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 그래프의 정보를 입력받을 인접리스트 초기화
		adjList = new ArrayList[N+1];
		for (int i = 0; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		// a b d 입력 받기 (M번 반복)
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine().trim());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			// 무향 그래프
			// 늑대가 2배빨라졌다 느려졌다 하니깐,, 잘 나눠지기 위해 가중치에 2 곱해주기 
			adjList[a].add(new double[] {b, d*2.0});
			adjList[b].add(new double[] {a, d*2.0});
		}
		
		// 최단 거리 배열 초기화
		foxMinDistance = new double[N+1];
		wolfMinDistance = new double[N+1][2];
		Arrays.fill(foxMinDistance, Double.MAX_VALUE);
		for(int i=0; i<=N; i++){
			Arrays.fill(wolfMinDistance[i], Double.MAX_VALUE);
		}
		
		// 시작 노드는 1번 나무임
		// 다익스트라 알고리즘을 한 번씩만 실행하여 모든 노드까지의 최단거리를 구함
		dijkstraFOX(1);
		dijkstraWOLF(1);
		
		// 여우가 늑대보다 최단거리를 움직였을 경우를 count
		int result = 0;
		for (int i = 1; i <= N; i++) {
			// 늑대는 두 상태(빠르게 도착, 느리게 도착) 중 더 유리한 시간을 선택
			double minWolfTime = Math.min(wolfMinDistance[i][0], wolfMinDistance[i][1]);
			// 여우의 도착 시간과 비교
			if (foxMinDistance[i] < minWolfTime) {
				result++;
			}
		}
		
		sb.append(result);
		System.out.println(sb);
	}
}