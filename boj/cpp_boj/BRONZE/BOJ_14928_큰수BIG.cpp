#include <iostream>
#include <string.h>

using namespace std;

int main() {
    ios::sync_with_stdio(0); cin.tie(0);
    
    string str;
    cin >> str;

    int ans = 0;
    for (int i = 0; i < str.size(); i++) {
        ans = (ans*10 + (str[i]-'0')) % 20000303;
    }

    cout << ans;

    return 0;
}