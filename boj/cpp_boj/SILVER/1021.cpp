// 1021. 회전하는 큐 -> 실버 3

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  int n, m;
  cin >> n >> m;
  
  deque<int> dq;
  for (int i = 1; i <= n; i++) {
    dq.push_back(i);
  }
  
  int ans=0;
  while (m--) {
    int target;
    cin >> target;
    int idx = find(dq.begin(), dq.end(), target) - dq.begin(); 
    // find로 찾고자 하는 것의 위치를 알 수 있음. 
    // 그 위치가 전체를 기준으로 앞쪽과 가까운 지 뒤쪽과 가까운지 판단하여 동작을 수행.
    while (dq.front() != target) {
      if (idx < dq.size() - idx) {
        dq.push_back(dq.front());
        dq.pop_front();
      }
      else {
        dq.push_front(dq.back());
        dq.pop_back();
      }
      ans++;
    }
    dq.pop_front(); 
  }

  cout << ans;
}


int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}