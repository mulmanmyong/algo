// 14716. 현수막 -> 실버 1

#include <bits/stdc++.h>

using namespace std;

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  int m, n;
  cin >> m >> n;

  // 배열 입력
  int arr[251][251] = {0};
  for (int i = 0; i < m; i++)
  {
    for (int j = 0; j < n; j++)
    {
      cin >> arr[i][j];
    }
  }

  // 8방향 탐색 delta 배열
  int deltaRow[] = {1, -1, 0, 0, 1, -1, 1, -1};
  int deltaColumn[] = {0, 0, 1, -1, 1, -1, -1, 1};
  // bfs를 하여 1이 연결되어있는 것 확인
  // 1을 마주하면 해당 위치는 0으로 변경하기 -> 반복적으로 이동하는 것 방지
  queue<pair<int, int>> q; // queue
  int ans = 0;
  for (int i = 0; i < m; i++)
  {
    for (int j = 0; j < n; j++)
    {
      if (arr[i][j] == 1)
      {
        ans++;
        q.push({i, j});
        arr[i][j] = 0; // 재방문 방지
      }

      // 8방향으로 이어진 1 확인하기
      while (!q.empty())
      {
        int a = q.front().first;
        int b = q.front().second;
        q.pop();

        // 8방향 체크
        for (int dir = 0; dir < 8; dir++)
        {
          int newRow = a + deltaRow[dir];
          int newColumn = b + deltaColumn[dir];

          // 배열의 범위를 벗어나면 패스
          if (newRow < 0 || newRow >= m || newColumn < 0 || newColumn >= n)
            continue;
          // 배열의 범위 안이면 다음위치가 1인지 체크, 1이면 해당좌표 q에 넣고, 0으로 바꿈(중복방문방지)
          if (arr[newRow][newColumn] == 1)
          {
            q.push({newRow, newColumn});
            arr[newRow][newColumn] = 0;
          }
        }
      }
    }
  }

  cout << ans;

  return 0;
}
