import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// DFS로 풀이 
public class BOJ_13023_ABCDE_1 {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	/*
	 * N명이 참가
	 * 사람번호는 0번부터 N-1번까지, 일부 사람들은 친구
	 * 아래의 친구관계를 만족하는 지 판단
	 * A는 B와 친구
	 * B는 C와 친구
	 * C는 D와 친구
	 * D는 E와 친구
	 * 즉, 연속 5명이 친구인 패턴을 찾는 것
	 * 
	 * 입력으로
	 * 첫째 줄에 사람의 수 N, 친구 관계의 수 M 주어짐
	 * M개의 줄에 거쳐 a와 b가 주어짐, a와 b는 친구다!
	 */
	
	static int N, M;
	// 인접리스트로 A->B->C->D->E 이런식으로 연결구조가 있는 지 확인
    static List<Integer>[] adjList;
    // 방문 처리
    static boolean[] visited;
    // 해당 패턴 찾았는 지 확인
    static boolean isFound;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
        // 인접 리스트 초기화
        adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adjList[a].add(b);
            adjList[b].add(a);
        }

        isFound = false;

        // 친구 패턴 있는 지 확인 
        for (int i = 0; i < N; i++) {
        	// 방문 배열 초기화
        	visited = new boolean[N];
            // 현재 사람으로 출발하여, 5명이 연결되어있는지 찾기
        	findPattern(i, 1);
        	// 패턴찾았으면 바로 탈출 
            if (isFound) break;
        }
        
        if (isFound) {
        	sb.append('1');
        }
        else {
        	sb.append('0');
        }
        System.out.println(sb);
    }

    static void findPattern(int current, int depth) {
        // 기저 조건 : 5명 연속 연결되었으면 종료
    	if (depth == 5) {
            isFound = true;
            return;
        }

    	// 현재 사람 방문 처리
        visited[current] = true;
        // 현재 사람과 연결되어 있는 사람 탐색 
        for (int next : adjList[current]) {
            // 이미 탐색한 곳이면 패스
        	if (visited[next])	continue;
        	// 그렇지 않으면 친구관계니깐 탐색 
            findPattern(next, depth + 1);
        }
        // 원복
        visited[current] = false;
    }
}
