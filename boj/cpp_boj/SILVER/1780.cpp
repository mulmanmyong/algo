// 1780. 종이의 개수 -> 실버 2

#include <bits/stdc++.h>

using namespace std;

int n;
int arr[2188][2188];
int cnt[3]; // -1 0 1

bool check(int a, int b, int n) {
  for (int i = a; i < a+n; i++) {
    for (int j = b; j < b+n; j++) {
      if (arr[a][b] != arr[i][j]) return false;
    }
  }
  return true;
}

void paper(int a, int b, int n) {
  if (check(a, b, n)) {
    cnt[arr[a][b] + 1] += 1;
    return;
  }
  int tmp = n / 3;
  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
      paper(a+tmp*i, b+tmp*j, tmp);
    }
  }
}

void func1(void) {
  cin >> n;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> arr[i][j];
    }
  }
  paper(0, 0, n);

  for (int ans : cnt) cout << ans << '\n';
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}