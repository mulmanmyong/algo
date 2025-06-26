// 1620. 나는야 포켓몬 마스터 이다솜 -> 실버 4

#include <bits/stdc++.h>
#include <unordered_map> 

using namespace std;

void func1() {
  int n, m;
  cin >> n >> m;
  unordered_map<string, string> mp;

  string poke;
  for (int i = 1; i <= n; i++) {
    cin >> poke;
    mp.insert({poke, to_string(i)});
    mp.insert({to_string(i), poke});
  }

  string cmd;
  while (m--) {
    cin >> cmd;
    cout << mp[cmd] << '\n';
  }
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}