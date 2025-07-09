// 1512. 주기문으로 바꾸기 -> 골드 5

#include <bits/stdc++.h>

using namespace std;

void func1(void)
{
  int m;
  cin >> m;
  string s;
  cin >> s;

  int ans = 3001;
  for (int p = 1; p <= m; p++)  // 주기는 m보다 작거나 같은 주기문, 즉, m까지의 주기를 모두 파악
  {
    int cur = 0; // 현재 주기에서의 최소 변경 개수 담을 변수
    for (int i = 0; i < p; i++)
    {
      string tmp = "";
      for (int j = i; j < s.length(); j += p)
      {
        tmp += s[j]; // 주기 만큼 입력
      }

      if (tmp.length() == 0)  continue; // 아무것도 없으면 굳이 계산 필요 x

      int min_chk = 3001; // 현재 주기의 문자열에서 가장 적게 바꿀 개수를 확인
      for (int t = 0; t < tmp.length(); t++)
      {
        int chk = 0; 
        for (int y = 0; y < tmp.length(); y++)
        {
          if (tmp[t] != tmp[y])
          {
            chk++;
          }
        }
        min_chk = min(chk, min_chk); // 현재 주기문에서 가장 적게 변경하는 경우를 계산
      }
      cur += min_chk;
    }
    ans = min(ans, cur); // 주기 중에서 제일 작은 결과값을 사용
  }
  cout << ans;
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}
