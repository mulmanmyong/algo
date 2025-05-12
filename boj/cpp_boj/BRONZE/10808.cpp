// 10808. 알파벳 개수 -> 브론즈 4

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
    string s;
    cin >> s;
    int arr[26] = {0, };
    for (char a : s) arr[a - 'a']++;    
    for (int n : arr) cout << n << " ";
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
} 