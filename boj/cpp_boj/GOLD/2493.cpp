// 2493. 탑 -> 골드 5

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  int n;
  cin >> n;
  vector<int> height(n);
  vector<int> answer(n);
  stack<pair<int, int>> st; // (index, height)

  for (int i = 0; i < n; i++) {
    cin >> height[i];

    while (!st.empty() && st.top().second < height[i]) {
      st.pop(); // 자신보다 작은 탑은 수신 불가
    }

    if (!st.empty()) {
      answer[i] = st.top().first + 1; // 1-based index
    } else {
      answer[i] = 0;
    }

    st.push({i, height[i]});
  }

  for (int a : answer) cout << a << ' ';
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}