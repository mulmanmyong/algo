#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool compare(int a, int b) {
    return a > b;
}

int solution(vector<int> citations) {
    int ans = 0;
    
    sort(citations.begin(), citations.end(), compare);
    for (int i = 0; i < citations.size(); i++) {
        if (citations[i] >= i + 1) {
            if (citations.size() - i - 1 <= i + 1) {
                ans = i + 1;
            }
        }
        else {
            break;
        }
    }
    
    return ans;
}
