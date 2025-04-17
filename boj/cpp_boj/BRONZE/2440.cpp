// 2440. 별 찍기 - 3 -> 브론즈 4

#include <bits/stdc++.h>

using namespace std;

void func1() {
    int n;
    cin >> n;
    for (int i = n; i >= 1; i--) {
        for (int j = 1; j <= i; j++)     cout << '*';
        cout << "\n";
    }
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}