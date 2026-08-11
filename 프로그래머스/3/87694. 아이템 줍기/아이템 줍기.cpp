#include <string>
#include <vector>
#include <queue>

using namespace std;

// 직사각형 테두리 전부 그리기
void drawRectangleLine(vector<vector<int>>& rectangles, 
                       vector<vector<bool>>& horizontal, vector<vector<bool>>& vertical) {
    for (vector<int>& rectangle : rectangles) {
        int x1 = rectangle[0];
        int y1 = rectangle[1];
        int x2 = rectangle[2];
        int y2 = rectangle[3];
        
        // 위 아래 테두리 그리기
        // horizontal[x][y]는 (x - 1, y)와 (x, y)를 연결하는 선
        for (int x = x1 + 1; x <= x2; x++) {
            horizontal[x][y1] = true;
            horizontal[x][y2] = true;
        }
        
        // 좌 우 테두리 그리기
        // vertical[x][y]는 (x, y - 1)와 (x, y)를 연결하는 선
        for (int y = y1 + 1; y <= y2; y++) {
            vertical[x1][y] = true;
            vertical[x2][y] = true;
        }
    }
}

// 겹쳐진 직사각형 내부 선 제거하기
void eraseInnerRectangleLine(vector<vector<int>>& rectangles, 
                             vector<vector<bool>>& horizontal, vector<vector<bool>>& vertical) {
    for (vector<int>& rectangle : rectangles) {
        int x1 = rectangle[0];
        int y1 = rectangle[1];
        int x2 = rectangle[2];
        int y2 = rectangle[3];
        
        // 직사각형 내부에 있는 가로 선들은 이동할 수 없으므로 제거
        for (int y = y1 + 1; y < y2; y++) {
            for (int x = x1 + 1; x <= x2; x++) {
                horizontal[x][y] = false;
            }
        }
        
        // 직사각형 내부에 있는 세로 선들은 이동할 수 없으므로 제거
        for (int x = x1 + 1; x < x2; x++) {
            for (int y = y1 + 1; y <= y2; y++) {
                vertical[x][y] = false;
            }
        }
    }
}

// bfs 진행
int bfs(int characterX, int characterY, int itemX, int itemY, 
        vector<vector<bool>>& horizontal, vector<vector<bool>>& vertical) {
    queue<pair<int, int>> q;
    
    // 각 좌표까지 이동한 거리 담을 배열
    vector<vector<int>> dist(51, vector<int>(51, 0));
    
    // 방문 배열
    vector<vector<bool>> visited(51, vector<bool>(51, false));
    
    // 방향 배열
    int deltaX[4] = {-1, 1, 0, 0};
    int deltaY[4] = {0, 0, -1, 1};
    
    // 캐릭터 시작 위치 큐에 넣기
    q.push({characterX, characterY});
    visited[characterX][characterY] = true;
    
    while (!q.empty()) {
        int currentX = q.front().first;
        int currentY = q.front().second;
        q.pop();
        
        // 아이템 위치에 도착했다면 현재까지 이동한 거리 반환
        if (currentX == itemX && currentY == itemY) {
            return dist[currentX][currentY];
        }
        
        // 상하좌우 탐색
        for (int dir = 0; dir < 4; dir++) {
            int nextX = currentX + deltaX[dir];
            int nextY = currentY + deltaY[dir];
            
            // 배열 범위를 벗어나면 이동 불가능
            if (nextX < 0 || nextX > 50 || nextY < 0 || nextY > 50) {
                continue;
            }
            
            // 이미 방문한 곳이면 이동하지 않음
            if (visited[nextX][nextY]) {
                continue;
            }
            
            // 현재 좌표와 다음 좌표 사이에 실제 테두리가 있는지 확인
            bool canMove = false;
            
            // 왼쪽 이동
            if (dir == 0) {
                // horizontal[currentX][currentY]는 
                // (currentX - 1, currentY)와 (currentX, currentY)를 연결하는 선
                if (horizontal[currentX][currentY]) {
                    canMove = true;
                }
            }
            // 오른쪽 이동
            else if (dir == 1) {
                // horizontal[currentX + 1][currentY]는
                // (currentX, currentY)와 (currentX + 1, currentY)를 연결하는 선
                if (horizontal[currentX + 1][currentY]) {
                    canMove = true;
                }
            }
            // 아래 이동
            else if (dir == 2) {
                // vertical[currentX][currentY]는
                // (currentX, currentY - 1)와 (currentX, currentY)를 연결하는 선
                if (vertical[currentX][currentY]) {
                    canMove = true;
                }
            }
            
            // 위 이동
            else if (dir == 3) {
                // vertical[currentX][currentY + 1]는
                // (currentX, currentY)와 (currentX, currentY + 1)를 연결하는 선
                if (vertical[currentX][currentY + 1]) {
                    canMove = true;
                }
            }
            
            // 실제 테두리로 연결되어 있지 않으면 이동할 수 없음
            if (!canMove) {
                continue;
            }
            
            // 이동 가능한 곳이라면 방문 처리 후 큐에 넣기
            visited[nextX][nextY] = true;
            dist[nextX][nextY] = dist[currentX][currentY] + 1;
            q.push({nextX, nextY});
        }
    }
    
    return -1;
}

int solution(vector<vector<int>> rectangle, int characterX, int characterY, int itemX, int itemY) {
    // 캐릭터가 아이템을 줍기 위해 이동해야하는 가장 짧은 거리
    // 직사각형을 나타내는 모든 좌표값은 1 이상 50 이하인 자연수
    // 즉, 50까지의 크기 배열을 만들고 rectangle에 있는 애들만 갈수 있는 길로 표시
    // 근데 여기서 주의할 건 모든 직사각형을 겹치고, 그 테두리만 이동 할 수 있다!
    // 그리고 bfs로 탐색하면서 이동, itemX itemY 도착하면 그 즉시 종료
    
    // 가로 방향으로 이동가능한 선 담을 배열
    // horizontal[x][y]는 (x - 1, y)와 (x, y)를 연결하는 선
    vector<vector<bool>> horizontal(51, vector<bool>(51, false));
    
    // 세로 방향으로 이동가능한 선 담을 배열
    // vertical[x][y]는 (x, y - 1)와 (x, y)를 연결하는 선
    vector<vector<bool>> vertical(51, vector<bool>(51, false));
    
    // 먼저 이동가능한 경로 만들기
    // 각 직사각형의 테두리만 따서 만들기
    drawRectangleLine(rectangle, horizontal, vertical);
    
    // 겹쳤을 때 내부에 속한 선들 제거
    eraseInnerRectangleLine(rectangle, horizontal, vertical);
    
    // bfs 진행
    int answer = bfs(characterX, characterY, itemX, itemY, horizontal, vertical);
    
    return answer;
}