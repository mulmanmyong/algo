#include <string>
#include <vector>
#include <algorithm>

using namespace std;

string convert_num(int num, int n) {
    // 0은 따로 처리
    if (num == 0) {
        return "0";
    }
    
    // 저장할 string
    string s = "";
    // n으로 계속 나누면서 나머지를 구함
    while (num > 0) {
        // 0~9는 그대로 문자로 변환
        if (num % n < 10) {
            s += (char)('0' + (num % n));
        }
        // 10~15는 A~F로 변환
        else {
            s += (char)('A' + (num % n) - 10);
        }
        
        // 숫자를 n으로 나눔
        num /= n;
    }
    
    // 나머지를 뒤에서부터 붙였기 때문에 뒤집어야 정상적인 n진수 문자열
    reverse(s.begin(), s.end());
    return s;
}

string solution(int n, int t, int m, int p) {
    // 제일 단순한 완탐으로 접근
    // t*m을 하는게 string으로 만들 최대 길이
    // 그중에서 내 순서 -1의 인덱스부터 m간격으로 담기
    
    int num = 0;
    // n진수로 변환을 해서 string을 만드는데 이때의 길이가 t*m 이상이면 종료
    string s = "";
    while (s.length() <= m*t) {
        // num을 n지수로 변환한 string으로 반환
        s += convert_num(num, n);
        num++;
    }
    
    // 정답을 담을 answer string
    string answer = "";
    // 시작 인덱스
    int idx = p - 1;
    // answer의 길이가 t가 될 때까지 반복 -> t이상이면 중단
    while (answer.length() < t) {
        answer += s[idx];
        // idx 사람 인원 수 만큼 점프
        idx += m;
    }
    
    return answer;
}