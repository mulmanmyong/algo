// 1076. 저항 -> 브론즈 2

#include <bits/stdc++.h>

using namespace std;

void func1() {
  string colorlist[10] = {"black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"};
  string color;
  long long ans=0;
  for (int i = 0; i < 3; i++) {
    cin >> color;

    if (i==2) {
      if (color == colorlist[0])  ans *= 1; 
      else if (color == colorlist[1])  ans *= 10;
      else if (color == colorlist[2])  ans *= 100;
      else if (color == colorlist[3])  ans *= 1000;
      else if (color == colorlist[4])  ans *= 10000;
      else if (color == colorlist[5])  ans *= 100000;
      else if (color == colorlist[6])  ans *= 1000000;
      else if (color == colorlist[7])  ans *= 10000000;
      else if (color == colorlist[8])  ans *= 100000000;
      else if (color == colorlist[9])  ans *= 1000000000;
    }
    else {
      ans *= 10;
      if (color == colorlist[0]) ans += 0;
      else if (color == colorlist[1]) ans += 1;
      else if (color == colorlist[2]) ans += 2;
      else if (color == colorlist[3]) ans += 3;
      else if (color == colorlist[4]) ans += 4;
      else if (color == colorlist[5]) ans += 5;
      else if (color == colorlist[6]) ans += 6;
      else if (color == colorlist[7]) ans += 7;
      else if (color == colorlist[8]) ans += 8;
      else if (color == colorlist[9]) ans += 9;
    }
  }
  cout << ans << '\n';
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}