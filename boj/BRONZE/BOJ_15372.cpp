#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(0); cin.tie(0);
    
    int t;
    cin >> t;
    
    long long n; // int*int는 int로 출력되기에 200,000 * 200,000하면 int 오버플로우 발생 => long long 사용
    while (t--) {
        cin >> n;
        cout << n * n << '\n';    
    }
    
    return 0;
}