#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

// 문제의 수 N, 좋은 문제에 대한 정보의 개수 M
int N, M;
// 인접리스트
vector<int> adjList[32001]; // 문제의 수 32000개
// 진입 차수 배열
int inDegree[32001];

// 위상정렬을 수행
void topologySort() {
  // 가능하면 쉬운문제부터 풀어야하기 때문에 priority_queue를 이용해서 BFS로 위상정렬 수행 
  priority_queue<int, vector<int>, greater<int>> pq;

  // 진입 차수가 0인 문제들을 큐에 담기
  for (int i = 1; i <= N; i++) {
    if (inDegree[i] == 0) pq.push(i);
  }

  // 큐가 빌 때까지 반복
  while (!pq.empty()) {
    int current = pq.top();
    pq.pop();

    // 큐에 담겨있다 출력되는 것은 차수가 0이기에 문제 풀이하는 문제
    // 즉, 출력
    cout << current << ' ';

    // 현재 문제와 같이 풀었을 때 풀기 쉬운 문제 확인하기 
    for (int next : adjList[current]) {
      // 차수 감수 
      inDegree[next]--;

      // 차수가 0인 애들은 큐에 담기 
      if (inDegree[next] == 0) {
        pq.push(next);
      }
    }
  }
}

int main() {
  ios::sync_with_stdio(0); cin.tie(0);
  
  /*
  1~N까지 총 N개의 문제로 되어 있는 문제집을 풀려 함 
  난이도 순서로 출제되어 있음 : 1번 문제가 가장 쉬운 문제이고, N번 문제가 가장 어려운 문제 
  1. N개의 문제는 모두 풀어야 함
  2. 먼저 푸는 것이 좋은 문제가 있는 문제는 먼저 푸는 것이 좋은 문제를 반드시 먼저 풀어야 함
  3. 가능하면 쉬운 문제부터 풀어야함 

  입력으로   
  첫째 줄에 문제의 수 N과 먼저 푸는 것이 좋은 문제에 대한 정보의 개수 M이 주어짐
  둘째 줄부터 M개의 줄에 걸쳐 두 정수의 순서쌍 A, B가 빈칸을 사이에 두고 주어짐
    이는 A번 문제는 B번 문제보다 먼저 푸는 것이 좋다는 의미

  즉, 위상정렬을 이용하면 풀릴 문제 
  */
  
  // 입력을 받음 
  cin >> N >> M;
  
  // 좋은 문제에 대한 정보를 입력 받음
  for (int i = 0; i < M; i++) {
    int A, B;
    cin >> A >> B;
    adjList[A].push_back(B); // A다음에 B를 풀어야 함 
    inDegree[B]++;
  }

  // 위사정렬로 출력 
  topologySort();

  return 0;
}