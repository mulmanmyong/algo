// 5014. 스타트링크 -> 실버 1

#include <bits/stdc++.h>

using namespace std;

int arr[1000002];

void func1(void) {
  int f, s, g, u, d;
  cin >> f >> s >> g >> u >> d;
  int cnt = 0;
  fill(arr+1, arr+f+1, -1);
  
  queue<int> q;
  arr[s] = 0;
  q.push(s);
  while (!q.empty()) {
    int cur = q.front();
    q.pop();
    
    for (int nxt : {cur+u, cur-d}) {
      if (nxt > f || nxt <= 0 || arr[nxt] != -1)  continue;
      arr[nxt] = arr[cur] + 1;
      q.push(nxt);
    }
  }

  if (arr[g] == -1) cout << "use the stairs";
  else  cout << arr[g];
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}
