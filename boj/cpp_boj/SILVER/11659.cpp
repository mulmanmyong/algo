// 11659. 구간 합 구하기 4 -> 실버 3

#include <bits/stdc++.h>

using namespace std;

int main(void) {
  ios::sync_with_stdio(0); cin.tie(0);
    
  int n, m;
  cin >> n >> m;

  int arr[100001], a;
  for (int i = 1; i <= n; i++) {
    cin >> a;
    arr[i] = arr[i-1] + a;
  }

  int st, end;
  while (m--) {
    cin >> st >> end;
    cout << arr[end]-arr[st-1] << '\n';
  }

  return 0;
}