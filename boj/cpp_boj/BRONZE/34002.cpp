// 34002. 임스의 잠수맵 -> 브론즈 1

#include <bits/stdc++.h>

using namespace std;
#define need 25000

void func1() {
  int a, b, c;
  cin >> a >> b >> c;

  int s, v;
  cin >> s >> v;

  int l;
  cin >> l;

  int exp = l * 100;
  int time = 0;

  // 1. VIP 사우나 먼저
  while (v-- && exp < need) {
    exp += c * 30;
    time += 30;
    if (exp > need) {
      time -= (exp - need) / c;
      break;
    }
  }

  // 2. 심신 수련관
  while (s-- && exp < need) {
    exp += b * 30;
    time += 30;
    if (exp > need) {
      time -= (exp - need) / b;
      break;
    }
  }

  // 3. 이벤트 맵
  if (exp < need) {
    time += (need - exp + a - 1) / a; // 올림 나눗셈
  }

  cout << time << '\n';
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}