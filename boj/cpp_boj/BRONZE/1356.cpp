// 1356. 유진수 -> 브론즈 1

#include <bits/stdc++.h>

using namespace std;

int main(void) {
    ios::sync_with_stdio(0); 
    cin.tie(0);

    string n;
    cin >> n;
    bool flag = false;
    for (int t = 0; t < n.length()-1; t++) {
      int a=1, b=1;

      for (int i = 0; i <= t; i++) {
        a *= (n[i]-'0');
      }

      for (int j = t+1; j < n.length(); j++) {
        b *= (n[j]-'0');
      }

      if (a == b) {
        flag = true;
        break;
      }
    }
    if (flag) cout << "YES";
    else  cout << "NO";

    return 0;
}