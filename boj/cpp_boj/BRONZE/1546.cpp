// 1546. 평균 -> 브론즈 1

#include <bits/stdc++.h>

using namespace std;

int main(void) {
  ios::sync_with_stdio(0); cin.tie(0);
    
  int n;
  cin >> n;

  vector<int> vec;
  int mn = 0;
  for (int i = 0; i < n; i++) {
    int tmp;
    cin >> tmp;
    vec.push_back(tmp);
    mn = max(mn, tmp);
  }

  double sum = 0.0;
  for (int num : vec) {
    sum += ((double)num/mn*100);
  }
  cout << sum/n;

  return 0;
}