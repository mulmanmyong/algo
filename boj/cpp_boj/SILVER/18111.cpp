// 18111. 마인크래프트 -> 실버 2

#include <bits/stdc++.h>

using namespace std;

int ground[501][501] = {0};

void func1() {
  int n, m, b;
  cin >> n >> m >> b;

  int maxh=-1, minh=257;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      cin >> ground[i][j];
      maxh = max(maxh, ground[i][j]);
      minh = min(minh, ground[i][j]);
    }
  }

  // 현재 블록의 높이가 최소에서 최대까지 중에서 제일 빠른 시간으로
  // 근데 시간이 같다면 높은 놈으로
  // 최대 반복이 64000000이라 1억이 안넘으니깐 브루트포스 방식 될 지도?
  int anst=99999999, ansh;
  for (int h = minh; h <= maxh; h++) {
    int bb = b, curt=0;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (ground[i][j] > h) {
          curt += ((ground[i][j]-h)*2);
          bb += (ground[i][j]-h);

        }
        else if (ground[i][j] < h) {
          curt += ((h-ground[i][j]));
          bb -= (h-ground[i][j]);

        }
      }
    }

    // 모든 작업을 했을 때 블록의 개수가 음수만 안되면 될 듯? 다른 곳 먼저 캘 수 있으니깐
    if (0 <= bb) {
      if (curt < anst || (curt == anst && ansh < h)) {
        anst = curt;
        ansh = h;
      }
    }
  }
  cout << anst << ' ' << ansh << '\n';
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}