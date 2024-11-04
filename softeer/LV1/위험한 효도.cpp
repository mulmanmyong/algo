// 빡구현
// 소요시간 : 15분
// 그냥 멍청해서 오래걸림
#include<iostream>

using namespace std;

int main(int argc, char** argv)
{

    int a, b, d;
    cin >> a >> b >> d;

    int forward = d, backward = d, time = 0;
    bool go = true;
    // 앞으로
    while (forward > 0) {
        if (go && b <= forward) {
            time += b;
            forward -= b;
            go = false;
            // cout << "forward" << endl;
        }
        else if (go && b > forward) {
            time += forward;
            forward = 0;
            // cout << "last_forward" << endl;
            // go = true; // true 유지 -> 바로 교대해서 움직이니
        }
        else if (!go) {
            time += a;
            go = true;
            // cout << "rest" << endl;
        }
    }

    // 되돌아가기
    while (backward > 0) {
        if (go && a <= backward) {
            time += a;
            backward -= a;
            go = false;
            // cout << "backward" << endl;
        }
        else if (go && a > backward) {
            time += backward;
            backward = 0;
            // cout << "last_backward" << endl;
        }
        else if (!go) {
            time += b;
            go = true;
            // cout << "rest" << endl;
        }
    }

    cout << time << endl;

   return 0;
}