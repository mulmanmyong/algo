// 1212. 8진수 2진수 -> 브론즈 2

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  string oct;
  cin >> oct;
  string bin[8] = {"000", "001", "010", "011", "100", "101", "110", "111"};
  
  for (int i = 0; i < oct.length(); i++) {
    if (i==0) cout << stoi(bin[oct[i]-'0']);
    else  cout << bin[oct[i]-'0'];
  }

}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
} 