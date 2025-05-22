// 7562. 나이트의 이동 -> 실버 1

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
 int t, n, start_x, start_y, end_x, end_y;
 int arr[301][301];
 int dx[8] = {-2, -2, -1, -1, 1, 1, 2, 2}; // 나이트의 이동방식
 int dy[8] = {1, -1, 2, -2, 2, -2, 1, -1};
 cin >> t;
 while (t--) {
  cin >> n;
  for (int i = 0; i < n; i++) fill(arr[i], arr[i]+n, -1);
  queue<pair<int, int>> q;
  cin >> start_x >> start_y;
  cin >> end_x >> end_y;
  arr[start_x][start_y] = 0;
  q.push({start_x, start_y});

  while (!q.empty() || arr[end_x][end_y] == -1) {
    int a = q.front().first;
    int b = q.front().second;
    q.pop();

    for (int dir = 0; dir < 8; dir++) {
      int nx = a + dx[dir];
      int ny = b + dy[dir];
      if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
      if (arr[nx][ny] != -1)  continue;
      arr[nx][ny] = arr[a][b] + 1;
      q.push({nx, ny});
    }
  }
  cout << arr[end_x][end_y] << '\n';
 }
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}