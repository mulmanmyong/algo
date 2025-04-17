// 2490. 윷놀이 -> 브론즈 3

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
    int a, cnt = 0;
    for (int i = 0 ; i < 4; i++) {
        cin >> a;
        if (a == 0) {
            cnt++;
        }
    }

    if (cnt == 1)   cout << "A\n";
    else if (cnt == 2)  cout << "B\n";
    else if (cnt == 3)  cout << "C\n";
    else if (cnt == 4)  cout << "D\n";
    else    cout << "E\n";
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    for (int i = 0; i < 3; i++) func1();
    return 0;
}