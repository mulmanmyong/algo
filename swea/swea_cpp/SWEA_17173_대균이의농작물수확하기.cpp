#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>

using namespace std;
/*
로봇은 바라보고 있는 방향을 기준으로 오른쪽, 앞, 왼쪽, 뒤 순서로 확인하면서 이동
1일 동안 아래와 같은 작업을 수행
- 오전
	오후에 이동할 수 있는 영역이 있다면, 2가지 중 1가지 수행
	1. 씨앗을 심거나
	2. 다 자란 농작물을 수확하기
	- 이동할 수 없으면 그 자리에 그대로 있음(4면에 다 씨앗이 있고, 수확할 수 있는 농작물이 없는 경우일듯)

- 오후
	오전에서 판단한 내용을 토대로 이동할 수 있는 영역으로 이동

씨앗은 심은 후 다음 날 싹이 트고, K+3일이 지나 수확할 수 있다. K는 씨앗을 심는 회차를 의미 


입력
	첫째 줄에 테스트 케이스 개수가 주어짐
	각 테스트 케이스마다
		첫째줄에 영억의 크기 N, 로봇이 일하는 기간 D 주어짐
		그 다음에 영역의 정보가 주어짐 (산:1, 농작물 수확할 수 있는 영역:0)

출력
	일하는 기간 동안 최대로 수확할 수 있는 농작물의 개수 출력하기 
*/

// 테스트 케이스
int testCase;
// 영역의 크기 : N, 로봇이 일하는 기간 : D
int N, D;
// 로봇이 동작할 영역 6~10, 즉, 최대 10
int arr[10][10];
int tempArr[10][10];

// 방향 지정하기
// 우 상 좌 하
int deltaRow[4] = { 0, -1, 0, 1 };
int deltaColumn[4] = { 1, 0, -1, 0 };

void testPrint() {
	// 테스트 출력
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cout << arr[i][j] << ' ';
		}
		cout << '\n';
	}
}

// 현재의 땅을 업데이트 하기
void update() {
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			if (tempArr[i][j] >= 3) tempArr[i][j]--;
		}
	}
}


// 현재 상황을 기준으로 simulation 진행 
int simulation(int row, int column, int dir) {
	// 씨앗을 심는 회차
	// 씨앗을 심으면 해당회차+3일 뒤에 수확가능
	int K = 1;

	int currentRow = row;
	int currentColumn = column;
	int currentDirection = dir;
	int currentCount = 0;

	// D일동안 작업을 수행
	for (int day = 1; day <= D; day++) {
		// cout << day << "일차\n";

		// 현재 보는 방향 기준 우 상 좌 하 순으로 확인하기 
		// 수확이 가능하면 수확하는 방향으로 이동
		// 수확이 가능한 것이 없으면 씨앗을 심고 이동가능한 방향으로 이동
		
		// 0 : 씨앗을 심을 수 있는 공간, 1 : 산, 2 : 수확가능

		// 수확가능한지 플래그
		bool isPossible = false;
		int nextRow = -1, nextCol = -1, nextDir = -1;

		// 현재 방향 기준: 우, 상, 좌, 하 확인 
		for (int i = -1; i < 3; i++) {
      // -1은 곧 3이 됨
			int ndir = (currentDirection + i == -1 ? 3 : currentDirection + i) % 4;
			int nr = currentRow + deltaRow[ndir];
			int nc = currentColumn + deltaColumn[ndir];

			// 범위 벗어 난다면
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

			// 우선적으로 이동이 가능하면 이동을 합니다 -> 멍청한 로봇 같으니 
			if (tempArr[nr][nc] == 0) {
				nextRow = nr;
				nextCol = nc;
				nextDir = ndir;
				break;
			}

			// 수확이 가능한 경우만
			if (tempArr[nr][nc] == 2) {
				// 수확 가능
				isPossible = true;
				nextRow = nr;
				nextCol = nc;
				nextDir = ndir;
				break;
			}
		}

		// 수확이 가능하다면?
		if (isPossible) {
			// 수확
			tempArr[nextRow][nextCol] = 0;
			currentRow = nextRow;
			currentColumn = nextCol;
			currentDirection = nextDir;
			currentCount++;
		}
		// 불가능하다면 씨앗을 심기
		else {
			// 현재 위치에 씨앗을 심고
			tempArr[currentRow][currentColumn] += K + 3 + 2;
			// 씨앗을 심었음
			K++;

			// 이동 가능한 방향 찾기
			for (int i = -1; i < 3; i++) {
        // -1은 곧 3이 됨
				int ndir = (currentDirection + i == -1 ? 3 : currentDirection + i) % 4;
				int nr = currentRow + deltaRow[ndir];
				int nc = currentColumn + deltaColumn[ndir];

				// 범위 확인
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

				// 다음 위가 빈칸인 경우만 이동, 이동이 불가능하면 그대로 있기
				if (tempArr[nr][nc] == 0) {
					currentRow = nr;
					currentColumn = nc;
					currentDirection = ndir;
					break;
				}
			}
		}

		// 하루 지나며 성장 상태 업데이트
		update();
	}

	return currentCount;
}

int main() {
	ios::sync_with_stdio(0); cin.tie(0);

	// 테스트 케이스를 입력받음 
	cin >> testCase;
	for (int test_case = 1; test_case <= testCase; ++test_case) {
		// 영역의 크기와 일하는 기간을 입력받음
		cin >> N >> D;
		
		// 로봇이 동작할 영역에 대한 정보를 입력받음
		// 빈공간은 queue에 담아서 추우 시작 위치를 지정할 수 있도록 하기 
		queue<pair<int, int>> startPosition;
		for (int row = 0; row < N; row++) {
			for (int column = 0; column < N; column++) {
				cin >> arr[row][column];

				// 빈칸이면 로봇을 둘 수 있는 위치
				if (arr[row][column] == 0)	startPosition.push(make_pair(row, column));
			}
		}

		// 로봇이 어떠한 위치, 어떠한 방향으로 시작할 지는 정해지지 않음
		// 그저 최대 수확하는 경우를 판단
		// 로봇을 둘 수 있는 위치 startPosition 큐에서 확인하기
		// 모든 위치를 탐색할 때 까지 
		int maxCount = -1;
		while (!startPosition.empty()) {
			// 시작 위치
			int startRow = startPosition.front().first;
			int startColumn = startPosition.front().second;
			startPosition.pop();

			// 시작 방향 지정
			for (int startDirection = 0; startDirection < 4; startDirection++) {
				// 임시 배열 초기화 
				memcpy(tempArr, arr, sizeof(arr));
				maxCount = max(maxCount, simulation(startRow, startColumn, startDirection));
			}
		}

		// 출력
		cout << '#' << test_case << ' ' << maxCount << '\n';
	}

	return 0;
}