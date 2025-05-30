// 2583. 영역 구하기 -> 실버 1

#include <bits/stdc++.h>

using namespace std;

int m, n, k;
int arr[101][101];
int ans[101];
int visited[101][101];
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

void func1(void) {
  cin >> m >> n >> k;
  for (int t = 0; t < k; t++) {
    int x1, y1, x2, y2;
    cin >> x1 >> y1 >> x2 >> y2;
    for (int i = y1; i < y2; i++) {
      for (int j = x1; j < x2; j++) {
        arr[i][j] = -1;
      }
    }
  }

  int cnt = 0;

  for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
      if (arr[i][j] == -1 || visited[i][j] == 1) {
        continue;
      }

      cnt++;
      int area = 1;
      queue<pair<int, int>> q;
      q.push({i, j});
      visited[i][j] = 1;
      while (!q.empty()) {
        int a = q.front().first;
        int b = q.front().second;
        q.pop();

        for (int dir = 0; dir < 4; dir++) {
          int ny = a + dy[dir];
          int nx = b + dx[dir];

          if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
          if (visited[ny][nx] == 1 || arr[ny][nx] == -1)  continue;
          arr[ny][nx] += 1;
          visited[ny][nx] = 1;
          area++;
          q.push({ny, nx});
        }
      }
      ans[cnt-1] = area;
    }
  }
  sort(ans, ans+cnt);

  cout << cnt << '\n';
  for (int i = 0; i < cnt; i++) cout << ans[i] << ' ';
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}
