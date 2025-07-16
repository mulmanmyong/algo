// 4153. 직각삼각형 -> 브론즈 3

#include <bits/stdc++.h>

using namespace std;

int main(void) {
  ios::sync_with_stdio(0); cin.tie(0);
    
  int a, b, c;
  while (true) {
    cin >> a >> b >> c;
    if (a==0 && b==0 & c==0)  break;

    int mn = max(a, max(b, c));
    if (a*a+b*b+c*c-mn*mn==mn*mn) cout << "right\n";
    else  cout << "wrong\n";
  }

  return 0;
}