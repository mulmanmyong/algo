// 10828. 스택 -> 실버 4

#include <iostream>
#include <stack>
#include <string>

using namespace std;

int main() {

    int n;
    string cmd;
    cin >> n;

    stack<int> s;
    int a;
    for (int i = 0; i < n; i++) {
        cin >> cmd;

        if (cmd == "push") {
            cin >> a;
            s.push(a);
        }
        else if (cmd == "pop") {
            if (s.empty()) {
                cout << -1 << endl;
            }
            else {
                cout << s.top() << endl;
                s.pop();
            }
        }
        else if (cmd == "size") {
            cout << s.size() << endl;
        }
        else if (cmd == "top") {
            if (s.empty()) {
                cout << -1 << endl;
            }
            else {
                cout << s.top() << endl;
            }
        }
        else if (cmd == "empty") {
            if (s.empty()) {
                cout << 1 << endl;
            }
            else {
                cout << 0 << endl;
            }
        }
    }

    return 0;
}