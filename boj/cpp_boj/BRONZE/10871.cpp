// 10871. X보다 작은 수 -> 브론즈 5

#include <bits/stdc++.h>

using namespace std;

int main(void) {
    ios::sync_with_stdio(0); 
    cin.tie(0);

    int n, x, a;
    cin >> n >> x;
    for (int i = 0; i < n; i++) {
        cin >> a;
        if (a < x)  cout << a << " ";
    }

    return 0;
}