// 14502. 연구소 -> 골드 4

#include <iostream>
#include <algorithm>
#include <queue>
#include <cstring>

using namespace std;

#define MAX_SIZE 8

int n, m, cnt = 0, ans = 0;

int map[MAX_SIZE][MAX_SIZE];
int cpy_map[MAX_SIZE][MAX_SIZE];
int visited[MAX_SIZE][MAX_SIZE] = {0,};

int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

void find_max() {

    int tmp = 0;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {

            if (cpy_map[i][j] == 0) {
                tmp++;
            }

        }
    }

    if (ans < tmp) {
        ans = tmp;
    }
}

void bfs() {

    memset(visited, 0, sizeof(visited)); // visited 초기화

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cpy_map[i][j] = map[i][j];
        }
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {

            if (cpy_map[i][j] == 2 && visited[i][j] == 0) {
                // virus 발견 하고, 이미 왔던 곳이 아닐경우만 => 중복될 경우도 있을 거 같은?
                queue< pair<int, int> > q;
                q.push(make_pair(i, j));
                visited[i][j] = 1;

                while (!q.empty()) {
                    int a = q.front().first;
                    int b = q.front().second;
                    q.pop();

                    for (int k = 0; k < 4; k++) {
                        int nx = a + dx[k];
                        int ny = b + dy[k];

                        // 바이러스 점염 시키는 조건, 빈칸이어야하고, 방문하지 않은 곳이어야 함 근데 0인곳만 점염시키면 굳이 visited 필요없느듯
                        // + 필요할 수도 있음, 바이러스 찾는 과정에서 중복으로 감염시키기 할 가능성이 있음
                        // 범위 벗어나지 않고, 
                        if (nx >= 0 && nx < n && ny >= 0 && ny < m && cpy_map[nx][ny] == 0) {
                            q.push(make_pair(nx, ny));
                            visited[nx][ny] = 1;
                            cpy_map[nx][ny] = 2;
                        }
                    }
                }
            }
        }
    }

    // max 값 찾기
    find_max();

}

void wall() {

    if (cnt == 3) {
        return bfs();
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {

            if (map[i][j] == 0) {
                cnt++;
                map[i][j] = 1;
                wall(); // 재귀
                cnt--;
                map[i][j] = 0; // 원래대로 돌리기, 모든 벽 세우는 경우의 수를판단하기 위해
            }
        }
    }
}

int main() {

    // 기존에 제공되어있는 지도에서 벽을 3개 추가할 것 -> 브루트포스로 0으로 되어있는 곳의 모든 경우 탐색
    // 그 이후 바이러스 퍼짐 -> bfs를 이용해서 바이러스의 위치에서 1로 막힐때까지 2로 변경
    // 안전지대의 최대개수 -> 나중에 점염 다 시키고, count하기, 이를 최대 개수 업데이트 지속할 것 

    cin >> n >> m;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> map[i][j]; 
        }
    }

    wall();

    cout << ans << endl;

    return 0;
}