// 2576. 홀수 -> 브론즈 3

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
    int a[7] = {0, }, min_num=101, sum_num=0;
    for (int i = 0; i < 7; i++) {
        cin >> a[i];
        if (a[i] % 2 != 0) {
            sum_num += a[i];
            min_num = min(min_num, a[i]);
        }
    }
    if (sum_num > 0)    cout << sum_num << "\n" << min_num;
    else    cout << -1;

}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}