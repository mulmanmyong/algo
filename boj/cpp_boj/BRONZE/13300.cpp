// 13300. 방 배정 -> 브론즈 2

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  int n, k, ans=0;
  int arr[6][2] = {0,};

  cin >> n >> k;
  int a, b;
  for (int i = 0; i < n; i++) {
    cin >> a >> b;
    arr[b-1][a]++;
  }

  for (int i = 0; i < 6; i++) {
    for (int j = 0; j < 2; j++) {
      ans += (arr[i][j] / k);
      if (arr[i][j] % k > 0)  ans++;
    }
  }

  cout << ans << '\n';
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
} 