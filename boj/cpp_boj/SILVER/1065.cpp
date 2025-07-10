// 1065. 한수 -> 실버 4

#include <bits/stdc++.h>

using namespace std;

void func1(void)
{
  int n;
  cin >> n;
  // 앞자리가 뒷자리보다 크면 제외? 그럼 -의 차를 가진 등차수열이잖아
  // 3자리부터가 관건
  // 123 135 147 159 210 234 246 258 321 345 357 369 432 420 456 468

  if (n <= 99)  cout << n;
  else {
    int cnt=99;
    int num[3];
    for (int i = 100; i <= (n != 1000 ? n : 999); i++) {
      int idx=0, tmp=i;
      while (tmp > 0) {
        num[idx++] = tmp % 10;
        tmp /= 10;
      }

      if (num[0]-num[1] == num[1]-num[2]) cnt++;
    }
    cout << cnt;
  }
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}
