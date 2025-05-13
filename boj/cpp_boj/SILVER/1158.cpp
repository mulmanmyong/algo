// 1158. 요세푸스 문제 -> 실버 4

#include <bits/stdc++.h>

using namespace std;

void func1(void)
{
  // queue를 이용한 풀이
  int n, k;
  cin >> n >> k;

  queue<int> q;
  for (int i = 1; i <= n; i++)  q.push(i);
  cout << '<';
  while (!q.empty()) {
    for (int i = 0; i < k-1; i++) {
      q.push(q.front());
      q.pop(); // queue는 FIFO
    }
    cout << q.front();
    q.pop();
    if(q.size())  cout << ", "; // 마지막꺼 pop하면 q.size()는 0이 되므로 마지막 , 출력안되게
  }
  cout << '>';
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}