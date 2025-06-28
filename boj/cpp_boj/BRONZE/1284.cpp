// 1284. 집 주소 -> 브론즈 3

#include <bits/stdc++.h>

using namespace std;

void func1() {
  int n, ans;
  while (1) {
    cin >> n;
    if (n == 0) break;

    ans=1;
    while (n > 0) {
      if (n%10 == 0) {
        ans += 4;
      }
      else if (n%10 == 1) {
        ans += 2;
      }
      else {
        ans += 3;
      }
      ans += 1;
      n = n/10;
    }

    cout << ans << '\n';
  }
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}