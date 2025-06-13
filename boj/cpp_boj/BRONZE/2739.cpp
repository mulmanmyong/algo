// 2739. 구구단 -> 브론즈 5

#include <bits/stdc++.h>

using namespace std;

void func1() {
  int n;
  cin >> n;
  for (int i = 1; i <= 9; i++) {
    cout << n << " * " << i << " = " << n*i << '\n';
  }
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}