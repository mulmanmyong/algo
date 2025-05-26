// 10158. 개미 -> 실버 3

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  int w, h;
  int p, q;
  int t;
  cin >> w >> h;
  cin >> p >> q;
  cin >> t;
  
  int x;
  if ((p+t)/w % 2 == 0) x = (p+t)%w;
  else  x = w-(p+t)%w;

  int y;
  if ((q+t)/h % 2 == 0) y = (q+t)%h;
  else  y = h-(q+t)%h;

  cout << x << ' ' << y << '\n';
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}