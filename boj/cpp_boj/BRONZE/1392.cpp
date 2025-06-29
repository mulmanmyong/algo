// 1392. 노래 악보 -> 브론즈 2

#include <bits/stdc++.h>

using namespace std;

int arr[10000];

void func1() {
  int n, q;
  cin >> n >> q;
  int idx=0;
  for (int i = 1; i <= n; i++) {
    int tmp;
    cin >> tmp;
    
    while (tmp--) {
      arr[idx++] = i;
    }
  }

  while (q--) {
    int tmp;
    cin >> tmp;
    cout << arr[tmp] << '\n';
  }
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}