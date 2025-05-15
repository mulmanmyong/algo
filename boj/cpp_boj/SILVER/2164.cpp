// 2164. 카드2 -> 실버 4

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  int x;
  queue<int> q;
  cin >> x;
  for (int i = 1; i <= x; i++) {
    q.push(i);
  }

  while (q.size() > 1) {
      q.pop();
      if (q.size() == 1)  break;
      q.push(q.front());
      q.pop();
  }

  cout << q.front();
}


int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}