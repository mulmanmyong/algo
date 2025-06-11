// 1330. 두 수 비교하기 -> 브론즈 5

#include <bits/stdc++.h>

using namespace std;

void func1() {
  int a, b;
  cin >> a >> b;

  if (a > b)  cout << ">\n";
  else if (a < b) cout << "<\n";
  else  cout << "==\n";
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}