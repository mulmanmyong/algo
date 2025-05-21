// 2015. 오아시스 재결합 -> 플래티넘 5

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  int n;
  cin >> n;

  stack<pair<long long, long long>> st;
  long long h, ans=0;
  for (int i = 0; i < n; i++) {
    cin >> h;
    long long cnt = 1; // 현재 높이 h의 사람 수를 1로 시작 (자기 자신)

    while (!st.empty() && st.top().first <= h) {
      ans += st.top().second; // 스택 top에 있는 사람들은 현재 h를 볼 수 있음
      if (st.top().first == h) {
        cnt += st.top().second; // 만약 스택 top의 높이가 현재 h와 같다면, 그 수를 현재 h의 카운트에 더함
      }
      st.pop(); // top보다 작으면 이후에 들어오는 수랑은 마주볼 수 없으므로 pop
    }
    if (!st.empty()) {
      ans++;
      // 스택이 비어있지 않다면, 스택의 top은 현재 h보다 큰 사람임
      // 이 경우 현재 h는 스택 top의 사람을 볼 수 있음
    }
    st.push({h, cnt}); // 현재 h와 누적된 카운트를 스택에 푸시
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