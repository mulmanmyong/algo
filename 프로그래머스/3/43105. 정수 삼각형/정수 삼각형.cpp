#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> triangle) {
    // 1 2 3 4 이런식으로 숫자의 개수가 생기고
    // 1->2는
    // 1에서 0 이었으면
    // 2에서 0 또는 1로 갈 수 있다 => 이렇게 더해지기
    // 2->3은
    // 0,1 1,2 이렇게 갈 수 있다
    
    // 즉 바텀업을 하면서 triangle의 사이즈만큼 미리 dp배열 만들어주고
    // 최종 도착 인덱스에 따라 최대값 갱신하게 하면 될듯
    
    // dp[j] : 현재 행의 j 위치까지 왔을 때의 최대 합
    vector<int> dp(triangle.size(), 0);
    dp[0] = triangle[0][0];
    
    for (int i = 1; i < triangle.size(); i++) {
        // 이전 행의 값을 사용해야 하므로 오른쪽 -> 왼쪽으로 갱신
        // 왼쪽부터 갱신하면 dp[j-1]이 현재 행의 값으로 바뀌어 이전 행의 값을 사용할 수 없게 됨
        for (int j = i; j >= 0; j--) {
            // 가장 오른쪽은 왼쪽 위에서만 올 수 있다.
            if (j == i) {
                dp[j] = dp[j - 1] + triangle[i][j];
            }
            // 가장 왼쪽은 바로 위에서만 올 수 있다.
            else if (j == 0) {
                dp[j] += triangle[i][j];
            }
            // 나머지는 왼쪽 위, 바로 위 중 큰 값을 선택
            else {
                dp[j] = max(dp[j - 1], dp[j]) + triangle[i][j];
            }
        }
    }

    // 최대값 찾기
    int answer = 0;
    for (int i = 0; i < dp.size(); i++) {
        answer = max(answer, dp[i]);
    }

    return answer;
}