// 15552. 빠른 A+B -> 브론즈 4

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
    int t, a, b;
    cin >> t;
    for (int i = 0; i < t; i++) {
        cin >> a >> b;
        cout << a + b << "\n";
    }
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}