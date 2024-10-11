#include <string>
#include <vector>
#include <map>
#include <algorithm>
#include <iostream>

using namespace std;

bool cmp(const pair<string, int> &a, const pair<string, int> &b) {
    if (a.second == b.second) 
        return a.first > b.first;
    return a.second > b.second;
    
}

string solution(vector<string> participant, vector<string> completion) {
    string answer = "";
    
    // map을 사용하여 참가자 목록을 옮겨담고, 
    // 그 후에 completion을 을 이용하여 해당 키를 -1하는 형식으로 진행
    // 이 때 최후에 0이 안된 친구가 나머지
    
    map<string, int> mp;
    for (int i = 0; i < participant.size(); i++) {
        mp[participant[i]] += 1;
    }
    
    for (int i = 0; i < completion.size(); i++) {
        mp[completion[i]] -= 1;
    }
    
    vector< pair<string, int> > vec(mp.begin(), mp.end());
    sort(vec.begin(), vec.end(), cmp);
    
    answer = vec[0].first;
    
    return answer;
}

int main() {

    vector<string> tmp1;
    tmp1.push_back("leo");
    tmp1.push_back("kiki");
    tmp1.push_back("eden");
    vector<string> tmp2;
    tmp2.push_back("eden");
    tmp2.push_back("kiki");


    cout << solution(tmp1, tmp2) << endl;

    return 0;
}