// 4108. 지뢰찾기 - 실버 5
#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  char board[101][101];
  while (true) {
    int r, c;
    cin >> r >> c;

    if (r == 0 && c == 0) break;
    for (int i = 0; i < r; i++) {
      string s;
      cin >> s;
      for (int j = 0; j < c; j++) {
        board[i][j] = s[j];
      }
    }

    // 8방향 전부 지뢰가 있는 것을 확인하기
    int dr[8] = {-1, 1, 0, 0, -1, 1, -1, 1};
    int dc[8] = {0, 0, -1, 1, -1, 1, 1, -1};
    int cnt;

    // 완전탐색
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        if (board[i][j] == '*') continue;

        cnt = 0;  // 지뢰의 수 cnt
        for (int d = 0; d < 8; d++) {
          int nr = i + dr[d];
          int nc = j + dc[d];

          // 인덱스 범위를 넘어갈 경우
          if (nr < 0 || nr >= r || nc < 0 || nc >= c) continue;
          if (board[nr][nc] == '*') cnt++;
        }

        // 주변 지뢰 탐색 후 '.' 대신 지뢰의 수 입력
        board[i][j] = cnt + '0';  // int to char
      }
    }

    // 출력
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        cout << board[i][j];
      }
      cout << '\n';
    }
  }

  return 0;
}
