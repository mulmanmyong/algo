#include <bits/stdc++.h>
using namespace std;
int main()
{
  ios::sync_with_stdio(0); cin.tie(0);

  int n, m;
  cin >> n >> m;
  vector<int> vec(n);

  int i, j, k;
  for (int t = 1; t <= m; ++t) {
    cin >> i >> j >> k;
    for (int a = i-1; a < j; a++) {
      vec[a] = k;
    }
  }

  for (int a = 0; a < n; a++) {
    cout << vec[a] << ' ';
  }

  return 0;
}