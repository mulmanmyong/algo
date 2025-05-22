// 5427. 불 -> 골드 4

#include <bits/stdc++.h>

using namespace std;

char arr[1001][1001];
int fire[1001][1001];
int sanggeun[1001][1001];
int dx[4] = {1, 0, -1, 0};
int dy[4] = {0, 1, 0, -1};

void func1(void) {
  int t;
  cin >> t;

  while (t--) {
    int w, h;
    cin >> w >> h;
    queue<pair<int, int>> qf;
    queue<pair<int, int>> qs;
    for (int i = 0; i < h; i++) {
      fill(fire[i], fire[i]+w, -1);
      fill(sanggeun[i], sanggeun[i]+w, -1);
    }
    for (int i = 0; i < h; i++) {
      string s;
      cin >> s;
      for (int j = 0; j < w; j++) {
        arr[i][j] = s[j];
        if (arr[i][j] == '*') {
          fire[i][j] = 0;
          qf.push({i, j});
        }
        if (arr[i][j] == '@') {
          sanggeun[i][j] = 0;
          qs.push({i, j});
        }
      }
    }

    // 불 전이
    while (!qf.empty()) {
      int a = qf.front().first;
      int b = qf.front().second;
      qf.pop();

      for (int dir = 0; dir < 4; dir++) {
        int nx = a + dx[dir];
        int ny = b + dy[dir];

        if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
        if (fire[nx][ny] != -1 || arr[nx][ny] == '#') continue;
        fire[nx][ny] = fire[a][b]+1;
        qf.push({nx, ny});
      }
    }

    // 상근이 탈출 작전
    bool escaped = false;
    while (!qs.empty()) {
      int a = qs.front().first;
      int b = qs.front().second;
      qs.pop();

      for (int dir = 0; dir < 4; dir++) {
        int nx = a + dx[dir];
        int ny = b + dy[dir];

        if (nx < 0 || ny < 0 || nx >= h || ny >= w) {
          // nx, ny 둘다 영역을 벗어나면 탈출 성공
          cout << sanggeun[a][b]+1 << '\n';
          escaped = true;
          break;
        }
        
        if (arr[nx][ny] == '#' || sanggeun[nx][ny] != -1) continue;
        if (fire[nx][ny] != -1 && fire[nx][ny] <= sanggeun[a][b] + 1) continue;
        sanggeun[nx][ny] = sanggeun[a][b]+1;
        qs.push({nx, ny});
      }
      if (escaped)  break;
    }
    if (!escaped) cout << "IMPOSSIBLE\n";
  }
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}