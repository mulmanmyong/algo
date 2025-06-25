// 1152. 단어의 개수 -> 브론즈 2

#include <bits/stdc++.h>

using namespace std;

void func1() {
    string s;
    getline(cin, s);
    int cnt = 0;
    stringstream ss(s);
    string word;
    while (ss >> word) {
        cnt++;
    }
    cout << cnt << '\n';
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}