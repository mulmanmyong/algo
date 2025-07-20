// 1292. 쉽게 푸는 문제 -> 브론즈 1
#include <bits/stdc++.h>

using namespace std;

int main(void) {
  ios::sync_with_stdio(0); cin.tie(0);
  
  int a, b;
  cin >> a >> b;

  int arr[1001], n=1;
  for (int i = 1; i <= b; i++) {
    for (int j = 1; j <= i; j++) {
      arr[n] = i + arr[n-1];
      if (n > b)  break;
      n++;
    }
  }

  cout << arr[b] - arr[a-1];

  return 0;
}