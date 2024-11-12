// 2606. 바이러스 -> 실버 3

#include <iostream>
#include <algorithm>
#include <queue>
#include <vector>

using namespace std;

vector<int> vec[101];
int visit[101] = {0};
int cnt = 0;

void bfs(int x) {
    queue<int> q;
    q.push(x);
    visit[x] = 1;

    while (!q.empty()) {
        int tmp = q.front();
        q.pop();

        for (int i = 0; i < vec[tmp].size(); i++) {
            
            if (visit[vec[tmp][i]] == 0) {
                // 방문하지 않은 곳
                visit[vec[tmp][i]] = 1;
                q.push(vec[tmp][i]); // 다음 방문할 곳
                cnt++; // 바이러스에 감염된 곳
            }
        }
    }
}

int main() {

    int n, m, a, b;
    cin >> n >> m;

    for (int i = 0; i < m; i++) {
        cin >> a >> b;
        vec[a].push_back(b);
        vec[b].push_back(a);
        // 양방향 연결
    }

    for (int i = 1; i <= n; i++) {
        sort(vec[i].begin(), vec[i].end()); // 1부터 탐색할 수 있도록
    }

    bfs(1);
    cout << cnt << endl;


    return 0;
}