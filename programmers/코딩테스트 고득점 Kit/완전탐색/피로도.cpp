#include <string>
#include <vector>

using namespace std;

int dungeon;
bool visited[8];

int dfs(vector<vector<int>> dungeons, int k, int count) {
    dungeon = max(dungeon, count);
    
    for (int i = 0; i < dungeons.size(); i++) {
        if (visited[i] || dungeons[i][0] > k) {
            continue;
        }
        
        visited[i] = true;
        dfs(dungeons, k - dungeons[i][1], count + 1);
        visited[i] = false;
    }
    
    return dungeon;
}

int solution(int k, vector<vector<int>> dungeons) {
    int answer = dfs(dungeons, k, 0);
    return answer;
}