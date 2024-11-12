// 2178. 미로 탐색

#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

int N, M;

int vec[101][101];

int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

int bfs(int x, int y) {
    // bfs는 queue를 이용
    queue< pair<int, int> > q;
    q.push(make_pair(x, y));

    // q가 빌때까지 계속
    while(!q.empty()) {
        int a = q.front().first;
        int b = q.front().second;
        q.pop();

        // 4가지 방향 확인
        for (int i = 0; i < 4; i++) {
            int nx = a + dx[i];
            int ny = b + dy[i];

            // 범위 벗어난 경우는 무시
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                continue;
            }
            
            if (vec[nx][ny] == 0) {
                // 이동할 수 없는 칸인 경우
                continue;
            }

            if (vec[nx][ny] == 1) {
                // 처음 방문하거나 이동가능할 때
                vec[nx][ny] = vec[a][b] + 1; // 누적합 기법도 혼합 => 이동하는 위치 현 위치보다 한칸 이동했으니 1 더함
                q.push(make_pair(nx, ny)); // 이동
            }
        }
    }

    // 구현할 때 : "도착위치로 이동할 수 있는 경우만 입력으로 주어진다." 의 조건에 의해 항상 이동할 것을 이용하여 누적합 기법이용
    return vec[N-1][M-1]; // 마지막위치 도달
    
}

int main() {

    string str;
    cin >> N >> M;

    for (int i = 0; i < N; i++) {
        cin >> str;
        for (int j = 0; j < M; j++) {
            vec[i][j] = str[j] - '0';
        }
    }

    // bfs를 이용
    cout << bfs(0, 0) << endl; // 시작지점은 좌측 최상단

    return 0;
}