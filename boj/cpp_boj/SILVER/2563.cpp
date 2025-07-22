// 2563. 색종이 -> 실버 5
#include <iostream>

using namespace std;

int main()
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >> n;

  // 각 시작지점부터 +10까지 이중포문 하며 더해줌.
  // 이때 이미 방문한 곳은 0이 아니기 때문에 continue할 수 있도록 함
  int paper[101][101] = {0};
  int ans = 0;
  while (n--)
  {
    int a, b;
    cin >> a >> b;
    for (int i = a; i < a + 10; i++)
    {
      for (int j = b; j < b + 10; j++)
      {
        if (paper[i][j])
          continue;
        ans++;
        paper[i][j]++;
      }
    }
  }
  cout << ans;
  return 0;
}
