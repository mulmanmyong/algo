#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(vector<int> priorities, int location) {
    int ans = 1;
    priority_queue<int> pq;
    queue<pair<int, int>> q;
    
    for (int i = 0; i < priorities.size(); i++) {
        q.push(pair(i, priorities[i]));
        pq.push(priorities[i]);
    }
    
    while (!pq.empty()) {
        pair<int, int> cur = q.front();
        q.pop();
        
        if (cur.second == pq.top()) {
            if (cur.first == location) {
                break;
            }
            else {
                ans++;
                pq.pop();
            }
        }
        else {
            q.push(cur);
        }
    }
    
    return ans;
}