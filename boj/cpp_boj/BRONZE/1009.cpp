// 1009. 분산처리 -> 브론즈 2

#include <bits/stdc++.h>

using namespace std;

void func1() {
  int t;
  cin >> t;
  int a, b, n=1;
  while (t--) {
    cin >> a >> b;
    // n = pow(a, b); // 범위 넘음
    while (b--) {n = (n*a)%10;}
    if (n == 0)  cout << 10 << '\n';
    else  cout << n << '\n';
    n=1;
  }
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}