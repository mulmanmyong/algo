// 1158. 요세푸스 문제 -> 실버 4

#include <bits/stdc++.h>

using namespace std;

void func1(void)
{
  // queue를 이용한 풀이
  int n, k;
  cin >> n >> k;

  queue<int> q;
  for (int i = 1; i <= n; i++)  q.push(i);
  cout << '<';
  while (!q.empty()) {
    for (int i = 0; i < k-1; i++) {
      q.push(q.front());
      q.pop(); // queue는 FIFO
    }
    cout << q.front();
    q.pop();
    if(q.size())  cout << ", "; // 마지막꺼 pop하면 q.size()는 0이 되므로 마지막 , 출력안되게
  }
  cout << '>';
}

void func2(void) 
{
  int n, k, len=0, mx=5001;
  int pre[mx];
  int nxt[mx];
  vector<int> vec;

  cin >> n >> k;
  for (int i = 1; i <= n; ++i) {
    pre[i] = (i==1) ? n : i-1;
    nxt[i] = (i==n) ? 1 : i+1;
    ++len;
  }

  int i=1;
  for (int cur=1; len!=0; cur=nxt[cur]) {
    if(i==k) {
      // 현재의 이전 = 현재의 다음의 이전
      // 현재의 다음 = 현재의 이전의 다음
      pre[nxt[cur]] = pre[cur];
      nxt[pre[cur]] = nxt[cur];
      vec.push_back(cur);
      i=1;
      --len;
    }
    else  ++i;
  }

  cout << '<';
  for (i=0; i < vec.size(); ++i) {
    cout << vec[i]; 
    if (i != vec.size()-1)  cout << ", ";
  }
  cout << '>';

}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func2();
  return 0;
}