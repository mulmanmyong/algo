#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(int x, int y, int n) {
    // 사용 가능한 연산을 이용해서 계산을 하고
    // 연산 카운트를 올리는 형식으로 진행하기
    // 제일 먼저 40에 도달하면 return
    
    // bfs로 queue를 사용하기
    // 현재의 수, 연산 횟수
    queue<pair<int, int>> q;
    q.push({x, 0});
    
    // 이미 동일한 값이 나왔었으면 또 그 연산 진행하지 않게 calculated 추가
    vector<bool> calculated(y + 1, false);
    calculated[x] = true;
    
    // 빌때까지 진행, 중간에 끝나지 않으면 -1 
    int answer = -1;
    while(!q.empty()) {
        int currentNum = q.front().first;
        int currentCnt = q.front().second;
        q.pop();
        
        // 중간 종료 조건
        if (currentNum == y) {
            answer = currentCnt;
            break;
        }
        
        // n 더하는 연산 -> 더했을 때 y 초과안할때만
        if (currentNum + n <= y && !calculated[currentNum + n]) {
            calculated[currentNum + n] = true;
            q.push({currentNum + n, currentCnt + 1});
        }
        
        // 2 곱하는 연산 -> 곱했을 때 y 초과안할때만
        if (currentNum * 2 <= y && !calculated[currentNum * 2]) {
            calculated[currentNum * 2] = true;
            q.push({currentNum * 2, currentCnt + 1});
        }
        
        // 3 곱하는 연산 -> 곱했을 때 y 초과안할때만
        if (currentNum * 3 <= y && !calculated[currentNum * 3]) {
            calculated[currentNum * 3] = true;
            q.push({currentNum * 3, currentCnt + 1});
        }
    }
    return answer;
}