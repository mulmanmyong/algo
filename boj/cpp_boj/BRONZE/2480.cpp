// 2480. 주사위 세개 -> 브론즈 4

#include <bits/stdc++.h>

using namespace std;

// 기본 조건문을 이용한 것
void func1(void) {
    int a, b, c;
    cin >> a >> b >> c;

    if (a == b && b == c)   cout << a*1000 + 10000;
    else if (a == b || a == c)  cout << a*100 + 1000;
    else if (b == c)    cout << b*100 + 1000;
    else    cout << max(a, max(b, c))*100;
}

// 정렬을 이용하여 풀이한 것
void func2(void) {
    int a[3] = {0, };
    cin >> a[0] >> a[1] >> a[2];
    sort(a, a+3);

    if (a[0] == a[2])   cout << a[0]*1000 + 10000;
    else if (a[0] == a[1] || a[1] == a[2])  cout << a[1]*100 + 1000;
    else    cout << a[2]*100;
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}