// 1868. 파핑파핑 지뢰찾기

#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

int n, cnt;
char board[301][301];
int visited[301][301];
int di[8] = {1, 0, -1, 0, 1, 1, -1, -1};
int dj[8] = {0, 1, 0, -1, 1, -1, -1, 1};

int trap(int i, int j) {
  int cnt = 0;
  for (int dir = 0; dir < 8; dir++) {
    int ni = i + di[dir];
    int nj = j + dj[dir];
    if (ni < 0 || ni >= n || nj < 0 || nj >= n) continue;
    if (board[ni][nj] == '*') cnt++;
  }
  return cnt;
}

int main(int argc, char** argv)
{
	int test_case;
	int T;
	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
    cin >> n;

    string s;
    for (int i = 0; i < n; i++) {
      cin >> s;
      for (int j = 0; j < n; j++) {
        board[i][j] = s[j];
        visited[i][j] = 0;
      }
    }

    cnt=0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] == '*' || visited[i][j])  continue;
        if (trap(i, j) != 0)  continue; // 주변에 지뢰 있으면 bfs 진행안함(전이불가)
        
        queue<pair<int, int>> q;
        q.push({i, j});
        visited[i][j] = 1;
        cnt++;
        while (!q.empty()) {
          int a = q.front().first;
          int b = q.front().second;
          q.pop();
          
          for (int dir = 0; dir < 8; dir++) {
            int ni = a + di[dir];
            int nj = b + dj[dir];

            if (ni < 0 || ni >= n || nj < 0 || nj >= n) continue;
            if (visited[ni][nj])  continue;
            visited[ni][nj]=1;
            if (trap(ni, nj) == 0) { // 주변에 지뢰가 없는 경우만 
              q.push({ni, nj});
            }
          }       
        }
      }
    }

    // bfs가 불가하여, 한칸씩 남은 경우가 존재 한칸의 경우 모두 count
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (board[i][j] != '*' && !visited[i][j]) cnt++;
      }
    }

    cout << '#' << test_case << ' ' << cnt << '\n';
	}
	return 0;
}