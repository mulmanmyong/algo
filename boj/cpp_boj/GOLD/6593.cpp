#include <bits/stdc++.h>

using namespace std;

char arr[31][31][31];
int visited[31][31][31]; // 거리를 저장하기 위한 배열
int dz[6] = {1, 0, 0, -1, 0, 0};
int dy[6] = {0, 1, 0, 0, -1, 0};
int dx[6] = {0, 0, 1, 0, 0, -1};

void func1(void) {

  while (true) {
    int l, r, c;
    cin >> l >> r >> c;
    if (l == 0 && r == 0 && c == 0) break;

    for (int i = 0; i < l; i++) {
      for (int j = 0; j < r; j++) {
        fill(visited[i][j], visited[i][j] + c, -1);
      }
    }

    queue<pair<int, pair<int, int>>> q;
    for (int i = 0; i < l; i++) {
      for (int j = 0; j < r; j++) {
        string s;
        cin >> s;
        for (int k = 0; k < c; k++) {
          arr[i][j][k] = s[k];
          if (arr[i][j][k] == 'S') {
            q.push({i, {j, k}});
            visited[i][j][k] = 0;
          }
        }
      }
    }

    int shortest_time = -1;
    bool escaped = false;

    while (!q.empty()) {
      int aa = q.front().first;
      int bb = q.front().second.first;
      int cc = q.front().second.second;
      q.pop();

      if (arr[aa][bb][cc] == 'E') {
        shortest_time = visited[aa][bb][cc];
        escaped = true;
        break;
      }

      for (int dir = 0; dir < 6; dir++) {
        int nz = aa + dz[dir];
        int ny = bb + dy[dir];
        int nx = cc + dx[dir];

        if (nx < 0 || ny < 0 || nz < 0 || nx >= c || ny >= r || nz >= l)  continue;
        if (visited[nz][ny][nx] != -1 || arr[nz][ny][nx] == '#')  continue;

        visited[nz][ny][nx] = visited[aa][bb][cc] + 1; // 1분 추가
        q.push({nz, {ny, nx}});
      }
    }

    if (escaped)  cout << "Escaped in " << shortest_time << " minute(s).\n";
    else  cout << "Trapped!\n";
  }
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}