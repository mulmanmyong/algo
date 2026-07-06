#include <string>
#include <vector>
#include <unordered_set>

using namespace std;

int solution(vector<int> elements) {
    // 길이가 1 2 ... elements.size() 까지 할 때 각각 합한 수들의 총 개수.
    // 중복없이 해야하니 hash set인 unordered_set 사용 순서 상관없으니깐..
    
    // 정답용 해시셋
    unordered_set<int> answer;
    
    // 그리고 원형이니깐 슬라이딩 윈도우 방식을 통해 구간합을 진행
    for (int len = 1; len <= elements.size(); len++) {
        int sum = 0;
        
        // 첫번째 구간
        for (int i = 0; i < len; i++) {
            sum += elements[i % elements.size()];
        }
        // 첫번째 구간의 합 삽입
        answer.insert(sum);
        
        // 이후 슬라이딩 윈도우 기법으로 슬라이딩 진행
        for (int st = 1; st < elements.size(); st++) {
            // 제일 앞에 값을 지우고
            sum -= elements[(st - 1) % elements.size()];
            // 다음 값을 더함
            sum += elements[(st + len - 1) % elements.size()];
            // 그리고 이 합을 삽입
            answer.insert(sum);
        }
    }
    
    // 해시셋의 크기 return
    return answer.size();
}