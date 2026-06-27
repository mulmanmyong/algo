#include <string>
#include <vector>

using namespace std;

// 대문자 변환 함수
char toUpper(char c) {
    if ('a' <= c && c <= 'z') {
        return c - ('a' - 'A');
    }
    return c;
}

// 소문자 변환 함수
char toLower(char c) {
    if ('A' <= c && c <= 'Z') {
        return c + ('a' - 'A');
    }
    return c;
}

string solution(string s) {
    bool isFirst = true;
    
    for (int i = 0; i < s.length(); i++) {
        if (s[i] == ' ') {
            isFirst = true;
        }
        else {
            if (isFirst) {
                s[i] = toUpper(s[i]);
                isFirst = false;
            }
            else {
                s[i] = toLower(s[i]);
            }
        }
    }
    
    return s;
}