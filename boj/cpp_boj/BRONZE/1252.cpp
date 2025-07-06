// 1252. 이진수 덧셈 -> 브론즈 2

#include <bits/stdc++.h>

using namespace std;

void func1() {
  string n1, n2;
  cin >> n1 >> n2;

  while (n1.length() != n2.length()) {
    if (n1.length() > n2.length()) {
      n2 = '0' + n2;
    }
    else if (n1.length() < n2.length()) {
      n1 = '0' + n1;
    }
  }
  
  int carry = 0;
  string ans = "";
  for (int i = n1.length()-1; i >= 0; i--) {
    int tmp = (n1[i]-'0') + (n2[i]-'0') + carry;
    ans = (tmp%2==0 ? '0' : '1') + ans;
    if (tmp >= 2) carry = 1;
    else  carry = 0;
  }

  if (carry == 1) ans = '1' + ans;
  int idx = 0;
  for (int i = 0; i < ans.length(); i++) {
    if (ans[i] == '1') break;
    idx++; // 0 인덱스가 아닐때까지
  }

  if (idx == ans.length())  cout << '0';
  else {
    for (int i = idx; i < ans.length(); i++) { // 1인것부터 출력
      cout << ans[i];
    }
  }
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}