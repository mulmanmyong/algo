#include <string>
#include <vector>
#include <algorithm>

using namespace std;

long long solution(long long n) {
    long long answer = 0;
    
    // 각 자리수 vector로 넣음
    vector<int> nArr;
    while (n > 0) {
        nArr.push_back(n%10);
        n /= 10;
    }
    
    // 정렬 오름 차순으로 
    sort(nArr.begin(), nArr.end(), greater<int>());
    
    // 제일 낮은 자리수부터 더하면서 숫자 생성
    for (int digit : nArr) {
        answer = answer * 10 + digit;
    }
    
    return answer;
}