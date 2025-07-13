// 1193. 분수찾기 -> 실버 5

#include <bits/stdc++.h>

using namespace std;

int main(void)
{
  ios::sync_with_stdio(0); cin.tie(0);

  int x;
  cin >> x;

  // 1은 대각선에 수 1개, 2는 2개 ... 이런식, 몇번째 대각선인지 먼저 판단
  int i=1;
  while (x > i) {
    x -= i;
    i++;
  }

  // 짝수면 분자가 차례대로 증가 분모 차례대로 감소, 홀수면 분모가 차례대로 증가 분자가 차례대로 감소
  if (i % 2 == 0) {
    cout << x << '/' << i+1-x;
  }
  else {
    cout << i+1-x << '/' << x;
  }

  return 0;
}
