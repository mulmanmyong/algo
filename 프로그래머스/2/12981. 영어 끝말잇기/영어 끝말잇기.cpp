#include <string>
#include <vector>
#include <iostream>
#include <map>

using namespace std;

vector<int> solution(int n, vector<string> words) {
    
    // map을 string int로 활용하면 좋을 듯 함
    map<string, int> mp;
    // 제일 첫 글자 넣고 증가
    mp[words[0]]++;

    // 이제 글자들을 비교하며 끝말잇기 진행
    // i % n +1이 몇번사람인지 그리고, i / n +1이 몇번사람의 몇번 차례인지를 알 수 있음
    for (int i = 1; i < words.size(); i++) {
        // 종료 조건은 이미 나왔던 단어거나 이전글자의 끝이 이번 글자의 앞과 다르면 종료
        if (mp[words[i]] > 0 || words[i-1].back() != words[i].front()) {
            return {i % n + 1, i / n + 1};
        }
        
        // 조건 만족하지 않으면 끝말잇기 진행
        mp[words[i]]++;
    }
    
    return {0, 0};
}