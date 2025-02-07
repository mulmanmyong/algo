#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(int bridge_length, int weight, vector<int> truck_weights) {
    int ans = bridge_length;
    queue<int> qTruck;
    queue<int> q;
    int totalWeight = 0;
    
    for (int num : truck_weights) {
        qTruck.push(num);
    }
    
    while (!qTruck.empty()) {
        
        ans++;
        int cur = qTruck.front();
        
        if (q.size() == bridge_length) {
            totalWeight -= q.front();
            q.pop();
        }
        
        if (totalWeight + cur <= weight) {
            totalWeight += cur;
            q.push(cur);
            qTruck.pop();
        }
        else {
            q.push(0);
        }
        
    }
    
    return ans;
}