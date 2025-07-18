// 11279. 최대 힙 -> 실버 2

#include <bits/stdc++.h>

using namespace std;

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >> n;

  priority_queue<int> pq;
  int x;
  while (n--)
  {
    cin >> x;
    if (x == 0)
    {
      if (pq.empty())
      {
        cout << 0 << '\n';
      }
      else
      {
        cout << pq.top() << '\n';
        pq.pop();
      }
    }
    else {
      pq.push(x);
    }
  }
  
  return 0;
}