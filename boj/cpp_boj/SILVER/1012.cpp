// 1012. 유기농 배추 - 실버 2

#include <iostream>
#include <algorithm>
#include <queue>
#include <vector>
#include <cstring>

using namespace std;

int farm[51][51] = { 0, };
int visited[51][51] = { 0, };
int m, n, k;

int dx[4] = { 1, 0, -1, 0 };
int dy[4] = { 0, 1, 0, -1 };

void bfs(int x, int y) {
	queue< pair<int, int> > q;
	q.push(make_pair(x, y));
	visited[x][y] = 1;

	while (!q.empty()) {
		int a = q.front().first;
		int b = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = a + dx[i];
			int ny = b + dy[i];

			if (0 <= nx && nx < m && 0 <= ny && ny < n && visited[nx][ny] == 0 && farm[nx][ny] == 1) {
				q.push(make_pair(nx, ny));
				visited[nx][ny] = 1;
			}
		}
	}
}

int main() {

	vector<int> result;
	int test_case;
	cin >> test_case; // test case 

	for (int t = 0; t < test_case; t++) {
		cin >> m >> n >> k;
		memset(farm, 0, sizeof(farm));
		memset(visited, 0, sizeof(visited));

		int a, b, cnt = 0;
		for (int i = 0; i < k; i++) {
			cin >> a >> b;
			farm[a][b] = 1;
		}

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (farm[i][j] == 1 && visited[i][j] == 0) {
					bfs(i, j);
					cnt++;
				}
			}
		}

		result.push_back(cnt);
	}

	for (int i = 0; i < result.size(); i++) {
		cout << result[i] << endl;
	}


	return 0;
}