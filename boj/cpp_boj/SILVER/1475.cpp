// 1475. 방 번호 -> 실버 5

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
    int room, ans = 0;
    int arr[10] = {0, };
    cin >> room;

    while (room > 0) {
      arr[room%10]++;
      room /= 10;
    }

    for (int i = 0; i < 10; i++) {
      if (i == 6 || i == 9)   continue;
      ans = max(ans, arr[i]);
    }
    ans = max(ans, (arr[6]+arr[9]+1) / 2);
    cout << ans;
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
} 