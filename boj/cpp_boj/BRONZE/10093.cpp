// 10093. 숫자 -> 브론즈 2

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
    long long a, b;
    cin >> a >> b;

    if (a > b)  swap(a, b);
    if (a == b || b - a == 1)   cout << 0; // 사이에 숫자가 존재 X
    else {
        cout << b-a-1 << '\n';
        for (long long i = a + 1; i < b; i++) cout << i << ' ';
    }
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}