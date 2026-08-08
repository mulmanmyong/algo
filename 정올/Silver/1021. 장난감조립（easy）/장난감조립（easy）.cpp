#include <iostream>
#include <vector>

using namespace std;

// 제조식 저장 그래프 (동일한 부품 만들어지는 경우가 2가지 이상일 수도 있으니 2차원으로)
vector<vector<pair<int, int>>> graph;
// 필요한 기본 부품들의 개수 저장할 배열
vector<int> answer;

void dfs(int part, int count) {
    // 기본부품이냐?
    // 제조식이 없음 -> 제조식이 입력된 것이 없다 empty여부 판단
    if (graph[part].empty()) {
        // 비어있으면 기본 부품
        answer[part] += count; // 개수 누적
        return;
    }

    // 제조식이 있으면 기본 부품이 아님
    // 현재 부품이 어떻게 만들어졌는지 판단
    for (int i = 0; i < graph[part].size(); i++) {
        // X Y K 순이니깐 Y가 다음 부품, K가 필요한 개수
        int nextPart = graph[part][i].first;
        int needCount = graph[part][i].second;

        // 다음 part에 필요한 부품의 개수 누적해서 dfs
        // 현재 부품 하나 만드는데 nextPart가 needCount개 필요한거니깐?
        // 현재 부품의 count개수를 nextPart로 만들라면
        // count * needCount만큼 nextPart가 필요
        dfs(nextPart, count * needCount);
    }
}

int main() {
    // 기본 부품이 있다
    // 기본 부품들로 중간 부품을 만들 수 있음
    // 부품 제조식 X Y K 형태인데
    // X라는 부품을 Y부품 K개로 만들 수 있다는 뜻
    // 즉, X에 나오지 않은 것들이 기본 부품

    int N, M;
    cin >> N >> M;

    // 부품 번호를 그대로 인덱스로 사용하기 위해
    // 0번부터 N - 1번까지 사용할 수 있도록 N 크기로 만든다.
    graph.resize(N + 1);
    answer.resize(N);

    for (int i = 0; i < M; i++) {
        // 제조식
        int X, Y, K;
        cin >> X >> Y >> K;

        // X를 만들기 위해 Y가 K개 필요하다는 제조식을 저장
        graph[X].push_back({Y, K});
    }

    // 완제품 N부터 시작해서 기본 부품이 나올 때까지 DFS 진행
    // 파츠 번호, 개수
    dfs(N, 1);

    // 필요한 기본 부품의 개수를 순회
    for (int i = 1; i < N; i++) {
        // 필요한 개수가 0이면 출력하지 않는다.
        if (answer[i] == 0) {
            continue;
        }

        // 기본부품번호 개수 출력
        cout << i << ' ' << answer[i] << '\n';
    }

    return 0;
}