#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    // 규칙이 떠오르지 않아서 우선 그려봐야할 것
    // 직사각형은 가로 길이가 2이고, 세로 길이가 2임
    // n = 1은 1가지
    // n = 2는 2가지
    // n = 3은 3가지
    // n = 4는 5가지
    // n = 5는 8가지
    
    // 이로 봤을 때 dp느낌으로 점화식으로 바텀업을 하면 될 둣 함
    vector<int> dp(n+1);
    // 초기 값
    dp[1] = 1;
    dp[2] = 2;
    
    // 바텀업 진행
    for (int i = 3; i <= n; i++) {
        // 1,000,000,007를 나눈 나머지..
        dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
    }
    
    int answer = dp[n];
    return answer;
}