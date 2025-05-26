// 1074. Z -> 골드 5

#include <bits/stdc++.h>

using namespace std;

int z(int n, int r, int c) {
  if (n == 0) return 0;
  int half = 1<<(n-1);
  if (r < half && c < half) return z(n-1, r, c);
  else if (r < half && c >= half) return half*half + z(n-1, r, c-half);
  else if (r >= half && c < half) return 2*half*half + z(n-1, r-half, c);
  else if (r >= half && c >= half) return 3*half*half + z(n-1, r-half, c-half);
}

void func1(void) {
  int n, r, c;
  cin >> n >> r >> c;
  cout << z(n, r, c);
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}