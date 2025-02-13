#include <string>
#include <vector>
#include <queue>

using namespace std;

vector<int> arr[101];
int cnt;

void bfs(int v1, int v2) {
    bool visited[101] = {0, };
    visited[v1] = true;
    visited[v2] = true;
    
    queue<int> q;
    q.push(v1);
    while (!q.empty()) {
        int a = q.front();
        q.pop();
        
        for (int i = 0; i < arr[a].size(); i++) {
            int b = arr[a][i];
            if (visited[b]) {
                continue;
            }
            
            cnt++;
            q.push(b);
            visited[b] = true;
        }
    }
}

int solution(int n, vector<vector<int>> wires) {
    int answer = 101;
    
    for (auto wire : wires) {
        arr[wire[0]].push_back(wire[1]);
        arr[wire[1]].push_back(wire[0]); 
    }
    
    for (auto wire : wires) {
        cnt = 1;
        bfs(wire[0], wire[1]);
        answer = min(answer, abs(2 * cnt - n));
    }
    
    return answer;
}