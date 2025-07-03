// 1225. 이상한 곱셈 -> 브론즈 2

#include <bits/stdc++.h>

using namespace std;

void func1() {
  string a, b;
  cin >> a >> b;
  long long ans=0;  
  for (int i = 0; i < a.length(); i++) {
    for (int j = 0; j < b.length(); j++) {
      ans += ((a[i]-'0')*(b[j]-'0'));
    }
  }
  cout << ans;
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}