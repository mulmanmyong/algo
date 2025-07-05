// 1247. 부호 -> 브론즈 3

#include <bits/stdc++.h>

using namespace std;

void func1() {
  for (int t = 0; t < 3; t++) {
    int n;
    long long sum=0, flow=0;
    cin >> n;
    while (n--) {
      long long s;
      cin >> s;
      long long temp = sum; // 더하기 전
      sum += s; // 더한 후

      if (s > 0 && temp > 0 && sum < 0) flow++; // overflow : 입력값이 양수, 더하기 전 양수, 근데 더하고 음수되면 overflow
      if (s < 0 && temp < 0 && sum > 0) flow--; // underflow : 입력값이 음수, 더하기 전 음수, 근데 더하고 양수되면 underflow
      // 그 외에는 long long 범위 안에 있음
    }

    if (flow == 0) {
      if (sum == 0) cout << "0\n";
      else cout << (sum > 0 ? "+\n" : "-\n");
    }
    else {
      cout << (flow > 0 ? "+\n" : "-\n");
    }
  }
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}