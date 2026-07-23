#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(int n, vector<vector<int>> computers) {
    // bfs를 통해서 각 컴퓨터들이 연결되어있는 것을 풀 수 있을 듯 함
    // 왔던 길 다시 못돌아가게 하려면 방문 여부를 체크하면서 탐색
    // 방문하지 않은 컴퓨터를 시작점으로 bfs를 돌리고 끝날 때마다 answer 증가

    int answer = 0;

    // (i, i)은 항상 1 => 즉, 컴퓨터는 n-1까지 모두 존재하긴 한다는 것
    // 따라서 연결된 정보는 2차원으로 제공되니 방문확인은 1차원만 체크하면 된다
    // 방문한 컴퓨터
    vector<bool> visited(n, false);
    // 연결되는 컴퓨터를 담을 큐 -> bfs용도
    queue<int> q;

    // 0부터 n-1의 컴퓨터까지 탐색
    for (int i = 0; i < n; i++) {
        // 이미 방문한 컴퓨터면 넘어감
        if (visited[i]) continue;

        // 새로운 네트워크 시작
        visited[i] = true;
        q.push(i);

        // 연결된 컴퓨터가 없을때까지
        while (!q.empty()) {
            int cur = q.front();
            q.pop();

            // 현재 컴퓨터와 연결된 컴퓨터를 전부 확인
            for (int next = 0; next < n; next++) {
                // 연결되어 있지 않으면 넘어감
                if (computers[cur][next] == 0) continue;

                // 이미 방문했으면 넘어감
                if (visited[next]) continue;

                visited[next] = true;
                q.push(next);
            }
        }

        // 하나의 네트워크 탐색 완료
        answer++;
    }

    return answer;
}