// 15649. N과 M (1) -> 실버 3

#include <bits/stdc++.h>

using namespace std;

int n, m;
int arr[9];
bool used[9];

void back(int cnt)  {
  if (cnt == m) {
    for (int i = 0; i < m; i++) {
      cout << arr[i] << ' ';
    }
    cout << '\n';
    return;
  }

  for (int i = 1; i <= n; i++) {
    if (!used[i]) { // 사용안된 숫자면
      arr[cnt] = i;
      used[i] = true;
      back(cnt+1); // 재귀, 백트래킹 사용된경우의 상태로 들어감
      used[i] = false; // 원복
    }
  }
}

void func1(void) {
  cin >> n >> m;
  back(0); // 초기에는 카운트 없으므로
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}