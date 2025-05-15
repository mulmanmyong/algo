// 10866. 덱 -> 실버 4

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  int t;
  deque<int> dq;
  cin >> t;
  while (t--) {
    string cmd;
    cin >> cmd;

    if (cmd == "push_front") {
      int x;
      cin >> x;
      dq.push_front(x);
    }
    else if (cmd == "push_back") {
      int x;
      cin >> x;
      dq.push_back(x);
    }
    else if (cmd == "pop_front") {
      if (dq.empty()) cout << "-1\n";
      else {cout << dq.front() << "\n"; dq.pop_front();}
    }
    else if (cmd == "pop_back") {
      if (dq.empty()) cout << "-1\n";
      else {cout << dq.back() << "\n"; dq.pop_back();}
    }
    else if (cmd == "size") {
      cout << dq.size() << '\n';
    }
    else if (cmd == "empty") {
      cout << dq.empty() << '\n';
    }
    else if (cmd == "front") {
      if (dq.empty()) cout << "-1\n";
      else {cout << dq.front() << "\n";}
    }
    else if (cmd == "back") {
      if (dq.empty()) cout << "-1\n";
      else {cout << dq.back() << "\n";}
    }
  }
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}