#include <iostream>
#include <queue>
#include <algorithm>
#include <deque>

using namespace std;

// 입력
int N; // 보드의 크기
int K; // 사과의 개수
vector<vector<int>> board;
int L; // 뱀의 방향 변환 횟수
queue<pair<int, char>> dirCmd; // 시간에 따른 방햔 전환 명령 

// 뱀을 구성하기
/*
뱀은 좌측 맨 위 시작 오른쪽방향으로
1. 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
2. 만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
3. 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다. -> 머리는 한칸 늘어났지만 꼬리가 안자르니깐 몸길이가 늘어나는 것
4. 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다다. -> 머리도 한칸 늘어나고 꼬리도 잘라서 몸길이가 그대로 
*/
// deque를 이용해서 앞에 다음위치를 넣어주며 머리를 이동 시키고,
// pop back으로 꼬리 자르기
deque<pair<int, int>> snake; // 좌표를 넣는 것 (r, c)

// 방향 전환
// L은 현재 방향기준 왼쪽으로 회전한다는 것이고, D는 현재 방향 기준 오른쪽으로 회전한다는 것임 
// 우 하 좌 상
int dr[4] = {0, 1, 0, -1};
int dc[4] = {1, 0, -1, 0};

// 시간을 저장 (정답)
int ans = 0;
// 방향을 저장
int dir = 0; // 처음에는 오른쪽으로 


void input() {
    // 첫째 줄에 보드의 크기 N 주어짐
    cin >> N;
    // 다음 줄에 사과의 개수 K 주어짐
    cin >> K;

    // 보드 크기 초기화 NxN 크기, 0으로 초기화 
    board.assign(N, vector<int>(N, 0));

    // K개의 줄에 걸쳐 사과의 위치 주어짐 , r과 c로 주어짐 
    int r, c;
    for (int t = 0; t < K; t++) {
        cin >> r >> c;
        // 사과는 1로 기록
        board[r-1][c-1] = 1;
    }

    // 방향 명령을 입력받을 횟수
    cin >> L;
    int time;
    char cmd;
    for (int t = 0; t < L; t++) {
        cin >> time >> cmd;
        dirCmd.push({time, cmd});
    }
}

bool move() {
    // 움직이기
    ans++; // 시간을 증가하기

    // 다음 좌표
    int nr = snake.front().first + dr[dir];
    int nc = snake.front().second + dc[dir];

    // 범위 체크
    if (nr < 0 || nr >= N || nc < 0 || nc >= N) return false;

    // 자신의 몸에 박는가?
    for (pair<int, int> cur : snake) {
        // 다음 좌표가 뱀의 구성 중에 있으면 자신의 몸에 박는 것 
        if (cur.first == nr && cur.second == nc)  return false;
    }

    // 그렇지 않다면 한칸이동
    snake.push_front({nr, nc});

    // 다음 위치가 사과인가?
    if (board[nr][nc] == 1) {
        // 사과면 사과를 먹고 몸길이는 증가
        board[nr][nc] = 0;
    }
    else {
        // 사과가 아니라면 꼬리 자르기
        snake.pop_back();
    }
    
    // 움직이기 성공
    return true;
}

void rotate(char cmd) {
    // 회전할 때가 되었음 !
    if (cmd == 'L') {
        dir = (dir + 3) % 4;
    }
    else if (cmd == 'D') {
        dir = (dir + 1) % 4;
    }
}

void play() {
    // 게임을 플레이 시작함
    // 초기 0,0부터 시작
    snake.push_back({0, 0});
    
    // 게임이 끝날 때 까지
    while (true) {
        // 움직일 수 없으면 게임 종료
        if (!move())  return;

        // 움직였음
        // 움직였을 때 X초가 끝났으면 회전하기 -> 명령이 있다면..
        if (!dirCmd.empty() && dirCmd.front().first == ans) {
            char cmd = dirCmd.front().second;
            dirCmd.pop();
            rotate(cmd);
        }
    }

}

int main() {

    ios::sync_with_stdio(0); cin.tie(0);

    // 입력을 받음
    input();
    // 입력 끝

    // 게임 시작
    play();
    // 게임 끝

    // 결과는?
    cout << ans;

    return 0;
}