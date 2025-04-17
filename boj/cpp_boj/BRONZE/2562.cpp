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
    cout << *max_element(arr, arr+9) << "\n";  // 최대값을 가지는 이터레이터(포인터) 반환, 주소를 반환하는 것. 그래서 *를 통해 해당 주소의 값을 참조
    cout << max_element(arr, arr+9) - arr + 1; // max_element()로 최댓값의 위치주소를 return하고, 배열의 시작주소를 뺴줌으로써 최대값의 위치 파악가능. 1부터 시작이니 +1을 해줌
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}