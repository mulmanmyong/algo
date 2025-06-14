// 1037. 약수 -> 브론즈 1

#include <bits/stdc++.h>

using namespace std;

void func1() {
  int n;
  int arr[51];

  cin >> n;
  for (int i = 0; i < n; i++) {
    cin >> arr[i];
  }
  sort(arr, arr+n);

  cout << arr[0]*arr[n-1] << '\n';
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}