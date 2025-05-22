// 1679. 숨바꼭질 -> 실버 1

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  // 10 9 18 17
  // +1 -1 *2
  int n, k;
  cin >> n >> k;
  int arr[100001];
  fill(arr, arr+100001, -1);
  arr[n] = 0;
  queue<int> q;
  q.push(n);
  while(arr[k] == -1) { // -1로 초기화, bfs 도중 먼저 도달하는 놈이 제일 빠른 놈
    int cur = q.front();
    q.pop();

    for (int nxt : {cur-1, cur+1, 2*cur}) {
      if (nxt < 0 || nxt > 100000)  continue;
      if (arr[nxt] != -1) continue; // 이미 갔던 곳은 안가게
      arr[nxt] = arr[cur]+1;
      q.push(nxt);
    }
  }
  cout << arr[k];
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}