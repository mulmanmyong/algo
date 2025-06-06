#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  int n;
  int arr[1000001];
  cin >> n;
  for (int i = 0; i < n; i++) cin >> arr[i];
  sort(arr, arr+n);
  cout << arr[0] << ' ' << arr[n-1];
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}