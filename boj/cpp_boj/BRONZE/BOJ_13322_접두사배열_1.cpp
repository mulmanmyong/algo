#include <iostream>

using namespace std;

// 알파벳 소문자로 이루어진 문자열 S가 주어짐 
string s;

void input() {
    cin >> s;
}

int main() {
    ios::sync_with_stdio(0); cin.tie(0);
    
    // 입력을 받기 
    input();
    // 입력 완료

    // 예시는 접미사였지만
    // 우리는 접두사.
    // 즉 banana 였으면
    // b, ba, ban, bana, banan, banana 이런식으로 뒤에 더 붙는 식으로 증가
    // 그렇다는 것은 이미 정렬되어있는 상태가 되는 것일 거고
    // 길이만큼 반복문을 돌며 인덱스를 출력하면 될 것
    for (int i = 0; i < s.length(); i++)  cout << i << '\n';
    
    return 0;
}