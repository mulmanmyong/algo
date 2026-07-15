#include <string>
#include <vector>

using namespace std;

// 결과값
int answer = 0;

void dfs(vector<int> numbers, int target, int current, int depth) {
    // 종료 조건
    if (depth == numbers.size()) {
        // 이 때 current랑 target같으면 answer증가
        if (current == target) {
            answer++;
        }
        // 그 외에는 되돌아가기
        return;
    }
    
    // 조건 분기
    // 더하거나
    dfs(numbers, target, current + numbers[depth], depth + 1);
    // 빼거나
    dfs(numbers, target, current - numbers[depth], depth + 1);
}

int solution(vector<int> numbers, int target) {
    // dfs를 통해서 제일 깊은 깊이까지 갔을 때의 target이랑 같은 지 확인하기
    // 매개변수는 numbers랑 target은 고정적으로 가져가고, 현재까지 더하거나 뺀값, 그리고 깊이를 넘겨주면됨
    dfs(numbers, target, 0, 0);
    return answer;
}