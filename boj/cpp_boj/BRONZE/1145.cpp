// 1145. 적어도 대부분의 배수 -> 브론즈 1

#include <bits/stdc++.h>

using namespace std;

void func1() {
  int arr[5];
  int min_num=-1;
  for (int i = 0; i < 5; i++) {
    cin >> arr[i];
    if (min_num==-1 || min_num > arr[i])  min_num=arr[i];
  }

  int cnt = 0, ans=min_num-1;
  while (cnt < 3) {
    ans++;
    cnt=0;
    for (int i = 0; i < 5; i++) {
      if (ans % arr[i] == 0)  cnt++;
    }
  }

  cout << ans << '\n';
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}