//  10804. 카드 역배치 -> 브론즈 2

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
    int arr[21] = {0, };
    for (int i = 1; i <= 20; i++)   arr[i] = i; 

    int a, b;
    for (int t = 0; t < 10; t++) {
        cin >> a >> b;
        for (int i = 0; i < (b-a+1) / 2; i++)   swap(arr[a+i], arr[b-i]);
    }

    for (int i = 1; i <= 20; i++)   cout << arr[i] << ' ';
}

// swap을 이용한 교환형식 외에 reverse라는 STL이 존재
void func2(void) {
    int arr[21] = {0, };
    for (int i = 1; i <= 20; i++)   arr[i] = i; 

    int a, b;
    for (int t = 0; t < 10; t++) {
        cin >> a >> b;
        reverse(arr+a, arr+b+1);
    }

    for (int i = 1; i <= 20; i++)   cout << arr[i] << ' ';
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}