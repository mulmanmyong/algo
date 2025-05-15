// 18258. 큐 2 -> 실버 4

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  queue<int> q;
  int t;
  cin >> t;
  while (t--) {
    string cmd;
    cin >> cmd;

    if (cmd == "push") {
      int x; cin >> x; q.push(x);
    }
    else if (cmd == "pop")  {
      if (!q.empty()) {
        cout << q.front() << '\n'; q.pop();
      }
      else  cout << "-1\n";
    }
    else if (cmd == "front") {
      if (!q.empty()) cout << q.front() << '\n';
      else  cout << "-1\n";
    }
    else if (cmd == "back") {
      if(!q.empty())  cout << q.back() << '\n';
      else  cout << "-1\n";
    }
    else if (cmd == "empty") {
      cout << q.empty() << '\n';
    }
    else if (cmd == "size") {
      cout << q.size() << '\n';
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