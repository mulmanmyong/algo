#include <iostream>
#include <algorithm>

using namespace std;

int main() {
  ios::sync_with_stdio(0); cin.tie(0);
  int slen;
  string s;
  cin >> slen;
  cin >> s;

  int cnt[5] = {0}; // usopc
  for(int i = 0; i < slen; i++) {
    if(s[i] == 'u')      cnt[0]++;
    else if(s[i] == 's') cnt[1]++;
    else if(s[i] == 'o') cnt[2]++;
    else if(s[i] == 'p') cnt[3]++;
    else if(s[i] == 'c') cnt[4]++;
  }

  int ans = cnt[0];
  for (int i = 1; i < 5; i++) {
    ans = min(ans, cnt[i]);
  }

  cout << ans;

  return 0;
}