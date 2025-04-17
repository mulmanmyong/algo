// 2446. 별 찍기 - 9 -> 브론즈 3

#include <bits/stdc++.h>

using namespace std;

void func1() {
    int n;
    cin >> n;
    // 상단 별
    for (int i = n; i >= 1; i--) {
        for (int j = 1; j <= n-i; j++)       cout << ' ';
        for (int j = 1; j <= 2*i-1; j++)     cout << '*';
        cout << "\n";
    }
    // 하단 별
    for (int i = 2; i <= n; i++) {
        for (int j = 1; j <= n-i; j++)       cout << ' ';
        for (int j = 1; j <= 2*i-1; j++)     cout << '*';
        cout << "\n";
    }
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}