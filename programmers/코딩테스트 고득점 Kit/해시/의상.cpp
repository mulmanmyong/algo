#include <string>
#include <vector>
#include <map>
#include <iostream>

using namespace std;

int solution(vector<vector<string>> clothes) {
    
    int answer = 1;
    // 의상의 이름, 의상의 종류로 제공이 됨
    // 그렇다면 map에다가 의상의 종류에 따른 분류를 하는 것은 어떠한가 ?
    // OK 그러면 map의 key를 의상의 종류로 하고 value는 종류별 개수로 함

    map<string, int> mp;
    for (int i = 0 ; i < clothes.size(); i++) {
        mp[clothes[i][1]] += 1; // 있으면 하나씩 늘림
    }
    
    // map을 순회하면서 각 종류를 곱해줌 (value값 + 1 => 착용안했을 경우의 수)
    for (auto iter = mp.begin(); iter != mp.end(); iter++) {
        answer *= (iter->second + 1);
    }
    
    // 아무것도 착용안했을 경우 -1
    return answer - 1;
}