// 16926. 배열 돌리기 1 -> 골드 5

#include <bits/stdc++.h>

using namespace std;

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n, m, r;
  cin >> n >> m >> r;

  int arr[301][301];
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      cin >> arr[i][j];
    }
  }

  for (int layer = 0; layer < min(n, m) / 2; layer++)
  {
    deque<int> dq;

    int x1 = layer, y1 = layer;
    int x2 = n - layer - 1, y2 = m - layer - 1;

    // layer 요소 deque에 넣기 (시계방향 기준)
    // 왼쪽 세로줄 (위 → 아래)
    for (int i = x1; i < x2; i++) dq.push_back(arr[i][y1]);
    // 아래 가로줄 (왼 → 오)
    for (int j = y1; j < y2; j++) dq.push_back(arr[x2][j]);
    // 오른쪽 세로줄 (아래 → 위)
    for (int i = x2; i > x1; i--) dq.push_back(arr[i][y2]);
    // 위 가로줄 (오 → 왼)
    for (int j = y2; j > y1; j--) dq.push_back(arr[x1][j]);

    // 회전 초기와, 30번 회전에 30사이즈면 안돌아도 되니깐(원위치)
    int rotate_count = r % dq.size();
    for (int i = 0; i < rotate_count; i++)
    {
      int tmp = dq.back();
      dq.pop_back();
      dq.push_front(tmp);
    }

    // 다시 배열에 채우기
    // 왼쪽 세로줄
    for (int i = x1; i < x2; i++)
    {
      arr[i][y1] = dq.front();
      dq.pop_front();
    }
    // 아래 가로줄
    for (int j = y1; j < y2; j++)
    {
      arr[x2][j] = dq.front();
      dq.pop_front();
    }
    // 오른쪽 세로줄
    for (int i = x2; i > x1; i--)
    {
      arr[i][y2] = dq.front();
      dq.pop_front();
    }
    // 위 가로줄
    for (int j = y2; j > y1; j--)
    {
      arr[x1][j] = dq.front();
      dq.pop_front();
    }
  }

  // 결과 출력
  for (int i = 0; i < n; i++)
  {
    for (int j = 0; j < m; j++) {
      cout << arr[i][j] << ' ';
    }
    cout << '\n';
  }

  return 0;
}
