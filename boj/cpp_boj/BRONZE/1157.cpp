// 1157. 단어 공부-> 브론즈 1

#include <bits/stdc++.h>

using namespace std;

void func1() {

  int cnt[26] = {0, };
  int max = 0;
  char ans;
  string s;
  cin >> s;
  for (char c : s) {
    // 소문자
    if ('a' <= c && c <= 'z') c = c - 'a' + 'A'; // 대문자로 변환
    cnt[c-'A']++;
    if (cnt[c-'A'] > max) {
      max = cnt[c-'A'];
      ans = c;
    }
  }
  
  int chk=0;
  for (int i = 0; i < 26; i++) {
    if (cnt[i] == max)  chk++;
  }

  if (chk == 1) cout << ans << '\n';
  else  cout << "?\n";
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}