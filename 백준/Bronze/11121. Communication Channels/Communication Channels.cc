#include <iostream>
#include <string>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int T;
    cin >> T;

    while (T--) {
        string a, b;
        cin >> a >> b;

        if (a == b) {
            cout << "OK\n";
        } else {
            cout << "ERROR\n";
        }
    }

    return 0;
}