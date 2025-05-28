// 1992. 쿼드트리 -> 실버 1

#include <bits/stdc++.h>

using namespace std;

int n;
int arr[65][65];

bool check(int a, int b, int n) {
  for (int i = a; i < a+n; i++) {
    for (int j = b; j < b+n; j++) {
      if (arr[a][b] != arr[i][j]) return false;
    }
  }
  return true;
}

void quadtree(int a, int b, int n) {
  if (n==1) {
    cout << arr[a][b];
    return;
  }
  if (check(a, b, n)) {
    if (arr[a][b] == 0) {
      cout << 0;
      return;
    }
    else if (arr[a][b] == 1) {
      cout << 1;
      return;
    }
  }
  else {
    cout << '(';
    quadtree(a, b, n/2); // 왼쪽위
    quadtree(a, b+n/2, n/2); // 오른쪽위
    quadtree(a+n/2, b, n/2); // 왼쪽아래
    quadtree(a+n/2, b+n/2, n/2); // 오른쪽아래
    cout << ')';
  }
}

void func1(void) {
  cin >> n;
  for (int i = 0; i < n ; i++) {
    string s;
    cin >> s;
    for (int j = 0; j < n; j++) {
      arr[i][j] = s[j]-'0';
    }
  }
  quadtree(0, 0, n);
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}
