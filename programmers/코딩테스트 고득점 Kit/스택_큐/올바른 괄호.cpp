#include <string>
#include <stack>
#include <iostream>

using namespace std;

bool solution(string s)
{
    bool answer = true;

    // stack을 사용해야함. LIFO 방식을 사용
    // (가 나오면 push를 하고, )가 나오면 pop을 하는 형식
    // )가 처음에 나오거나, (가 마지막에 나오면 바로 false 반환, 홀수여도 바로 false 반환
    // 스택에 넣고 뺴는 과정에서 스택에 남은 것이 앞으로의 과정보다 뒤에 과정 진행안하고 바로 false 반환
    stack<char> st; // string을 쪼개서 하나씩 char만 넣으니깐 char로 선언

    // 처음에 ')'가 오거나, 마지막에 '('가 오는 경우 바로 false 반환
    if ((s[0] == ')') || (s[s.size() - 1] == '(') || (s.size() % 2 != 0)) {
        return false;
    }

    // 문자열을 순회하면서 괄호 처리
    for (int i = 0; i < s.size(); i++) {
        if (st.size() > s.size() - i) {
            return false; // 현재 스택에 있는 수가 남은 것보다 많으면 빠른 false 반환
        }
        if (s[i] == '(') {
            st.push(s[i]); // 여는 괄호는 스택에 추가
        }
        else if (s[i] == ')') {
            if (st.empty()) {
                return false; // 닫는 괄호가 있을 때 여는 괄호가 없으면 불균형
            }
            st.pop(); // 여는 괄호가 있으면 닫는 괄호를 처리
        }
    }

    // 스택이 비어 있으면 모든 괄호가 잘 맞춰짐
    if (st.empty()) {
        return true;
    } else {
        return false; // 스택에 남아 있는 괄호가 있으면 불균형
    }
}
