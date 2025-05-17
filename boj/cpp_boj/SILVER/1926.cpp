// 1926. 그림 -> 실버 1

#include <bits/stdc++.h>

using namespace std;

int board[505][505] = {0, };
int visited[505][505] = {0, };
int n, m;
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

void func1(void) {
  cin >> n >> m;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      cin >> board[i][j];
    }
  }
  int cnt=0;
  int max_area=0;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j< m; j++) {
      if (board[i][j] == 0 || visited[i][j])  continue;
      cnt++;
      visited[i][j] = 1;
      queue<pair<int, int>> q;
      q.push({i, j});
      int area = 0;
      while (!q.empty()) {
        area++;
        pair<int, int> cur = q.front();
        q.pop();
        for (int dir = 0; dir < 4; dir++) {
          int nx = cur.first + dx[dir];
          int ny = cur.second + dy[dir];
          if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
          if (visited[nx][ny] || board[nx][ny] != 1)  continue;
          visited[nx][ny] = 1;
          q.push({nx, ny});
        }
      }
      max_area = max(max_area, area);
    }
  }
  cout << cnt << '\n' << max_area;
}


int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}