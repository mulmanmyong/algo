// 2667. 단지번호붙이기 -> 실버 1

#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;

vector<int> house[26];
vector<int> h_cnt;

int N, cnt = 0;
int visited[26][26] = { 0, };
int dx[4] = { 1, 0, -1, 0 };
int dy[4] = { 0, 1, 0, -1 };

void bfs(int x, int y) {
	queue< pair<int, int> > q;
	q.push(make_pair(x, y));
	visited[x][y] = 1;
	cnt++;

	while (!q.empty()) {
		int a = q.front().first;
		int b = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = a + dx[i];
			int ny = b + dy[i];

			// 범위를 벗어나지 않고, 방문한 적이 없고(visited 0), 이동할 수 있으면(house 1)
			if (0 <= nx && nx < N && 0 <= ny && ny < N && visited[nx][ny] == 0 && house[nx][ny] == 1) {
				q.push(make_pair(nx, ny));
				visited[nx][ny] = 1;
				cnt++;
			}
		}
	}
}

int main() {

	string str; // 한줄로 이어져서 입력됨
	cin >> N;

	for (int i = 0; i < N; i++) {
		cin >> str;
		for (int j = 0; j < N; j++) {
			house[i].push_back(str[j] - '0');
		}
	}

	// 완전탐색으로 그룹을 확인
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			
			if (house[i][j] == 1 && visited[i][j] == 0) {
				cnt = 0;
				bfs(i, j);
				h_cnt.push_back(cnt);
			}
		}
	}

	// 오름차순 정렬
	sort(h_cnt.begin(), h_cnt.end());

	cout << h_cnt.size() << endl;
	for (int i = 0; i < h_cnt.size(); i++) {
		cout << h_cnt[i] << endl;
	}

	return 0;
}