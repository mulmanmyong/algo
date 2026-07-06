#include <string>
#include <vector>
#include <unordered_set>
#include <numeric>

using namespace std;

int solution(vector<int> elements) {
    // 길이가 1 2 ... elements.size() 까지 할 때 각각 합한 수들의 총 개수.
    // 중복없이 해야하니 hash set인 unordered_set 사용 순서 상관없으니깐..
    
    // 정답용 해시셋
    unordered_set<int> answer;
    // 연속 부분 수열의 합을 구하기 위한 길이 설정
    for (int len = 1; len <= elements.size(); len++) {
        // 모두가 첫 시작 위치이었을 경우를 확인
        for (int st = 0; st < elements.size(); st++) {
            vector<int> vec;
            // 시작위치부터 len까지 vec에 담음 (부분수열)
            for (int i = st; i < st + len; i++) {
                vec.push_back(elements[i % elements.size()]);
            }
            // 부분수열의 합을 정답 해시셋(중복 제거)에 삽입
            answer.insert(accumulate(vec.begin(), vec.end(), 0));
        }
    }
    
    // 해시셋의 크기 return
    return answer.size();
}