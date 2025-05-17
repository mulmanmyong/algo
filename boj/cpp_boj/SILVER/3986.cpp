// 3986. 좋은 단어 -> 실버 4

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  int n, ans=0;
  cin >> n;
  while (n--) {
    string in;
    cin >> in;
    stack<char> st;
    bool isValid = true;
    for (char c : in) {
      if (!st.empty() && c == 'A' && st.top() == 'A')  st.pop();
      else if (!st.empty() && c == 'B' && st.top() == 'B')  st.pop();
      else st.push(c);   
    }
    if (!st.empty())  isValid = false;
    if(isValid) ans++;
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