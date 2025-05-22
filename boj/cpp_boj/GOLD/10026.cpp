// 10026. 적록색약 -> 골드 5

#include <bits/stdc++.h>

using namespace std;

int n;
char arr[101][101];
int visited[101][101];
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

void bfs(int i, int j, char tar) {
  queue<pair<int, int>> q;
  q.push({i, j});

  while (!q.empty()) {
    int a = q.front().first;
    int b = q.front().second;
    q.pop();

    for (int dir = 0; dir < 4; dir++) {
      int nx = a + dx[dir];
      int ny = b + dy[dir];

      if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
      if (visited[nx][ny] != -1 || arr[nx][ny] != tar) continue;
      visited[nx][ny] = 1;
      q.push({nx, ny});
    }
  }
}

void func1(void) {
  cin >> n;
  for (int i = 0; i < n; i++) {
    string s;
    cin >> s;
    for (int j = 0; j < n; j++) {
      arr[i][j] = s[j];
    }
  }

  // 적록색약 아닌 사람
  int cnt1 = 0;
  for (int i = 0; i < n; i++) fill(visited[i], visited[i]+n, -1);
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      if (visited[i][j] == -1 && (arr[i][j] == 'R' || arr[i][j] == 'G' || arr[i][j] == 'B')) {
        cnt1++;
        bfs(i, j, arr[i][j]);
      }
    }
  }
  // 적록색약인 사람
  int cnt2 = 0;
  for (int i = 0; i < n; i++) fill(visited[i], visited[i]+n, -1);
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      if (arr[i][j] == 'G') arr[i][j] = 'R';
    }
  }
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      if (visited[i][j] == -1 && (arr[i][j] == 'R' || arr[i][j] == 'B')) {
        cnt2++;
        bfs(i, j, arr[i][j]);
      }
    }
  }

  cout << cnt1 << ' ' << cnt2;
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}