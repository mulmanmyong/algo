// 4949. 균형잡힌 세상 -> 실버 4

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  while (true) {
    string in;
    getline(cin, in);
    if (in == ".")  break;
    stack<char> st;
    bool isValid = true;
    for (char c : in) {
      if (c == '(' || c == '[') {
        st.push(c);
      }
      else if (c == ')') {
        if (st.empty() || st.top() != '(') {
          isValid = false;
          break;
        }
        st.pop();
      }
      else if (c == ']') {
        if (st.empty() || st.top() != '[') {
          isValid = false;
          break;
        }
        st.pop();
      }
    }
    if (!st.empty())  isValid = false;

    if (isValid)  cout << "yes\n";
    else  cout << "no\n";
  }
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}