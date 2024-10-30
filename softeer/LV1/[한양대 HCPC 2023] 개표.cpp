// 주어진 문제를 올바르게 해결 가능한가?
// 조건문과 반복문을 사용하는 단순한 구현
// 소요시간 : 10분
#include<iostream>

using namespace std;

int main(int argc, char** argv)
{
    int T, n;
    cin >> T;

    for (int i = 0; i < T; i++) {
        cin >> n;

        // 5개 짜리 출력
        while (n >= 5) {
            cout << "++++";
            n -= 5;
            if (n != 0) {
                cout << " ";
            }
        }

        for (int i = 0; i < n; i++) {
            cout << "|";
        }
        cout << endl;
    }
    
    return 0;
}