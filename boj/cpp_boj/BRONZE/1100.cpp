// 1100. 하얀 칸-> 브론즈 2

#include <bits/stdc++.h>

using namespace std;

void func1() {
  int ans = 0;
  string s;
  for (int i = 0; i < 8; i++) {
    cin >> s;
    for (int j = 0; j < 8; j++) {
      if ((i+j)%2 == 0 && s[j] == 'F') ans++;
    }
  }
  cout << ans << '\n';
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}