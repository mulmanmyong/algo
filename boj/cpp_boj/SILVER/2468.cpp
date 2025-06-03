// 2468. 안전 영역 -> 실버 1

#include <bits/stdc++.h>

using namespace std;

int n;
int arr[101][101];
int visited[101][101];
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

void bfs(int i, int j, int t) {
  queue<pair<int, int>> q;
  q.push({i, j});

  while (!q.empty()) {
    int a = q.front().first;
    int b = q.front().second;
    q.pop();

    for (int dir = 0; dir < 4; dir++) {
      int nx = a + dx[dir];
      int ny = b + dy[dir];

      if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
      if (visited[nx][ny] || arr[nx][ny] <= t)  continue;
      visited[nx][ny] = true;
      q.push({nx, ny});
    }
  }
}

void func1(void) {
  int min_n=101, max_n=0, cnt=0, ans=0;
  cin >> n;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> arr[i][j];
      min_n = min(min_n, arr[i][j]);
      max_n = max(max_n, arr[i][j]);
    }
  }

  for (int t = min_n-1; t <= max_n; t++) {
    cnt=0;
    for (int i = 0; i < n; i++) {
      fill(visited[i], visited[i]+n, 0);
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (arr[i][j] > t && !visited[i][j]) {
          bfs(i, j, t);
          cnt++;
        }
      }
    }
    ans = max(ans, cnt);
  }
  cout << ans;
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}