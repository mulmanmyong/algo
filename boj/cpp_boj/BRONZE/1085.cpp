// 1085. 직사각형에서 탈출 -> 브론즈 3

#include <bits/stdc++.h>

using namespace std;

void func1() {
  int x, y, w, h;
  cin >> x >> y >> w >> h;
  cout << min(min(x, w-x), min(y, h-y)) << '\n';
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}