// 2448. 별 찍기 - 11 -> 골드 4

#include <bits/stdc++.h>

using namespace std;

int n;

void star(int i, int j, int n) {
  if (j <= 2*i) {
    if  (n == 3 && !(i==1 && j==1)) {
      cout << '*';
    }
    else {
      star(i%(n/2), j%n, n/2);
    }
  }
  else {
    cout << ' ';
  }
}

void func1(void) {
  cin >> n;
  for (int i = 0; i < n; i++) {
    cout << string(n-i-1, ' '); // 앞 부분 공백
    for (int j = 0; j <= n+i; j++) {
      star(i, j, n);
    }
    cout << '\n';
  }
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}
