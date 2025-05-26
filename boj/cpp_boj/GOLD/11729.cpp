// 11729. 하노이 탑 이동 순서 -> 골드 5

#include <bits/stdc++.h>

using namespace std;

void hanoi(int start, int mid, int end, int n) {
  if (n == 1) cout << start << ' ' << end << '\n';
  else {
    hanoi(start, end, mid, n-1);
    cout << start << ' ' << end << '\n';
    hanoi(mid, start, end, n-1);
  }
}

void func1(void) {
  int k;
  cin >> k;
  cout << (1<<k)-1 << '\n';
  hanoi(1, 2, 3, k);
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}