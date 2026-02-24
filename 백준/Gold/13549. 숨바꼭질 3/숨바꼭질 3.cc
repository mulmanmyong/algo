#include <iostream>
#include <queue>

using namespace std;

// 0 ~ 100000까지의 범위를 가짐 그럼 최대 100001을 가지면 됨
const int MAX = 100001;
// 해당 위치에서의 시간 저장
vector<int> vec(MAX, 987654321);
// 수빈이가 있는 위치 N, 동생이 있는 위치 K
int N, K;
 
// 입력받기
void init() {
    cin >> N >> K;
}

int findTarget() {
    // 다익스트라를 위한 우선순위 큐 -> 기본적으로 내림차순, 시간 빠른 순이기 때문에 오름차순을 해야함 (greater를 사용)
    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

    // 초기 값 설정
    vec[N] = 0;
    // 시간 위치 
    pq.push({0, N});

    // 찾을 때 까지 탐색 진행
    while (!pq.empty()) {
        int time = pq.top().first;
        int X = pq.top().second;
        pq.pop();

        // 도달했으면 바로 리턴
        if (X == K)   return time;
        // 이미 최소 시간으로 방문했었으면 패스
        if (vec[X] < time)    continue;

        // 그렇지 않다면 동작 진행

        // 순간이동 (0초)
        // 순간이동을 했을 때 범위안에 있고, 현재 시간이 최소 시간일때만 가능
        if (2*X < MAX && vec[2*X] > time) {
            vec[2*X] = time;
            pq.push({time, 2*X});
        }

        // 걷기 (1초)
        // -1을 했을 때 범위 안에 있과, 이동한 시간이 최소 시간일 때만 가능
        if (0 <= X-1 && vec[X-1] > time+1) {
              vec[X-1] = time+1;
              pq.push({time+1, X-1});
        }
        // +1을 했을 때 범위 안에 있과, 이동한 시간이 최소 시간일 때만 가능
        if (X+1 < MAX && vec[X+1] > time+1) {
              vec[X+1] = time+1;
              pq.push({time+1, X+1});
        }
    }

    // -1 뜨면 실패로직..?
    return -1;
}


int main() {
    ios::sync_with_stdio(0); cin.tie(0);

    /*
    수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동
    순간이동을 하는 경우에는 0초 후에 2*X의 위치로 이동
    가장 빠른 시간이 몇 초 후인지 구하는 프로그램 작성 

    순간이동은 0초가 걸리고, 그렇지 않은 것은 1초가 걸린다..
    제일 빠른 시간을 구해야하기 때문에 다익스트라를 이용해서 시간이 빠른 것은 앞에 넣을 수 있도록 구현해보기
    */
    
    // 입력받기
    init();

    // 수빈이가 동생을 찾는 가장 빠른 시간 출력 
    int ans = findTarget();

    cout << ans;

    return 0;
}