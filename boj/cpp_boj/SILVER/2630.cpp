// 2630. 색종이 만들기 -> 실버 2

#include <bits/stdc++.h>

using namespace std;

int n;
int arr[129][129];
int cnt[2];

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
    cnt[arr[a][b]] += 1;
    return;
  }

  int nn = n / 2;
  for (int i = 0; i < 2; i++) {
    for (int j = 0; j < 2; j++) {
      paper(a+nn*i, b+nn*j, nn);
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
  for (int num : cnt) cout << num << '\n';
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}
