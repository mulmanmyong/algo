// 2669. 직사각형 네개의 합집합의 면적 구하기 -> 실버 5

#include <bits/stdc++.h>

using namespace std;

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  bool board[101][101] = {false};

  int x=0, y=0;
  for (int t = 0; t < 4; t++) {
    int a, b, c, d;
    cin >> a >> b >> c >> d;
    x = max(x, max(a, c));
    y = max(y, max(b, d));

    // 사각형의 구역만큼 색칠하기
    for (int i = a; i < c; i++) {
      for (int j = b; j < d; j++) {
        board[i][j] = true;
      }
    }

  }

  int ans=0;
  for (int i = 1; i < x; i++) {
    for (int j = 1; j < y; j++) {
      if (board[i][j])  ans++;
    }
  }

  cout << ans;

  return 0;
}