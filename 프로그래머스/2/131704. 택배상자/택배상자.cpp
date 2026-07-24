#include <string>
#include <vector>
#include <stack>

using namespace std;

int solution(vector<int> order) {
    // 메인 벨트는 1부터 순서대로 나옴
    // 원하는 상자가 아니면 보조 컨테이너 벨트에 넣음
    // 원하는 상자가 나오면 바로 실으면 됨
    // 상자를 하나 실을 때마다 스택의 맨 위도 주문과 같다면 계속 실을 수 있음

    // 가장 마지막부터 꺼낼 수 있으니깐 스택을 활용
    stack<int> st;

    int answer = 0;

    // 현재 확인해야 하는 주문의 위치
    int idx = 0;

    // 메인 벨트의 상자를 1번부터 마지막까지 확인
    for (int box = 1; box <= order.size(); box++) {

        // 현재 상자가 원하는 상자라면 바로 싣기
        if (box == order[idx]) {
            answer++;
            idx++;

            // 보조 컨테이너 벨트에서도 계속 실을 수 있는지 확인
            while (!st.empty() && idx < order.size()) {

                // 스택의 최상단이 원하는 상자가 아니면 종료
                if (st.top() != order[idx]) break;

                // 원하는 상자라면 꺼내기
                st.pop();
                answer++;
                idx++;
            }
        }
        // 원하는 상자가 아니라면 보조 컨테이너 벨트에 넣기
        else {
            st.push(box);
        }
    }

    // 메인 벨트가 끝난 뒤에도 보조 컨테이너 벨트에서 실을 수 있는지 확인
    while (!st.empty() && idx < order.size()) {

        // 원하는 상자가 아니면 더 이상 진행 불가
        if (st.top() != order[idx]) break;

        st.pop();
        answer++;
        idx++;
    }

    return answer;
}