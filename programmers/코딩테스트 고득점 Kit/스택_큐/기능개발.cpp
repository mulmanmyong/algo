#include <string>
#include <vector>
#include <queue>
#include <iostream>

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    
    queue<int> q;
    int num = 0;
    
    // 남은 일수를 모두 입력
    for (int i = 0; i < progresses.size(); i++) {
        num = 100 - progresses[i];
        if (num % speeds[i] == 0) {
            num = num / speeds[i];
        }
        else {
            num = (num / speeds[i]) + 1;
        }
        
        q.push(num);
    }
    
    // queue를 이용해서 제거하는 형식으로 진행
    while(!q.empty()) {
        
        int first = q.front(); // q의 제일 앞 -> queue는 FIFO
        int count = 1; // 배포 개수
        q.pop(); // 작업 완료 제거
        
        while(!q.empty() && first >= q.front()) {
            count++; // 같은 날 작업이 끝났거나, 이미 끝나서 제일 앞에 있는 작업 기다리고 있었음
            q.pop(); // 작업 완료된 것 제거
        }
        
        answer.push_back(count); // 정답에 배포수 추가
    }
    
    
    return answer;
}