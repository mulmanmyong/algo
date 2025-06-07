// 2475. 검증수 -> 브론즈 5
#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  int ans=0, tmp;
  for (int i = 0; i < 5; i++) {
    cin >> tmp;
    ans += (tmp*tmp);
  }
  ans %= 10;
  cout << ans;
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}