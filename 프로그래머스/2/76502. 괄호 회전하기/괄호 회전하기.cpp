#include <string>
#include <vector>
#include <stack>

using namespace std;

int solution(string s) {
    // 모두 다 회전을 시도를 문자열의 길이가 6이면, 회전0회 ~ 5회까지 했을 때의 올바른 문자열인지 판단
    // 그리고 올바른 괄호 문자열이된 이후에 회전을 하면 그 규칙이 깨지기 때문에 다음에는 자동으로 올바른 괄호 문자열이 아니게 될 것.
    int answer = 0;

    // 시작 인덱스 설정
    for (int startIndex = 0; startIndex < s.length(); startIndex++) {
        int idx = startIndex;
        stack<char> st;
        bool valid = true;

        // 문자열 순회
        for (int i = 0; i < s.length(); i++) {
            char cur = s[idx % s.length()];

            // 여는 괄호면 push
            if (cur == '(' || cur == '{' || cur == '[') {
                st.push(cur);
            }
            // 닫는 괄호면 검사
            else {
                // 닫는 괄호인데 비어있으면 유효하지 않음
                if (st.empty()) {
                    valid = false;
                    break;
                }

                // 그렇지 않으면 비교
                if (st.top() == '(' && cur == ')') {
                    st.pop();
                }
                else if (st.top() == '{' && cur == '}') {
                    st.pop();
                }
                else if (st.top() == '[' && cur == ']') {
                    st.pop();
                }
                // pop할 수 없으면 매칭 없는 것 유효하지 않음
                else {
                    valid = false;
                    break;
                }
            }

            idx++;
        }

        // 스택 비교 완료
        // 올바른 괄호인것
        if (valid && st.empty()) {
            answer++;
        }
    }

    return answer;
}