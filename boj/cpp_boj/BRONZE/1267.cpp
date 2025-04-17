// 핸드폰 요금 -> 브론즈 3

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
    int n, a, y = 0, m = 0;
    cin >> n;

    for (int i = 0; i < n; i++) {
        cin >> a;
        y += ((a / 30)+1) * 10;
        m += ((a / 60)+1) * 15;
    }

    if (y < m)      cout << "Y " << y;
    else if (y > m)     cout << "M " << m;
    else    cout << "Y M " << y;

}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}