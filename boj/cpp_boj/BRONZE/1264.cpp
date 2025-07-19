// 1264. 모음의 개수 -> 브론즈 4

#include <bits/stdc++.h>

using namespace std;

int main(void) {
  ios::sync_with_stdio(0); cin.tie(0);

  char aeiou[5] = {'a', 'e', 'i', 'o', 'u'};
  string s;
  int cnt;
  while (true) {
    getline(cin, s);
    if (s=="#") break;

    cnt=0;
    for (char c : s) {
      if ('a' <= c && c <= 'z') {
        if (c=='a' || c=='e' || c=='i' || c=='o' || c=='u') {
          cnt++;
        }
      }
      else if ('A' <= c && c <= 'Z') {
        if (c=='A' || c=='E' || c=='I' || c=='O' || c=='U') {
          cnt++;
        }
      }
    }

    cout << cnt << '\n';

  }

  return 0;
}