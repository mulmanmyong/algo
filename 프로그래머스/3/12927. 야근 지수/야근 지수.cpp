#include <string>
#include <vector>
#include <queue>

using namespace std;

long long solution(int n, vector<int> works) {
    // 야근 피로도는 남은 작업량들의 제곱합
    // N시간 동안 야근하여 피로도를 최소로 만들어야 함

    // 가장 큰 작업량을 반복해서 줄이는 것이 가장 효율적
    // 우선순위 큐를 이용한 최대 힙 사용
    priority_queue<int> pq;

    // n 시간안에 모든 작업을 끝낼 수 있는 지 판단
    long long sum = 0;
    // 전체 작업량 계산 및 우선순위 큐에 저장
    for (int work : works) {
        sum += work;
        pq.push(work);
    }

    // N시간 안에 모든 일을 끝낼 수 있으면 바로 0 반환
    if (sum <= n) {
        return 0;
    }

    // N시간 동안 가장 큰 작업량을 1씩 감소
    while (n--) {
        // 현재 가장 큰 작업량을 꺼냄
        int cur = pq.top();
        pq.pop();
        
        // 작업량을 1 감소 후 다시 넣음
        pq.push(cur-1);
    }

    long long answer = 0;

    // 남은 작업량들의 제곱합 계산
    while (!pq.empty()) {
        long long cur = pq.top();
        pq.pop();

        answer += cur * cur;
    }

    return answer;
}