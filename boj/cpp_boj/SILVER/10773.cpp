// 10773. 제로 -> 실버 4

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  int k;
  cin >> k;

  stack<int> s;
  while(k--) {
    int n;
    cin >> n;
    if (n == 0) s.pop();
    else s.push(n);
  }

  long ans=0;
  while(!s.empty()) {
    ans += s.top();
    s.pop();
  }

  cout << ans;
}


int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}