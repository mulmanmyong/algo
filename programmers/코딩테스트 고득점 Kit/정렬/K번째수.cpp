#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> array, vector<vector<int>> commands) {
    vector<int> ans;

    for (auto cmd : commands) {
        vector<int> cpyarr(array.begin() + cmd[0] - 1, array.begin() + cmd[1]);
        sort(cpyarr.begin(), cpyarr.end()); 
        ans.push_back(cpyarr[cmd[2] - 1]);
    }

    return ans;
}
