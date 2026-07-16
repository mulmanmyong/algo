#include <string>
using namespace std;

int solution(string dirs) {
    // 게임 캐릭터를 4가지 명령어를 통해 움직이려 함
    // U D R L (0, 0)에서 시작
    // 처음 걸어본 길의 길이를 계산해라
    // 보드판은 (-5, -5) 부터 (5, 5)까지 있음
    // 계산이 쉽도록, 각각 +5한 좌표가 해당 좌표
    // 0,0 부터 10, 10까지

    // 시작은 (5, 5)
    int currentRow = 5;
    int currentCol = 5;

    // 상 하 우 좌
    int deltaRow[4] = {-1, 1, 0, 0};
    int deltaCol[4] = {0, 0, 1, -1};

    int dir = -1;
    int nextRow = -1;
    int nextCol = -1;

    // 길(간선) 방문 체크 (출발 좌표와 도착좌쵸)
    bool visited[11][11][11][11] = {};
    // 최초로 가는 길의 길이
    int answer = 0;

    for (char c : dirs) {

        // c 커맨드에 따라 이동하는 방향
        // U D R L 상 하 우 좌 0 1 2 3
        if (c == 'U') {
            dir = 0;
        }
        else if (c == 'D') {
            dir = 1;
        }
        else if (c == 'R') {
            dir = 2;
        }
        else if (c == 'L') {
            dir = 3;
        }

        // 다음 좌표가 범위 벗어났는 지 확인
        nextRow = currentRow + deltaRow[dir];
        nextCol = currentCol + deltaCol[dir];

        if (nextRow < 0 || nextRow > 10 || nextCol < 0 || nextCol > 10) {
            continue; // 넘었으니 이동 못함
        }

        // 이동 가능하니 이동

        // 최초 방문했는지 확인, true가 아니면 최초방문이므로 처음 걸어본 길의 길 추가
        if (!visited[currentRow][currentCol][nextRow][nextCol]) {
            answer++;

            // 왕복도 같은 길이므로 양방향 모두 방문 처리
            visited[currentRow][currentCol][nextRow][nextCol] = true;
            visited[nextRow][nextCol][currentRow][currentCol] = true;
        }

        currentRow = nextRow;
        currentCol = nextCol;
    }

    return answer;
}