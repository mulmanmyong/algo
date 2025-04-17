// 2752. 세수정렬 -> 브론즈 4

#include <bits/stdc++.h>

using namespace std;

// 기초적인 방안 -> 코드가 김
void func1(void) {
    int a, b, c;
    cin >> a >> b >> c;

    if (a < b && b < c)
        cout << a << ' ' << b << ' ' << c;
    else if (a < c && c < b)
        cout << a << ' ' << c << ' ' << b;
    else if (b < a && a < c)
        cout << b << ' ' << a << ' ' << c;
    else if (b < c && c < a)
        cout << b << ' ' << c << ' ' << a;
    else if (c < a && a < b)
        cout << c << ' ' << a << ' ' << b;
    else
        cout << c << ' ' << b << ' ' << a;
}

void func2(void) {
    int a, b, c;
    cin >> a >> b >> c;

    int max_num, mid_num, min_num;
    max_num = max(a, max(b, c));
    min_num = min(a, min(b, c));
    mid_num = a + b + c - max_num - min_num;

    cout << min_num << ' ' << mid_num << ' ' << max_num;
}

// 다른 방법도 있는 듯함
void func3(void) {
    
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);

    // func1();
    func2();

    return 0;
}



