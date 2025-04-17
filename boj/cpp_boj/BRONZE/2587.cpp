// 2587. 대표값2 -> 브론즈 2

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
    int a[5] = {0, }, total=0;

    for (int i = 0; i < 5; i++)     cin >> a[i];
    for (int i = 0; i < 5; i++)     total += a[i];
    cout << total / 5 << "\n";

    sort(a, a+5);
    cout << a[2];
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
} 