#include <string>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

int solution(int k, vector<int> tangerine) {
    int answer = 0;
    
    // 귤의 개수를 내림차순하고, 그 k보다 넘게 되는 지를 비교해가면 될 거 같음
    // 즉, 같은 크기의 귤을 count하고, count만 따로 내림차순을 진행
    
    // map을 사용하여 크기를 key, 크기에 따른 개수를 value로 잡으면 될 듯 함
    map<int, int> mp;
    for (int t : tangerine) {
        mp[t]++;
    }
    
    // value 정렬은 value를 vector에 넣고 내림차순 정렬
    vector<int> tangerineCounts;
    for (auto iter : mp) {
        tangerineCounts.push_back(iter.second);
    }
    
    // 내림차순 정렬
    sort(tangerineCounts.begin(), tangerineCounts.end(), greater<int>());
    
    // 이제 귤의 크기 종류 최소로 담는다! 그리디.. 많은 것부터 담아야 함
    int currentCount = 0;
    for (int tangerineCount : tangerineCounts) {
        // 종류 증가
        answer++;
        // 현재 개수에 이번 크기의 귤 개수 증가
        currentCount += tangerineCount;
        // 담으려는 귤의 개수보다 크거나 같아지면 종료
        if (currentCount >= k) {
            break;
        }
    }
    
    return answer;
}