#include <string>
#include <vector>
#include <stack>
#include <algorithm>

using namespace std;

string solution(string number, int k) {
    string answer = "";
    int cnt = 0;
    int chk = 0;
    stack<char> s;
    
    for (char c : number) {
        while(!s.empty() && cnt != k && s.top() < c) {
            s.pop();
            cnt++;
        }
        s.push(c);
    }
    
    while (cnt != k) {
        s.pop();
        cnt++;
    }
    while (!s.empty()) {
        answer += s.top();
        s.pop();
    }
    
    reverse(answer.begin(), answer.end());
    
    return answer;
}