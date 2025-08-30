#include <iostream>
#include <vector>     // vector 사용
#include <array>      // array 사용
#include <algorithm>  // sort 사용

using namespace std;
// union-find + 크루스칼 풀이! 
/*
그래프가 주어졌을 때 그래프의 최소 스패닝 트리 구하는 프로그램 작성
정점의 개수 V, 간선의 개수 E

정점은 1~V까지 넘버링 
*/

// 정점의 개수 V, 간선의 개수 E 주어짐
int V, E;
// 부모 노드 
vector<int> parent;

// A, B 정점이 가중치 C 간선으로 연결됨
// 인접 리스트 + 크루스칼로 구현할거임
// 간선 정보 입력 (가중치, 정점1, 정점2)
vector<array<int, 3>> edges;

// make
void make() {
  // 부모 배열 초기화 (정점은 1부터 V까지)
  for (int index = 1; index <= V; index++) {
    parent[index] = index;
  }
}

// find
int find(int element) {
  // 부모의 노드가 곧 나다! -> 내가 부모
  if (parent[element] == element) return element;
  return parent[element] = find(parent[element]); // 경로 압축
}

// union
bool uninon(int element1, int element2) {
  int e1Parent = find(element1);
  int e2Parent = find(element2);

  // 부모가 같으면 같은 MST (사이클 발생)
  // union 불가
  if (e1Parent == e2Parent) return false;

  // 그렇지 않으면 union 가능
  // 번호가 작은 쪽을 부모로 합침 (일관성을 위함)
  if (e1Parent > e2Parent)  parent[e1Parent] = e2Parent;
  else parent[e2Parent] = e1Parent;

  return true;
}

int main() {
  ios::sync_with_stdio(0); cin.tie(0);

  // 노드와 간선의 정보를 입력 받음
  cin >> V >> E;

  // 입력받은 V 크기에 맞게 parent 벡터 크기 조절
  parent.resize(V + 1);

  // E개의 간선 정보 주어짐
  // 간선 정보 입력 받기 
  for (int index = 0; index < E; index++) {
    int a, b, c;
    cin >> a >> b >> c;
    edges.push_back({c, a, b});
  }

  // 가중치를 기준으로 오름차순 정렬
  sort(edges.begin(), edges.end());

  make();
  long long ans = 0;
  int edgeCount = 0;

  // 간선 탐색
  for (array<int, 3>& edge : edges) {
    int cost = edge[0];
    int node1 = edge[1];
    int node2 = edge[2];

    // 두 노드를 합칠 수 있다면 (사이클이 아니라면)
    if (uninon(node1, node2)) {
      ans += cost;
      edgeCount++;
    }

    // 최소 스패닝 트리는 V-1개의 간선으로 이루어짐
    // 다 찾았으면 더 볼 필요 없이 종료
    if (edgeCount == V - 1) break;
  }

  cout << ans << "\n";

  return 0;
}