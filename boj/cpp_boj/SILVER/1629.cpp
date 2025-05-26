// 1629. 곱셈 -> 실버 1

#include <bits/stdc++.h>

using namespace std;

long long mod(long long a, long long b, long long c) {
  if (b == 1) return a % c;
  long long n = mod(a, b/2, c);
  n = n * n % c;
  if (b%2 == 0) return n;
  return n*a %c;
}

void func1(void) {
  long long a, b, c;
  cin >> a >> b >> c;
  // a^b % c
  cout << mod(a, b, c);
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}