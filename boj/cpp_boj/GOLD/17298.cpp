// 17298. 오큰수 -> 골드 4

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  int n;
  cin >> n;

  vector<int> vec(n);
  vector<int> ans;
  stack<int> st;
  for (int i = 0; i < n; i++) {
    cin >> vec[i];
  }

  for (int i = n-1; i >= 0; i--) {

    while (!st.empty() && st.top() <= vec[i]) {
      st.pop();
    }

    if (st.empty()) ans.push_back(-1);
    else  ans.push_back(st.top());
    st.push(vec[i]);
  }

  for (int i = n-1; i >= 0; i--) cout << ans[i] << ' ';
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}