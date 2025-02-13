#include <string>
#include <vector>
#include <cmath>

using namespace std;

vector<int> solution(int brown, int yellow) {
    vector<int> ans;
    int tiles = brown + yellow;
    int w = sqrt(tiles), h = 0;
    
    while (1) {
        if (tiles % w == 0 && w >= tiles / w) {
            h = tiles / w;
        }
        if ((w - 2) * (h - 2) == yellow) {
            break;
        }
        w++;
    }
    
    ans.push_back(w);
    ans.push_back(h);
    
    return ans;
}