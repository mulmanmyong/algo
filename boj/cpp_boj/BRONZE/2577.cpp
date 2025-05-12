// 2577. 숫자의 개수 -> 브론즈 2

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
    int arr[10] = {0,};
    int A, B, C;
    cin >> A >> B >> C;
    int mul = A*B*C;
    while (mul > 0) {
        arr[mul%10]++;
        mul /= 10;
    }

    for (int n : arr)   cout << n << '\n';
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
} 