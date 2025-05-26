#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int solution(string name) {
    int answer = 0;
    int move = name.length() - 1; // 최악의 경우, 오른쪽으로 끝까지 이동
    
    for (int i = 0; i < name.length(); i++) {
         answer += min(name[i] - 'A', 'Z' - name[i] + 1);
        
        int next = i + 1;
        while (next < name.length() && name[next] == 'A') {
            next++;
        }
        
        move = min({move, i * 2 + (int)name.length() - next, i + 2 * ((int)name.length() - next)});

    }
    
    return answer + move;
}