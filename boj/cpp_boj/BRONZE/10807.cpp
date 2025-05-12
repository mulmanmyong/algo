// 10807. 개수 세기 -> 브론즈 5

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  int N, v, ans=0;
  vector<int> vec;
  cin >> N;
  for (int i = 0; i < N; i++) {
    int tmp;
    cin >> tmp;
    vec.push_back(tmp);
  }
  cin >> v;

  for(int n : vec) {
    if (n == v) ans++;
  }

  cout << ans;
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
} 