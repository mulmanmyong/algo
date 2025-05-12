// 11328. Strfry -> 브론즈 2

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  int n;
  string s1, s2;
  cin >> n;

  for (int i = 0; i < n; i++) {
    cin >> s1 >> s2;
    sort(s1.begin(), s1.end()); sort(s2.begin(), s2.end()); 
    if (s1 == s2) cout << "Possible\n";
    else  cout << "Impossible\n";
  }
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
} 