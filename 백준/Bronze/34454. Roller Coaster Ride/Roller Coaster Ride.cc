#include <iostream>

using namespace std;

int main() {
    int N, C, P;
    cin >> N >> C >> P;

    if (N <= C * P)
        cout << "yes";
    else
        cout << "no";

    return 0;
}