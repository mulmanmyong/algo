// 3273. 두 수의 합 -> 실버 3

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
    int n, x, ans=0;
    vector<int> vec;

    cin >> n;
    for (int i = 0; i < n; i++) {
      int tmp;
      cin >> tmp;
      vec.push_back(tmp);
    }
    cin >> x;
    
    // O(N^2)으로 하면 시간 초과남. 그리고 x-vec[i]의 범위가 vec의 크기를 넘어서지 않는 선에서 동작해야함. (outofrange발생했었음)
    vector<int> chk_vec(10000001, 0);
    for (int i = 0; i < n; i++) {
      if (x-vec[i] >= 0 && x-vec[i] < chk_vec.size() && chk_vec[x-vec[i]] == 1)  ans++;
      chk_vec[vec[i]] = 1;
    }

    cout << ans;
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
} 