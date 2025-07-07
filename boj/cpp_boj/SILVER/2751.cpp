// 2751. 수 정렬하기 2 -> 실버 5

#include <bits/stdc++.h>

using namespace std;

int arr[1000001];

void func1() {
  int n;
  cin >> n;

  for (int i = 0; i < n; i++) {
    cin >> arr[i];
  }
  sort(arr, arr+n);
  for (int i = 0; i < n; i++) {
    cout << arr[i] << '\n';
  }
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}