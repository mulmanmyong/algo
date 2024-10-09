// PCCP 기출문제 2번 석유시추 - lv2

#include <string>
#include <vector>
#include <queue>
#include <cstring>
#include <map>
#include <set>
#include <iostream>

using namespace std;

int visited[501][501];
vector<vector<int>> oil_map; // 전역변수로 선언하여, 값을 이용할 수 있도록 하는 것
// land는 solution 내부에서만 사용가능하기에
int id = 1;
int n;
int m;
map<int, int> cnt;

int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, 1, 0, -1};

void bfs(int x, int y)
{
    int oil = 0;
    queue<pair<int, int>> q;
    q.push({x, y});
    oil_map[x][y] = id;
    visited[x][y] = 1;

    while (!q.empty())
    {
        int qx = q.front().first;
        int qy = q.front().second;
        oil++;
        q.pop();

        for (int i = 0; i < 4; i++)
        {
            int nx = qx + dx[i];
            int ny = qy + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny] || oil_map[nx][ny] == 0)
            {
                // 범위 밖이거나, 이미 방문했거나, oil이 아니면
                continue;
            }

            oil_map[nx][ny] = id; // 같은 oil의 덩어리
            visited[nx][ny] = 1;  // 방문할 거기에
            q.push({nx, ny});     // queue가 빌 때까지 순환
        }
    }

    // 방복문 탈출, oil 한덩어리의 계산 끝남.
    cnt[id++] = oil;
}

int solution(vector<vector<int>> land)
{

    /*
    탐색을 사용해야할 듯 함
    수직으로 하나만 뚫으니깐, 그 해당 열을 뚫었을 때 그 열에서 각 행별로 연속되는 1을 check
    그런 식으로 진행을 하면 되지 않을까 함
    일단 이런식으로 진행

    추가로 석유덩어리마다 id를 부여하는 형식, 그러면 이미 방문한 석유는 해당 id로 바꾸고,
    중복 id 안담기게 열마다 set으로 id를 찾음
    그 id에 맞는 석유덩어리를 받아와서 더함.
    map<id, cnt>가 되어야할 것 같음
    => 특정 key에 맞는 값을 찾는 것이기 때문에, map이 맞다고 판단
    하지만 그냥 그렇다고 생각할 뿐 확실한 것은 공부할 것
    */

    n = land.size();
    m = land[0].size();
    memset(visited, 0, sizeof(visited));

    oil_map = land;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < m; j++)
        {
            if (visited[i][j] == 0 && oil_map[i][j] > 0)
            {
                bfs(i, j);
            }
        }
    }
    // oil덩어리들 끝남

    // 이제 최적의 시추관을 뚫을 자리 찾기
    int answer = 0;
    for (int i = 0; i < m; i++)
    {
        int oil = 0;
        set<int> oil_set; // 중복방지
        for (int j = 0; j < n; j++)
        {
            oil_set.insert(oil_map[j][i]);
        }
        for (auto oil_id : oil_set)
        {
            oil += cnt[oil_id];
        }

        answer = max(answer, oil);
    }

    return answer;
}