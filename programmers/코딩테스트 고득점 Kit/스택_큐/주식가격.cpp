#include <string>
#include <vector>
#include <stack>

using namespace std;

vector<int> solution(vector<int> prices) {
    vector<int> ans(prices.size(), 0);
    stack<int> st;
    
    for (int i = 0; i < prices.size(); i++) { 
        while (!st.empty() && prices[st.top()] > prices[i]) {
            int idx = st.top();
            st.pop();
            ans[idx] = i - idx;
        }
        st.push(i);    
    }
    
    while (!st.empty()) {
        int idx = st.top();
        st.pop();
        ans[idx] = prices.size() - 1 - idx;
    }
    
    return ans;
}
