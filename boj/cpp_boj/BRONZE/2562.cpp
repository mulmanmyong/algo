// 2562. 최댓값 -> 브론즈 3

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
    int n, max_num = 0, max_idx= 0;

    for (int i = 1; i <= 9; i++) {
        cin >> n;
        if (max_num < n) {
            max_num = n;
            max_idx = i;
        }
    }

    cout << max_num << "\n" << max_idx;
}

// STL을 이용한 방안도 존재
void func2(void) {
    int arr[9] = {0, };
    for (int i = 0; i < 9; i++) cin >> arr[i];
    cout << *max_element(arr, arr+9) << "\n"; // 최대값 요소 주소값을 반환?
    cout << max_element(arr, arr+9) - arr + 1;
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}