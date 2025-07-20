// 4193. 수영대회 결승전 (완전 탐색 + 구현)

#include<iostream>
#include <algorithm>
#include <queue>

using namespace std;

#define NNN 999999999
int pool[16][16];
int visited[16][16];
int di[4] = {1, 0, -1, 0};
int dj[4] = {0, 1, 0, -1};

int main(int argc, char** argv)
{
	int test_case;
	int T;
	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
    int n;
    cin >> n;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        cin >> pool[i][j];
        visited[i][j]=NNN;
      }
    }

    int a, b, c, d;
    cin >> a >> b;
    cin >> c >> d;

    queue<pair<pair<int, int>, int>> q;
    q.push({{a, b}, 0});
    visited[a][b] = 0;
    while (!q.empty()) {
      pair<int, int> cur = q.front().first;
      int t = q.front().second;
      q.pop();

      if (t > visited[cur.first][cur.second]) continue; // 이미 잘 가고 있는 경로가 있음
      
      for (int dir = 0; dir < 4; dir++) {
        int ni = cur.first + di[dir];
        int nj = cur.second + dj[dir];
        int nt;

        if (ni < 0 || ni >= n || nj < 0 || nj >= n) continue;
        if (pool[ni][nj] == 1) continue;

        // 일반적인 진행
        if (pool[ni][nj] == 0)  nt = t + 1;
        else if (pool[ni][nj] == 2) { // 소용돌이
          if (t % 3 == 2) { // 바로 이동가능
            nt = t + 1;
          }
          else {
            int wait_time = (2 - (t % 3) + 3) % 3;
            nt = t + wait_time + 1;
          }
        }

        if (visited[ni][nj] > nt) {
          visited[ni][nj] = nt;
          q.push({{ni, nj}, nt});
        }
      }
    }
    
    if (visited[c][d] == NNN) cout << '#' << test_case << " -1\n";
    else cout << '#' << test_case << ' ' << visited[c][d] << '\n';
	}
	return 0;
}