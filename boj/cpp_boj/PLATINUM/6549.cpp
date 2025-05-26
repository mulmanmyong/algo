// 6549. 히스토그램에서 가장 큰 직사각형 -> 플래티넘 5

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  while (true) {
    int n, idx;
    long long h, ans=0;
    stack<pair<long long, long long>> st; // 높이와 위치 h idx
    cin >> n;
    if (n==0) break;
    for (int i = 0; i < n; i++) {
      cin >> h;
      idx = i;
      while (!st.empty() && st.top().first >= h) {
        ans = max(ans, (i-st.top().second)*st.top().first);
        // 현재까지의 인덱스-top의 인덱스가 길이, 현재의 top이 높이, 이를 통해 top에 따른 너비 계산 가능
        idx = st.top().second; // idx길이 누적, 0번 인덱스에서 1이 나오고 더 높은 수만 나오다가 4번 인덱스에서 1이 나오면, 아직 1은 누적계산 가능하지, 0으로 최신화
        st.pop();
      }
      st.push({h, idx});
    }
    while (!st.empty()) { // 끝까지 도달 했을 때 남아있는 놈의 너비까지 계산
      ans = max(ans, (n-st.top().second)*st.top().first);
      st.pop();
    }

    cout << ans << '\n';
  }
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}