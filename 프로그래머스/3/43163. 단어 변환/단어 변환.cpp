#include <string>
#include <vector>

using namespace std;

int answer = 987654321;
bool visited[50]; // 변환에 사용한 단어인지 확인

// 한글자만 다른지 확인
bool checkDiffrentOne(string current, string target) {
    int count = 0;

    // 반복하며 한글자만 다른지 확인 (모든 단어의 길이는 동일함)
    for (int i = 0; i < current.size(); i++) {
        // 글자가 다르니 count증가
        if (current[i] != target[i]) {
            count++;
        }
    }
    
    // count가 1이면 한글자만 다르니 true, 그 외에는 false
    return count == 1;
}

void dfs(string current, string target, vector<string>& words, int changeCount) {
    // 종료 조건 - 현재 글자와 target이 같으면 변환완료
    if (current == target) {
        // 최소 값으로 갱신
        answer = min(answer, changeCount);
        return;
    }
    
    // 백트래킹 - 각 글자를 서로 비교
    for (int i = 0; i < words.size(); i++) {
        // 사용한 단어가 아니고, 한글자만 다르면
        if (!visited[i] && checkDiffrentOne(current, words[i])) {
            // 변환에 사용
            visited[i] = true;
            dfs(words[i], target, words, changeCount + 1);
            visited[i] = false;
        }
    }
}

int solution(string begin, string target, vector<string> words) {
    // 백트래킹을 이용해서 한글자만 다른 경우에 변환을 하면 될 듯
    // 한글자씩만 변환해서 begin에서 target으로 만드는 것
    // 근데 target이 words에 없으면 변환을 못함
    bool empty = true;
    for (string word : words) {
        // 단어가 있으면 존재함
        if (word == target) {
            empty = false;
            break;
        }
    }
    
    // empty = true면 존재하지 않음 0반환
    if (empty) {
        return 0;
    }
    
    // 그렇지 않다면 백트래킹 진행
    dfs(begin, target, words, 0);
    
    return answer;
}