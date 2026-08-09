#include <string>
#include <vector>

using namespace std;

int solution(int m, int n, vector<vector<int>> puddles) {
    // 집이 있는 좌표 (1, 1) 학교 좌표 (m, n) = (col, row)
    // 물이 잠긴 지역의 좌표는 puddles -> 이 또한 (col, row)
    // 오른쪽과 아래쪽으로만 움직여서 학교까지 갈 수 있는 최단 경로의 개수를 1000000007로 나눈 나머지 return
    
    // dp를 이용해서 각 위치까지 이동할 수 있는 경로의 개수를 누적하면서 이동
    vector<vector<int>> dp(n + 1, vector<int>(m + 1, 0));

    // 물에 잠긴 지역은 -1로 표시
    for (vector<int> puddle : puddles) {
        int col = puddle[0];
        int row = puddle[1];
        dp[row][col] = -1;
    }
    
    // 시작 위치는 1
    dp[1][1] = 1;
    
    // 이동 방향이 오른쪽과 아래쪽이기 때문에
    // (1, 1)부터 row와 col을 증가시키면서 진행
    for (int row = 1; row <= n; row++) {
        for (int col = 1; col <= m; col++) {
            // 집(1, 1)은 이미 1로 설정했으니 계산 안 해도 되니 패스
            if (row == 1 && col == 1) {
                continue;
            }
            
            // 물웅덩이 = -1은 이동 못하니 패스
            if (dp[row][col] == -1) {
                continue;
            }
            
            // row 이동
            // row 이동은 row가 2부터 가능, 그리고 이동 전 좌표가 물웅덩이가 아니어야 현 위치로 올 수 있음
            if (row > 1 && dp[row - 1][col] != -1) {
                dp[row][col] += dp[row - 1][col];
            }
            
            // col 이동
            // col 이동은 col이 2부터 가능, 그리고 이동 전 좌표가 물웅덩이가 아니어야 현 위치로 올 수 있음
            if (col > 1 && dp[row][col - 1] != -1) {
                dp[row][col] += dp[row][col - 1];
            }
            
            dp[row][col] %= 1000000007;
        }
    }
    
    int answer = dp[n][m];
    return answer;
}