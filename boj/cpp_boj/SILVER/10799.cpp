// 10799. 쇠막대기 -> 실버 2

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  string s;
  cin >> s;
  int ans=0;
  stack<char> st;
  char pre;
  for (char c : s) {
    if (c == '(') {
      st.push(c);
      pre = '(';
    }
    else if (c == ')') {
      st.pop();
      if (pre == '(') ans += st.size(); // 레이저로 절단함. 스택에 남아있는 개수가 절단하는 파이프개수
      else  ans++; // 파이프의 끝을 의미함. 절단안하더라도 해당 파이프는 끊기게 되므로 개수를 하나 추가함
      pre = ')';
    }
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