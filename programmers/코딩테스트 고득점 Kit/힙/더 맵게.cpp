#include <iostream>
#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(vector<int> scoville, int K) {
    int ans = 0;
    priority_queue<int, vector<int>, greater<int>> mq;
    
    for (int num : scoville) {
        mq.push(num);
    }
    
    while (1) {
        int cur = mq.top();
        mq.pop();
        
        if (mq.empty() && cur < K) {
            ans = -1;
            break;
        }
        else if (cur < K) {
            ans++;
            int new_food = cur + (2*mq.top());
            mq.pop();
            mq.push(new_food);
        }
        else if (cur >= K) {
            break;
        }
    }    
    
    return ans;
}