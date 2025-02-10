#include <string>
#include <vector>
#include <queue>
#include <sstream>
#include <iostream>
#include <unordered_map>

using namespace std;

vector<int> solution(vector<string> operations) {
    vector<int> ans;
    priority_queue<int> pq1;
    priority_queue<int, vector<int>, greater<int>> pq2;
    unordered_map<int, int> cnt;
    int size = 0;
    char c; int n;
    for (string s : operations) {
        stringstream ss(s);
        ss >> c >> n;
        
        if (size == 0) {
            while (!pq1.empty()) {
                pq1.pop();
            }
            while (!pq2.empty()) {
                pq2.pop();
            }
        }
        
        if (c == 'I') {
            pq1.push(n);
            pq2.push(n);
            cnt[n]++;
            size++;
        }
        else if (c == 'D') {
            if (n == 1 && size != 0) {
                while (!pq1.empty() && cnt[pq1.top()] == 0) {
                    pq1.pop();
                }
                cnt[pq1.top()]--;
                pq1.pop();
                size--;
            }
            else if (n == -1 && size != 0) {
                while (!pq2.empty() && cnt[pq2.top()] == 0) {
                    pq2.pop();
                }
                cnt[pq2.top()]--;
                pq2.pop();
                size--;
            }
        }
    }
    
    while (!pq1.empty() && cnt[pq1.top()] == 0) {
        pq1.pop();
    }
    while (!pq2.empty() && cnt[pq2.top()] == 0) {
        pq2.pop();
    }
    
    
    if (size == 0) {
        ans.push_back(0);
        ans.push_back(0);
    }
    else {
        ans.push_back(pq1.top());
        ans.push_back(pq2.top());
    }
    
    return ans;
}