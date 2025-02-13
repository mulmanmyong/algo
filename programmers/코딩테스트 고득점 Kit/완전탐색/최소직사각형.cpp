#include <string>
#include <vector>

using namespace std;

int solution(vector<vector<int>> sizes) {
    int ans = 0;
    
    int w = 0, h = 0;
    for (auto size : sizes) {
        int w_tmp, h_tmp;
        if (size[0] >= size[1]) {
            w_tmp = size[0];
            h_tmp = size[1];
        }
        else {
            w_tmp = size[1];
            h_tmp = size[0];
        }
        
        if (w_tmp > w) {
            w = w_tmp;
        }
        if (h_tmp > h) {
            h = h_tmp;
        }
    }
    
    ans = w * h;
    
    return ans;
}