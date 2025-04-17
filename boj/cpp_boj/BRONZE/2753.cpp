// 2753. 윤년 -> 브론즈 5

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
    int year;
    cin >> year;

    if (year%4==0 && (year%100!=0 || year%400==0))  cout << 1;
    else    cout << 0;
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}