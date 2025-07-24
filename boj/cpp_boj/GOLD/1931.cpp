// 1931. 회의실 배정 -> 골드 5

#include <bits/stdc++.h>

using namespace std;

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >> n;
  
  vector<pair<int,int>> vec;
  for (int i = 0; i < n; i++) {
    int a, b;
    cin >> b >> a;
    vec.push_back({a, b}); // 끝나는 시간을 기준으로 정렬할 수 있도록
  }
  sort(vec.begin(), vec.end()); // 끝나는 시간을 기준으로 정렬

  // n까지 순회하며, 시간 체크,
  int cnt = 1; 
  int endTime = vec[0].first;
  for (int i = 1; i < n; i++) {
    if (vec[i].second >= endTime) {
      cnt++;
      endTime = vec[i].first;
    }
  }

  cout << cnt;

  return 0;
}