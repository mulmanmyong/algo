// 2058. 자릿수 더하기

#include<iostream>

using namespace std;

int main(int argc, char** argv)
{
	int n;
  cin >> n;

  int ans=0;
  while (n > 0) {
    ans += (n % 10);
    n /= 10;
  }
  cout << ans;
	return 0;
}