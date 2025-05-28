// 2447. 별 찍기 - 10 -> 골드 5

#include <bits/stdc++.h>

using namespace std;

int n;
char arr[6562][6562];

void star(int a, int b, int n) {
  if (n == 1) {
    arr[a][b] = '*';
    return;
  }
  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
      if (i == 1 && j == 1) {
        continue; // 중간에 구멍 뚫린 지점
      }
      star(a+n/3*i, b+n/3*j, n/3);
    }
  }
}

void func1(void) {
  cin >> n;
  for (int i = 0; i < n; i++) {
    fill(arr[i], arr[i]+n, ' ');
  }
  star(0, 0, n);
  for (int i = 0; i < n; i++) {
    cout << arr[i] << '\n';
  }
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}
