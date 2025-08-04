// 1600. 말이 되고픈 원숭이 -> 골드 3

#include <bits/stdc++.h>

using namespace std;

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  
  // 맨 왼쪽 위에서 맨 오른쪽 아래까지 가려고 함 
  // 말(나이트)처럼 이동 가능
  int moveLikeHorseColumn[8] = {-2, -2, -1, -1, 1, 1, 2, 2};
  int moveLikeHorseRow[8] = {-1, 1, -2, 2, -2, 2, -1, 1};

  // 근데 해당 이동횟수는 k번 가능
  int K;
  cin >> K;
  
  // 그 외에는 인접 상하좌우
  int deltaColumn[4] = {0, 0, -1, 1};
  int deltaRow[4] = {-1, 1, 0, 0};

  // 격자판 크기, 가로 W, 세로 H
  int W, H;
  cin >> W >> H;

  int board[200][200] = {0};
  // 격자판 입력
  for (int row = 0; row < H; row++) {
    for (int column = 0; column < W; column++) {
      cin >> board[row][column];
    }
  }

  // 방문 했니? 그리고 말처럼 이동했니?, 말이동경우랑 안이동 경우 2가지를 모두 queue에 담으며 방문
  int visited[200][200][31] = {0};
  // 0은 아무것도 없는 평지, 1은 장애물 
  queue<pair<pair<int, int>, int>> q;
  q.push({{0, 0}, 0}); // 출발은 왼쪽 맨 위
  visited[0][0][0] = 1;
  int answer = -1; // while문을 탈출할때 까지 값이 변경안되면, 목적지에 도달할 수 없었던 것
  while (!q.empty()) {
    int row = q.front().first.first;
    int column = q.front().first.second;
    int moveLikeHorseCount = q.front().second;
    q.pop();

    // 현재의 row, column, moveLikeHorseCount중에서
    // row와 column이 목적지 좌표라면, 누군가 먼저 도달한 것 
    if (row == H-1 && column == W-1) {
      answer = visited[row][column][moveLikeHorseCount]-1; // visited의 시작이 1이었기 때문
      break; // 최초 도달자 나왔으니 탐색 종료
    }
    
    // 인접 4방향 이동의 경우
    for (int dir = 0; dir < 4; dir++) {
      int newRow = row + deltaRow[dir];
      int newColumn = column + deltaColumn[dir];

      // 배열의 범위를 벗어날 경우
      if (newRow < 0 || newRow >= H || newColumn < 0 || newColumn >= W) continue;
      // 이동 위치에 장애물이 있거나 이미 방뭉했을 경우
      if (board[newRow][newColumn] == 1 || visited[newRow][newColumn][moveLikeHorseCount] != 0)  continue;

      // 그 외에는 이동 가능
      visited[newRow][newColumn][moveLikeHorseCount] = visited[row][column][moveLikeHorseCount] + 1;
      q.push({{newRow, newColumn}, moveLikeHorseCount});
    }

    // 말(나이트)처럼 이동의 경우, 이동가능 횟수 안에서만 
    if (moveLikeHorseCount < K) {
      for (int dir = 0; dir < 8; dir++) {
        int newRow = row + moveLikeHorseRow[dir];
        int newColumn = column + moveLikeHorseColumn[dir];
        
        // 배열의 범위를 벗어날 경우
        if (newRow < 0 || newRow >= H || newColumn < 0 || newColumn >= W) continue;
        // 이동 위치에 장애물이 있거나 이미 방뭉했을 경우
        if (board[newRow][newColumn] == 1 || visited[newRow][newColumn][moveLikeHorseCount+1] != 0)  continue;
      
        // 그 외에는 이동 가능
        visited[newRow][newColumn][moveLikeHorseCount+1] = visited[row][column][moveLikeHorseCount] + 1;
        q.push({{newRow, newColumn}, moveLikeHorseCount+1});
      }
    }
  }
  
  cout << answer << '\n';

  return 0;
}
