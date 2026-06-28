#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    int answer = 0;
    // 슬라이딩 윈도우로 가능할듯?? 연속된 자연수들로 표현하는 방법의 수니깐..
    int left = 1;
    int right = 1;
    int sum = 1;
    
    while (left <= n) {
        // 합이 n이랑 같아지면
        if (sum == n) {
            answer++; // 결과 개수 증가
            sum -= left; // 제일 작은 수 제거
            left++; // 제일 작은 수 하나 증가
        }
        // 아직 작으면
        else if (sum < n) {
            right++; // 큰 수 증가
            sum += right; // 큰 수 더하기
        }
        // sum이 더 커져버렸다
        else {
            // 제일 작은 수 제거
            sum -= left;
            left++; // 작은 수 키우기
        }
    }
    
    return answer;
}