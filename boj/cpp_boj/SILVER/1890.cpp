// 1890. 점프 -> 실버 1

#include <bits/stdc++.h>

using namespace std;

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  int board[101][101] = {0};
  int di[2] = {1, 0};
  int dj[2] = {0, 1};

  // NxN 게임판 수가 적힘, 
  // 가장 왼쪽 위 칸에서 가장 오른쪽 아래칸으로 규칙에 맞게 점프
  // 점프 규칙 : 현재 칸에서 갈 수 있는 거리, 오른쪽이나 아래쪽으로만 이동 가능
  // 0은 더 이상 진행을 막는 종점
  int N;
  cin >> N;

  // 게임판에 이동할 수 있는 거리 입력
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      cin >> board[i][j];
    }
  }

  long long ans[101][101]; // queue가 메모리 초과에 영향을 준듯함
  // 100x100은 10000 모두 반복해도 1억이 안넘기에 1초가 안넘을 것
  // 그래서 bfs가 아닌 dp방식으로 전환

  // 초기값 세팅
  ans[0][0] = 1; // 초기에서 시작하는 경로는 하나
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      if (board[i][j] == 0) continue; // 마지막 지점은 이동 필요 없음 
      // -> 해당값 처리 안하면 N-1+0=N-1 < N 되기에 재자리 점프를 해버림
      if (ans[i][j] == 0) continue;

      int jump = board[i][j]; // 점프할 거리
      
      // 아래 방향 점프 가능한 범위
      if (i + jump < N)   ans[i+jump][j] += ans[i][j]; // 가능한 경우의 수 증가
      // 오른쪽 방향 점프 가능한 범위
      if (j + jump < N)   ans[i][j+jump] += ans[i][j]; // 가능한 경우의 수 증가
    }
  }

  cout << ans[N-1][N-1];

  return 0;
}