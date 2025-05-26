// 15649. N과 M (1) -> 실버 3

#include <bits/stdc++.h>

using namespace std;

int n;
int cnt = 0;
bool arr1[];
bool arr2[];
bool arr3[];

void back(int cur)  {
  if (cur == n) {
    cnt++;
    return;
  }
   

}

void func1(void) {
  // queen은 어느방향으로든 이동할 수 있음. 
  // 따라서 서로 공격을 안하려면 같은 행, 열, 대각선에 있으면 안됨
  
  cin >> n;
  back(0);
  cout << cnt;
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}