// 7576. 토마토 -> 골드 5

#include <bits/stdc++.h>

using namespace std;

int m, n;
int board[1005][1005] = {0, };
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};


void func1(void) {
  cin >> m >> n;
  queue<pair<int, int>> q;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      cin >> board[i][j];
      if (board[i][j] == 1) { // 토마토가 초기에 익어 있는 곳, 이 곳에서 인접한 곳으로 초마토가 익음
        q.push({i, j});
      }
    }
  }

  while (!q.empty()) {
    int a = q.front().first;
    int b = q.front().second;
    q.pop();

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        int nx = a + dx[i];
        int ny = b + dy[i];

        if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
        if (board[nx][ny] == -1)  continue;
        if (board[nx][ny] == 0) {
          board[nx][ny] = board[a][b] + 1;
          q.push({nx, ny});
        }
      }
    }
  }

  int ans = 0;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (board[i][j] == 0) {
        cout << -1;
        return;
      }
      ans = max(ans, board[i][j]);
    }
  }

  cout << ans-1; // board가 1인 지점부터 누적합을 해왔기에, 모두가 다 익어져있을 땐 0 이 나와야 하므로 -1을 해야함
}


int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}