// 1120. 단어 정렬 -> 실버 4

#include <bits/stdc++.h>

using namespace std;

void func1(void)
{
  string a, b;
  cin >> a >> b;

  int cnt=0;
  if (a.length() == b.length()) {
    for (int i = 0; i < a.length(); i++) {
      if (a[i] != b[i]) cnt++;
    }
    cout << cnt;
  }
  else {
    int min_cnt = 51;
    for (int i = 0; i <= b.length()-a.length(); i++) {
      cnt=0;
      for (int j = 0; j < a.length(); j++) {
        if (a[j] != b[i+j]) cnt++; 
      }
      min_cnt = min(min_cnt, cnt);
    }
    cout << min_cnt;
  }
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}
