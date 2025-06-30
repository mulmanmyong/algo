// 1173. 운동 -> 브론즈 2

#include <bits/stdc++.h>

using namespace std;

void func1() {
  int N, m, M, T, R;
  cin >> N >> m >> M >> T >> R;

  int cnt = 0, doit = 0;
  int cur = m;

  if (m+T > M) {
    cout << -1 << '\n';
    return;
  }
  while (doit < N) {
    if (cur+T <= M) {
      cur += T;
      doit++;
    }
    else {
      cur = max(m, cur-R);
    }
    cnt++;
  }
  
  cout << cnt << '\n';
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}