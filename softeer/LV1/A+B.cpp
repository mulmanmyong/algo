// + 연산자를 사용할 줄 아는가?
// 소요시간 : 1분
#include<iostream>

using namespace std;

int main(int argc, char** argv)
{
    int A, B, T;

    cin >> T;
    for (int i = 1; i <= T; i++) {
        cin >> A >> B;

        cout << "Case #" << i << ": " << A+B << endl; 
    }
    
   return 0;
}