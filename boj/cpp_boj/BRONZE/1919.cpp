// 1919. 에너그램 만들기 -> 브론즈 2

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  string s1, s2;
  cin >> s1 >> s2;
  int arr[2][26] = {0, };
  for (char c : s1) arr[0][c-'a']++;
  for (char c : s2) arr[1][c-'a']++;

  int ans=0;
  for (int i = 0; i < 26; i++) ans += abs(arr[0][i] - arr[1][i]);
  cout << ans;
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
} 