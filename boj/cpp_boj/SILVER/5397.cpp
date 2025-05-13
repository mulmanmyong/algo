// 5397. 키로거 -> 실버 2

#include <bits/stdc++.h>

using namespace std;

void func1(void)
{
  int t;
  cin >> t;

  while (t--)
  {
    string s;
    list<char> l;
    cin >> s;
    auto cur = l.end();
    for (char c : s)
    {
      // 조건들은 분리해야함. 동시 판단을 하면, 우선시 되어야하는 조건이 동작이 안되기 때문. 이것은 내가 실수를 해서 수정을 함
      if (c == '<')
      {
        if (cur != l.begin()) cur--; 
      }
      else if (c == '>')
      {
        if (cur != l.end()) cur++;
      }
      else if (c == '-')
      {
        if (cur != l.begin())
        {
          cur--;
          cur = l.erase(cur);
        }
      }
      else
      {
        l.insert(cur, c);
      }
    }

    for (char c : l)  cout << c;
    cout << '\n';
  }
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}