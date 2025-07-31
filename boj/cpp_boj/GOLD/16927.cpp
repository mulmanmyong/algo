// 16927. 배열 돌리기 2 -> 골드 5

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

  // 16926과 동일한 문제 시간 동일
  // 무작정 돌리기 보다, 회전 수 최적화를 통해 돌렸음. 그러면 무의미한 회전 x
  // 그리고 해결 아이디어도 무자정 layer마다 돌리기보다, deque를 이용해서 1차원으로 변경 후
  // deque의 성질을 이용해서 반시계 방향이니 맨 뒤에껄 빼고 삽입하는 형식으로 진행
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
