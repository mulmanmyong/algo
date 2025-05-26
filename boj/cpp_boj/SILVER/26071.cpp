// 26071. 오락실에 간 총총이 -> 실버 2

#include <bits/stdc++.h>

using namespace std;

int n;
char arr[1501][1501];
int min_row=1501, max_row=0, min_col=1501, max_col=0;

void func1(void) {
  cin >> n;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> arr[i][j];
      if (arr[i][j] == 'G') {
        min_row = min(min_row, i);
        max_row = max(max_row, i);
        min_col = min(min_col, j);
        max_col = max(max_col, j);
      }
    }
  }

  int v_cost = 0;
  if (max_row > min_row) { // G들이 여러 행에 걸쳐 있는 경우
    v_cost = min(max_row, (n - 1) - min_row); // 원점에서 최대 끝에서 제일 작은 지점까지의 거리 중 작은 것
  }

  int h_cost = 0;
  if (max_col > min_col) { // G들이 여러 열에 걸쳐 있는 경우
    h_cost = min(max_col, (n - 1) - min_col); // 원점에서 최대 끝에서 제일 작은 지점까지의 거리 중 작은 것
  }

  cout << v_cost + h_cost << '\n';
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}