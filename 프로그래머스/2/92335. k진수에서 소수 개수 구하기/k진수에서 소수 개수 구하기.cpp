#include <string>
#include <vector>
#include <sstream>
#include <algorithm>

using namespace std;

// 소수 판별 함수
bool isPrime(long long num) {
    // 2부터 소수임
    if (num < 2) {
        return false;
    }
    
    for (long long i = 2; i * i <= num; i++) {
        // 나눠 떨어지면 소수가 아님
        if (num % i == 0) {
            return false;
        }
    }
    
    return true;
}

int solution(int n, int k) {
    // n을 k진수로 바꾸고
    // 0을 기준으로 끊어가며 소수인지 판별
    
    // n을 k진수로 변환하기
    string converted = "";
    while (n > 0) {
        converted += to_string(n % k);
        n /= k;
    }
    
    // 1의 자리부터 만들었으니 뒤집기
    reverse(converted.begin(), converted.end());

    // 0을 기준으로 끊어서 소수 개수 세기
    int answer = 0;
    stringstream ss(converted);
    string p;

    // 0을 기준으로 끊어가기
    while (getline(ss, p, '0')) {
        // 0이 연속으로 나온 경우 예외 처리
        if (p.empty()) {
            continue;
        }
        
        // 숫자가 크므로 long long 변환
        long long num = stoll(p);
        // 소수 판별
        if (isPrime(num)) {
            answer++;
        }
    }

    return answer;
}