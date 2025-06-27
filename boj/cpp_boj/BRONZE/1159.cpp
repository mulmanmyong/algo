// 1159. 농구 경기 -> 브론즈 2

#include <bits/stdc++.h>

using namespace std;

void func1() {
  int cnt[26] = {0, };
  int n, chk=0;
  string s;
  cin >> n;
  for (int i = 0; i < n; i++) {
    cin >> s;
    cnt[s[0]-'a']++;
    if (cnt[s[0]-'a'] == 5) chk++;
  }

  if (chk==0) cout << "PREDAJA";
  else {
    for(int i = 0; i < 26; i++) {
      if (cnt[i] >= 5)  cout << (char)(i+'a');
    }
  }
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}