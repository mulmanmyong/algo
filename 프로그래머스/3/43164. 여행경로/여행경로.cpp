#include <string>
#include <vector>
#include <deque>
#include <algorithm>

using namespace std;

// 정답 저장
vector<string> answer;
// 경로
deque<string> path;
// 티켓 사용여부 (인덱스로)
vector<bool> used;

void dfs(string cur, vector<vector<string>>& tickets, int depth) {
    // 모든 항공권을 사용했다면 현재 경로가 정답
    if (depth == tickets.size()) {
        answer = vector<string>(path.begin(), path.end());
        return;
    }

    for (int i = 0; i < tickets.size(); i++) {
        // 아직 사용하지 않은 항공권이고, 현재 공항에서 출발한다면
        if (!used[i] && tickets[i][0] == cur) {
            // 해당 티켓 사용
            used[i] = true;
            // 경로에 추가
            path.push_back(tickets[i][1]);

            dfs(tickets[i][1], tickets, depth + 1);

            // 정답을 찾았다면 더 이상 탐색할 필요 없음
            if (!answer.empty()) {
                return;
            }

            // 백트래킹
            path.pop_back();
            used[i] = false;
        }
    }
}

vector<string> solution(vector<vector<string>> tickets) {
    // 주어진 항공권 모두 이용하여 여행경로 짤거임. 항상 ICN 공항에서 출발함
    // 방문하는 공항 경로를 탐색할거니깐 이어쓰기가 될 것이고, 경로 2개 이상이면 알파벳 순서 앞서는 것
    // 모든 도시 방문가능!
    // dfs를 이용하여 해당 뎁스 이어쓰기 가기. 백트래킹으로 가면 될 거 같은데?

    // 알파벳 순서대로 탐색하기 위해 정렬
    sort(tickets.begin(), tickets.end());

    // 초기화
    answer.clear();
    path.clear();
    used.resize(tickets.size(), false);

    // 시작 공항
    path.push_back("ICN");

    dfs("ICN", tickets, 0);

    return answer;
}
