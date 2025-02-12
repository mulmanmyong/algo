#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool compare(string a, string b) {
    return a + b > b + a;
}

string solution(vector<int> numbers) {
    string ans = "";
    vector<string> numStr;
    for (int num : numbers) {
        numStr.push_back(to_string(num));
    }
    
    sort(numStr.begin(), numStr.end(), compare);
    
    for (string str : numStr) {
        ans += str;
    }
    
    if (ans[0] == '0') {
        ans = "0";
    }
    
    return ans;
}