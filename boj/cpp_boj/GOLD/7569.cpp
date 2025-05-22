// 7569. 토마토 -> 골드 5

#include <bits/stdc++.h>

using namespace std;

int m, n, h;
int arr[101][101][101];
int dx[6] = {1, 0, 0, -1, 0, 0};
int dy[6] = {0, 1, 0, 0, -1, 0};
int dz[6] = {0, 0, 1, 0, 0, -1};

void func1(void) {
  cin >> m >> n >> h;
  queue<pair<int, pair<int, int>>> q;
  for (int i = 0; i < h; i++) {
    for (int j = 0; j < n; j++) {
      for (int k = 0; k < m; k++) {
        cin >> arr[i][j][k];
        if (arr[i][j][k] == 1)  q.push({i, {j, k}}); // 토마토가 있는 곳
      }
    }
  }

  while (!q.empty()) {
    int c = q.front().first;
    int b = q.front().second.first;
    int a = q.front().second.second;
    q.pop();

    for (int dir = 0; dir < 6; dir++) {
      int nx = a + dx[dir];
      int ny = b + dy[dir];
      int nz = c + dz[dir];

      if (nx < 0 || ny < 0 || nz < 0 || nx >= m || ny >= n || nz >= h)  continue;
      if (arr[nz][ny][nx] != 0) continue;
      arr[nz][ny][nx] = arr[c][b][a]+1;
      q.push({nz, {ny, nx}});
    }
  }

  int day = 0; // 다 익는데 최소 걸리는 시간 담을 변수, 
  // 다 안익었을 땐 -1, 안 익은 부분만 전이되도록 bfs 진행했으므로, 나오는 값의 최대값이 다 익는 데 걸리는 최소 시간
  for (int i = 0; i < h; i++) {
    for (int j = 0; j < n; j++) {
      for (int k = 0; k < m; k++) {
        if (arr[i][j][k] == 0) { // 안 익은 놈 존재
          cout << -1;
          return;
        }
        day = max(day, arr[i][j][k]-1); // 최초에 다 익었을 땐 0, 즉, 모두가 1이면 0일 걸린 거므로 -1해줘야함
      }
    }
  }
  cout << day;
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}