// 4170. 불! -> 골드 3

#include <bits/stdc++.h>

using namespace std;

int r, c;
char board[1001][1001];
int fire[1001][1001];
int jihoon[1001][1001];
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

void func1(void) {
  cin >> r >> c;
  for (int i = 0; i < r; i++) {
    fill(fire[i], fire[i]+c, -1);
    fill(jihoon[i], jihoon[i]+c, -1);
  }
  queue<pair<int, int>> q1;
  queue<pair<int, int>> q2;
  for (int i = 0; i < r; i++) {
    for (int j = 0; j < c; j++) {
      cin >> board[i][j];
      if (board[i][j] == 'F') {
        q1.push({i, j});
        fire[i][j] = 0;
      }
      else if (board[i][j] == 'J') {
        q2.push({i, j});
        jihoon[i][j] = 0;
      }
    }
  }

  // 불 전염
  while (!q1.empty()) {
    int a = q1.front().first;
    int b = q1.front().second;
    q1.pop();

    for (int i = 0; i < 4; i++) {
      int nx = a + dx[i];
      int ny = b + dy[i];
      if (nx < 0 || nx >= r || ny < 0 || ny >= c) continue;
      if (fire[nx][ny] >= 0 || board[nx][ny] == '#') continue;
      fire[nx][ny] = fire[a][b] + 1;
      q1.push({nx, ny});
    }
  }

  // 지훈이 이동
  while (!q2.empty()) {
    int a = q2.front().first;
    int b = q2.front().second;
    q2.pop();

    for (int i = 0; i < 4; i++) {
      int nx = a + dx[i];
      int ny = b + dy[i];
      if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
        cout << jihoon[a][b] + 1;
        return;
      }
      if (jihoon[nx][ny] >= 0 || board[nx][ny] == '#')  continue;
      if (fire[nx][ny] != -1 && fire[nx][ny] <= jihoon[a][b]+1) continue; // 지훈이가 도착한 시간에서 그 다음 움직이는 타이밍에 이미 불이 전파되어있거나 전파될 예정이면 못 감
      jihoon[nx][ny] = jihoon[a][b] + 1;
      q2.push({nx, ny});
    }
  }
  cout << "IMPOSSIBLE";
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}