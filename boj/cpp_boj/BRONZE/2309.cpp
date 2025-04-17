// 2309. 일곱 난쟁이 -> 브론즈 1

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
    int arr[9] = {0, }, ans[7] = {0, }, total;
    for (int i = 0; i < 9; i++)    cin >> arr[i];

    // 2명을 제외한 경우의 수 만들기
    for (int i = 0; i < 8; i++) {
        for (int j = i+1; j < 9; j++) {
            total = 0;
            for (int k=0, r=0; k < 9; k++){
                if(k != i && k!= j) ans[r++] = arr[k];
            }
            for(int r = 0; r < 7; r++)  total += ans[r];

            if (total == 100)   break;
        }
        if (total == 100)   break;
    }

    sort(ans, ans+7);
    for (int i = 0; i < 7; i++) cout << ans[i] << '\n';
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}