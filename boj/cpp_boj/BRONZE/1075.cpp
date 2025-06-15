// 1075. 나누기 -> 브론즈 2

#include <bits/stdc++.h>

using namespace std;

void func1() {
  long long n, f;
  cin >> n >> f;
  // 뒷 두자리 00으로 변경
  n = n - n%100;
  for (int i = n; i <= n+99; i++) {
    if (i%f == 0) {
      if (i%100 < 10)  cout << 0;
      cout << i%100 << '\n';
      break;
    }
  }
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}