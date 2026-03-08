#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(0); cin.tie(0);
    
    cout << fixed; // 부동 소수점 숫자 -> 고정 소수점 형식
    cout.precision(10); // 10자리 이하 자리수 설정
    
    double x, y;
    cin >> x >> y;
    cout << x / y;
    
    return 0;
}