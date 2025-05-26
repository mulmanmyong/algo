#include <string>
#include <vector>

using namespace std;

int solution(int n, vector<int> lost, vector<int> reserve) {
    int answer = 0;
    vector<int> vec;
    
    for (int i = 0; i <= n; i++) {
        vec.push_back(1);
    }
    
    for (int i = 0; i < lost.size(); i++) {
        vec[lost[i]]--;
    }
    
    for (int i = 0; i < reserve.size(); i++) {
        vec[reserve[i]]++;
    }
    
    for (int i = 1; i <= n; i++) {
        if (vec[i] == 0) {
            if (vec[i - 1] == 2) {
                vec[i - 1]--;
                vec[i]++;
            }
            else if (vec[i + 1] == 2) {
                vec[i + 1]--;
                vec[i]++;
            }
        }
    }
    
    for (int i = 1; i <= n; i++) {
        if (vec[i] >= 1) {
            answer++;
        }
    }
    
    return answer;
}