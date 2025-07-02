// 1233. 주사위 -> 브론즈 2

#include <bits/stdc++.h>

using namespace std;

int arr[81] = {0, };

void func1(void) {
  int s1, s2, s3;
  cin >> s1 >> s2 >> s3;

  for (int i = 1; i <= s1; i++) {
    for (int j = 1; j <= s2; j++) {
      for (int k = 1; k <= s3; k++) {
        arr[i+j+k]++;
      }
    }
  }

  int maxCnt = 0;
  int result = 0;
  for (int i = 3; i <= s1 + s2 + s3; i++) {
    if (arr[i] > maxCnt) {
      maxCnt = arr[i];
      result = i;
    }
  }

  cout << result;
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
} 