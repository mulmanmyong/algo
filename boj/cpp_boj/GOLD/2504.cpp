// 2504. 괄호의 값 -> 골드 5

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  string s;
  cin >> s;
  stack<char> st;
  int ans=0;
  int cal = 1;
  char pre;

  for (char c : s) {
    if (c == '(') {
      cal *= 2; // 안에 있는 내용물은 모두 분배법칙이 적용됨
      // 따라서 (가 입력되면 2를 곱해줌
      st.push(c);
    }
    else if (c == '[') {
      cal *= 3; // 안에 있는 내용물은 모두 분배법칙이 적용됨
      // 따라서 [가 입력되면 3을 곱해줌
      st.push(c);
    }
    else if (c == ')') {
      if (st.empty() || st.top() != '(') {
        cout << 0; // 올바르지 않은 괄호
        return;
      }
      else if (pre == '(')  ans += cal; // 바로 직전에 열린 소괄호면 더해줌, 더할 타이밍인 것
      st.pop();
      cal /= 2; // 소괄호 제거됐으므로, 소괄호에 해당하는 2를 나눠줌
    }
    else if (c == ']') {
      if (st.empty() || st.top() != '[') {
        cout << 0; // 올바르지 않은 괄호
        return;
      }
      else if (pre == '[')  ans += cal; // 바로 직전에 열린 중괄호면 더해줌, 더할 타이밍인 것
      st.pop();
      cal /= 3; // 중괄호 제거됐으므로, 중괄호에 해당하는 3을 나눠줌
    }
    pre = c;
  }
  if (st.empty()) cout << ans;
  else  cout << 0; // 마지막 입력으로 ()((( 같은 것이 나오면, (((만 남아 있어 올바른 괄호가 아니지만 해당 처리구문 없으면 2가 출력됨
}


int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}