// 1236. 성 지키기 -> 브론즈 1

#include <bits/stdc++.h>

using namespace std;

char board[51][51];

void func1() {
  int n, m;
  cin >> n >> m;
  
  for (int i = 0; i < n; i++) {
    string s;
    cin >> s;
    for (int j = 0; j < m; j++) {
      board[i][j] = s[j];
    }
  }
  
  int cnt1=0;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (board[i][j] == 'X') {
        cnt1++;
        break;
      }
    }
  }

  int cnt2=0;
  for (int j = 0; j < m; j++) {
    for (int i = 0; i < n; i++) {
      if (board[i][j] == 'X') {
        cnt2++;
        break;
      }
    }
  }

  cout << max(n-cnt1, m-cnt2);
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}