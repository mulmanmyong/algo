#include <string>
#include <vector>
#include <algorithm>
#include <set>
#include <cctype>

using namespace std;

vector<int> solution(string s) {
    // string에 {}로 튜플을 구성하는 원소가 중복 없이 나와있음
    // 이를 먼저 2차원 배열로 구성되어있기에 2차원 배열로 파싱을 진행
    
    // string을 순회하면서 {이거면 여는 것, }이거면 닫는 것으로 인지 , 이거는 숫자 구분자, 그리고 숫자만 나옴
    vector<vector<int>> vec;
    vector<int> cur; // 현재 배열
    int num = 0;

    for (int i = 0; i < s.size(); i++) {
        char c = s[i];

        // 숫자냐?
        if (isdigit(c)) {
            num = num * 10 + (c - '0');
        }
        // 숫자가 아니다
        else {
            // 숫자를 모두 읽은 경우 현재 배열에 추가
            if ((c == ',' || c == '}') && i > 0 && isdigit(s[i - 1])) {
                cur.push_back(num);
                num = 0;
            }

            // 집합 하나가 끝난 경우
            if (c == '}' && !cur.empty()) {
                vec.push_back(cur);
                cur.clear();
            }
        }
    }
    
    // 튜플의 정해진 순서를 보면, 자주나오는 순서대로 제일 앞에 있는 것을 알 수 있다.
    // 따라서 크기가 작은 배열부터 확인하며 처음 나오는 숫자들을 추가하면 된다

    // 집합의 크기 순으로 정렬
    sort(vec.begin(), vec.end(),
         [](const vector<int>& a, const vector<int>& b) {
             return a.size() < b.size();
         });

    vector<int> answer;
    set<int> visited;

    // 처음 등장하는 숫자만 정답에 추가
    for (auto &v : vec) {
        for (int x : v) {
            if (!visited.count(x)) {
                answer.push_back(x);
                visited.insert(x);
            }
        }
    }

    return answer;
}