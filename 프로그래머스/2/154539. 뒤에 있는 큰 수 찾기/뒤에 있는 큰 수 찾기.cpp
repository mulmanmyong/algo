#include <string>
#include <vector>
#include <stack>

using namespace std;

vector<int> solution(vector<int> numbers) {
    // 완전 탐색으로 풀면 시간 초과가 날 것으로 보인다. (최악의 경우 O(N^2))
    // 스택을 활용해서 풀어보자
    // 뒤에 있는 큰 수를 찾는 문제이므로, answer는 우선 -1로 초기화
    vector<int> answer(numbers.size(), -1);

    // 스택 생성
    stack<int> st;
    
    // 스택에는 인덱스를 저장
    // 인덱스를 저장하면 중복된 숫자도 구분할 수 있고, answer에도 바로 값을 저장 가능
    
    for (int i = 0; i < numbers.size(); i++) {
        // 현재 값이 스택 top이 가리키는 값보다 크면,
        // 현재 값이 스택 top의 뒤에 있는 큰 수가 됨
        while (!st.empty() && numbers[st.top()] < numbers[i]) {
            answer[st.top()] = numbers[i]; // 뒤에 있는 큰 수 저장
            st.pop(); // 큰 수를 찾았으므로 제거
        }

        // 아직 뒤에 있는 큰 수를 찾지 못했으므로 현재 인덱스를 저장
        st.push(i);
    }

    return answer;
}