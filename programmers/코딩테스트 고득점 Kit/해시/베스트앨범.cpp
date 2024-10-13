#include <string>
#include <vector>
#include <map>
#include <algorithm>
#include <iostream>

using namespace std;

bool playTotalSort(const pair<string, int>& a, const pair<string, int>& b) {
    return a.second > b.second;
}

bool playSort(const pair<int, int>& a, const pair<int, int>& b) {
    if (a.first == b.first)
        return a.second < b.second;
    return a.first > b.first;
}

vector<int> solution(vector<string> genres, vector<int> plays) {
    vector<int> answer;
    
    // 수록하는 순서는 각 장르별 재생수 총합이 높은 장르별로 수록
    // 이 때 각 장르별로 최대 2개
    // 장르 내에서 같은 노래 중에서는 고유번호가 낮은 노래를 먼저 수록
    
    // 고유번호를 return 하는 것이기 때문에 고유번호도 같이 저장을 해야 함 
    // key는 장르로 하고, value를 vector<pair>를 이용해서 고유번호랑 재생수를 같이 저장
    // 먼저 총 재생수로 우선 순위 정렬이 필요 -> 총 재생수를 담을 map도 생성
    map<string, int> playTotal;
    map<string, vector<pair<int, int>>> mp;
    for (int i = 0; i < genres.size(); i++) {
        playTotal[genres[i]] += plays[i];
        mp[genres[i]].push_back(make_pair(plays[i], i)); // 하나의 키에 여러개 담으니 벡터
    }
    
    // 재생수 많은 장르 순으로 정렬
    vector<pair<string, int>> playTotalVec(playTotal.begin(), playTotal.end());
    sort(playTotalVec.begin(), playTotalVec.end(), playTotalSort);
    
    for (auto genreVec: playTotalVec) { // 벡터 자체를 순회
        string genre = genreVec.first; // 정렬된 장르순으로 담기
        sort(mp[genre].begin(), mp[genre].end(), playSort);
        
        // 장르별 최대 2개 저장
        int cnt = 0;
        for (auto iter = mp[genre].begin(); iter != mp[genre].end(); iter++) {
            if (cnt == 2)
                break;
            answer.push_back(iter->second);
            cnt++;
        }
    }
    
    return answer;
}