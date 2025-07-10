// 1463. 1로 만들기 -> 실버 3

#include <bits/stdc++.h>

using namespace std;

int dp[1000001];

void func1(void)
{
  int x;
  cin >> x;

  /*
  가능한 연산
  1. x가 3으로 나누어 떨어지면, 3으로 나눈다.
  2. x가 2로 나누어 떨어지면, 2로 나눈다.
  3. 1을 뺸다.

  이는 즉, 1이 초기값이니, 여기서 부터 끝까지 올라가면 됨
  1을 더했을 때랑 2로 나눴을 때, 1로 더했을 때랑 3으로 나웠을 때 2가지 경우를 계속 작은 수로 갱신해 나가면, 최소 연산이 도출됨
  */
  // dp로 bottom-up;
  dp[1] = 0; 

  for (int i = 2; i <= x; i++) {
    dp[i] = dp[i-1]+1; // 1을 빼는 경우 -> bottom-up이니 더해줌
    if (i % 2 == 0) dp[i] = min(dp[i], dp[i/2]+1); // 2로 나누어 떨어지는 경우, 1로 더해온거랑 2로 나누었을 때랑 연산횟수가 작은것
    if (i % 3 == 0) dp[i] = min(dp[i], dp[i/3]+1); // 3로 나누어 떨어지는 경우, 1로 더해온거랑 3로 나누었을 때랑 연산횟수가 작은것
  }

  cout << dp[x];
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}
