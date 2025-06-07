// 1008. A/B -> 브론즈 5
#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  double a, b;
  cin >> a >> b;
  cout.precision(10); 
  cout << a/b;
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}