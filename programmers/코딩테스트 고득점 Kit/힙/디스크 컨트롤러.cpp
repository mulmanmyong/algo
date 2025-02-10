#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

struct cmp {
    bool operator()(vector<int> a, vector<int> b) {
        return a[1] > b[1];
    }
};

int solution(vector<vector<int>> jobs) {
    int ans = 0;
    
    int totalTime = 0;
    int currentTime = 0;
    int cnt = 0;
    priority_queue<vector<int>, vector<vector<int>>, cmp> pq;
    
    sort(jobs.begin(), jobs.end());
    while (cnt < jobs.size() || !pq.empty()) {
        
        if (cnt < jobs.size() && jobs[cnt][0] <= currentTime) {
            pq.push(jobs[cnt]);
            cnt++;
            continue;
        }
        
        if (!pq.empty()) {
            currentTime += pq.top()[1];
            totalTime += (currentTime - pq.top()[0]);
            pq.pop();
        }
        else {
            currentTime = jobs[cnt][0];
        }
    }
    
    ans = totalTime / jobs.size();
    return ans;
}