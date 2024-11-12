// 1260. DFS와 BFS - 실버 2

#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <cstring> // memset

using namespace std;

vector<int> vec[10001];
bool visit[1001]; // 노드의 개수 1000개

vector<int> dfs_ans;
vector<int> bfs_ans;

void dfs(int x) {
    // dfs는 깊이 우선 탐색으로 먼저 이어진 간선을 끝까지 탐색한 후, 돌아오고 탐색하지 않은 부분을 탐색
    visit[x] = true; // 방문함
    dfs_ans.push_back(x);

    for (int i = 0; i < vec[x].size(); i++) {
        if (!visit[vec[x][i]]) { // 방분라지 않았으면 (!false)
            dfs(vec[x][i]); // 바로 이어진 다음 노드로 이동
        }
    }
}

void bfs(int x) {
    // bfs는 너비 우선 탐색으로 연결되어 있는 노드 먼저 다 탐색 후, 그 다음으로 이어져 나감
    queue<int> q;
    q.push(x); // 초기값 q에 담음
    visit[x] = true; // 방문함

    while (!q.empty()) {
        int tmp = q.front();
        q.pop();
        bfs_ans.push_back(tmp);

        for (int i = 0; i < vec[tmp].size(); i++) {
            if (!visit[vec[tmp][i]]) { // 방문하지 않은 곳만 탐색
                q.push(vec[tmp][i]);
                visit[vec[tmp][i]] = true;
            }
        }
    }  
}

int main() {

    int N, M, V, a, b;
    cin >> N >> M >> V;

    for (int i = 0; i < M; i++) {
        cin >> a >> b;
        vec[a].push_back(b);
        vec[b].push_back(a);
        // 양방향 그래프이기 때문에 양방향으로 연결해줘야 함
    }

    for (int i = 1; i <= N; i++){
        sort(vec[i].begin(), vec[i].end()); // 낮은 숫자부터 탐색.
    }

    // dfs 먼저
    dfs(V);
    memset(visit, false, sizeof(visit)); // 방문했던 흔적 초기화

    // 그 다음 bfs
    bfs(V);

    // 출력은 dfs 먼저
    for (int i = 0; i < dfs_ans.size(); i++) {
        cout << dfs_ans[i] << " ";
    }
    cout << endl;

    // 그 다음 bfs
    for (int i = 0; i < bfs_ans.size(); i++) {
        cout << bfs_ans[i] << " ";
    }
    cout << endl;

    return 0;
}