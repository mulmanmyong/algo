// 1259. 팰린드롬수 -> 브론즈 1

#include <bits/stdc++.h>

using namespace std;

int main(void)
{
  ios::sync_with_stdio(0); cin.tie(0);

  string s;
  while (true) {
    cin >> s;
    if (s == "0") break;

    int st=0, ed=s.length()-1;
    while (st <= ed) {
      if (s[st] != s[ed]) break;
      st++; ed--;
    }

    if (st <= ed) cout << "no\n";
    else  cout << "yes\n";
  }

  return 0;
}
