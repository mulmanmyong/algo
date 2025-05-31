// 14956. Philosopher's Walk -> 골드 2

#include <bits/stdc++.h>

using namespace std;

pair<int, int> philo(int side, int walk) {
  if (side == 2) {
    if (walk == 1) return {1, 1};
    else if (walk == 2) return {1, 2};
    else if (walk == 3) return {2, 2};
    else if (walk == 4) return {2, 1};
  }

  int half = side / 2;
  int part = half * half;
  if (walk <= part) {
    pair<int, int> rp = philo(half, walk);
    return {rp.second, rp.first};
  }
  else if (walk <= 2*part) {
    pair<int, int> rp = philo(half, walk-part);
    return {rp.first, rp.second+half};
  }
  else if (walk <= 3*part) {
    pair<int, int> rp = philo(half, walk-2*part);
    return {rp.first+half, rp.second+half};
  }
  else {
    pair<int, int> rp = philo(half, walk-3*part);
    return {2*half-rp.second+1, half-rp.first+1};
  }
}

void func1(void) {
  int n, m;
  cin >> n >> m;
  pair<int, int> ans = philo(n, m);
  cout << ans.first << ' ' << ans.second << '\n';
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}
