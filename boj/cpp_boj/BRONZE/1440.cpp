// 1440. 타임머신 -> 브론즈 2

#include <bits/stdc++.h>

using namespace std;

int main(void) {
  ios::sync_with_stdio(0);  cin.tie(0);

  string date;
  cin >> date;

  // 시를 나타내는 부분이 01~12 사이이면 += 2를 해주면 됨
  int ans =0;
  for (int i = 0; i < date.length(); i += 3) {
    int num = (date[i]-'0')*10 + (date[i+1]-'0');
    if (1 <= num && num <= 12)  ans += 2;
    if (59 < num) { // D는 0~9의 수 즉, 60 69 이런 수도 나올 수가 있음
      ans=0;
      break;
    }
  }

  cout << ans;

  return 0;
}