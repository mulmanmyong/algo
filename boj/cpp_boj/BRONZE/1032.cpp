// 1032. 명령 프롬프트 -> 브론즈 1

#include <bits/stdc++.h>

using namespace std;

void func1() {
  int n;
  string cmd, ans;
  cin >> n;
  cin >> ans;
  n--;
  while (n--) {
    cin >> cmd;
    for (int i = 0; i < cmd.length(); i++) {
      if (ans[i] != cmd[i]) ans[i] = '?';
    }
  }
  cout << ans << '\n';
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}