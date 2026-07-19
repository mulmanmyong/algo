#include <iostream>
#include <vector>
using namespace std;

int solution(vector<vector<int> > land)
{
    // 1행부터 내려오는데 같은 열 연속해서는 불가능함
    // 최대값을 return
    // 열의 개수는 4개
    // 그냥 dp사용하면 될 거 같음
    
    // 첫번째 행에서 다음 행으로 넘어갈때
    // 이전 행의 같은 열을 제외한 제일 큰 값을 더하는 형식으로 누적
    // 그럴때 이제 마지막 행에서 가장 큰 값이 answer

    int n = land.size();

    for (int i = 1; i < n; i++) {
        land[i][0] += max(land[i - 1][1], max(land[i - 1][2], land[i - 1][3]));
        land[i][1] += max(land[i - 1][0], max(land[i - 1][2], land[i - 1][3]));
        land[i][2] += max(land[i - 1][0], max(land[i - 1][1], land[i - 1][3]));
        land[i][3] += max(land[i - 1][0], max(land[i - 1][1], land[i - 1][2]));
    }

    int answer = max(max(land[n - 1][0], land[n - 1][1]), max(land[n - 1][2], land[n - 1][3]));

    return answer;
}