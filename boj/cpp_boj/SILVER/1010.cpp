// 1010. 다리 놓기 -> 실버 5

#include <bits/stdc++.h>

using namespace std;

void func1() {
  int t, n, m;
  long long ans;
  cin >> t;
  while (t--) {
    ans=1;
    cin >> n >> m;
    for (int i = 1; i <= n; i++) {
      ans *= m;
      ans /= i;
      m--;
    }
    cout << ans << '\n';
  }
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}