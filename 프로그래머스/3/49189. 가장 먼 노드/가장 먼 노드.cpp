#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int solution(int n, vector<vector<int>> edge) {
    // n개의 노드가 있는 그래프 존재
    // 1번부터 n번까지 번호가 적혀있음 (1-based)
    
    // 1번 노드와 가장 멀리 떨어진 노드의 개수를 구할 것
    // -> 가장 멀리 떨어진 노드는 최단경로로 이동했을 때 간선의 개수가 가장 많은 노드를 의미
    
    // 그래프를 저장하기 위해 인접 리스트 또는 인접 행렬을 사용할 수 있음
    // 인접 리스트는 실제로 연결된 노드의 번호들만 저장하기 때문에 이 문제에서 사용
    // BFS를 통해 1번 노드에서 각 노드까지의 최단거리를 구하고
    // 가장 큰 최단거리를 가진 노드의 개수를 구하면 됨
    
    // 인접 리스트 생성
    // graph[i]에는 i번 노드와 연결된 노드들이 저장됨
    vector<vector<int>> graph(n + 1);
    
    for (vector<int> e : edge) {
        int a = e[0];
        int b = e[1];
        
        // 양방향 그래프이므로 양쪽에 모두 저장
        graph[a].push_back(b);
        graph[b].push_back(a);
    }
    
    // 1번 노드에서 각 노드까지의 최단거리 저장
    // -1은 아직 방문하지 않았다는 의미
    vector<int> dist(n + 1, -1);
    
    queue<int> q;
    
    // 1번 노드에서 BFS 시작
    q.push(1);
    dist[1] = 0; // 시작점이니 거리는 0
    
    while (!q.empty()) {
        int current = q.front();
        q.pop();
        
        // 현재 노드와 연결된 노드들을 확인
        for (int next : graph[current]) {
            
            // 이미 방문한 노드는 넘어감
            if (dist[next] != -1) {
                continue;
            }
            
            // 다음 노드까지의 최단거리 저장
            dist[next] = dist[current] + 1;
            q.push(next);
        }
    }
    
    // 1번 노드에서 가장 먼 거리 찾기
    int maxDistance = 0;
    for (int i = 1; i <= n; i++) {
        maxDistance = max(maxDistance, dist[i]);
    }
    
    // 가장 먼 거리에 있는 노드 개수 세기
    int answer = 0;
    for (int i = 1; i <= n; i++) {
        if (dist[i] == maxDistance) {
            answer++;
        }
    }
    
    return answer;
}