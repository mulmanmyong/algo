#include <algorithm>
#include <iostream>
#include <vector>

using namespace std;

// 기둥의 개수 N
int N;
// 기둥의 위치 L, 기둥의 높이 H 주어짐 
vector<pair<int, int>> vec;


int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  /*
  1. 지붕은 수평 부분과 수직 부분으로 구성되며, 모두 연결되어야 한다.
  2. 지붕의 수평 부분은 반드시 어떤 기둥의 윗면과 닿아야 한다.
  3. 지붕의 수직 부분은 반드시 어떤 기둥의 옆면과 닿아야 한다.
  4. 지붕의 가장자리는 땅에 닿아야 한다.
  5. 비가 올 때 물이 고이지 않도록 지붕의 어떤 부분도 오목하게 들어간 부분이 없어야 한다.

  제일 높은 곳을 기준으로 왼쪽부터 진행하며 커지면 높이를 갱신하여 너비를 계산
  오른쪽부터 진행하며 커지면 높이를 갱신하여 너비를 계산

  입력으로 막대의 개수 N 입력
  N개의 줄에 거쳐 기둥의 높이 L과 높이 H가 주어짐 
  */

  cin >> N;
  int maxHeight=0;
  for (int i = 0; i < N; i++) {
    int L, H;
    cin >> L >> H;
    if (H > maxHeight) {
      maxHeight = H;
    }

    vec.push_back({L, H});
  }
  
  // L -> 기둥의 인덱스를 기준으로 정렬
  sort(vec.begin(), vec.end());

  // 정렬 후 첫 최대 높이 인덱스 찾기
  int maxHeightIndex = 0;
  for (int i = 0; i < N; i++) {
    if (vec[i].second == maxHeight) {
      maxHeightIndex = i;
      break;
    }
  }

  int answer=0;

  // 중앙을 기준으로 왼쪽 높이 계산
  int currentMaxHeight=0;
  for (int i = 0; i < maxHeightIndex; i++) {
    // 최대 높이 갱신 
    if (vec[i].second > currentMaxHeight) currentMaxHeight = vec[i].second;
    // 현재의 높이를 기준으로 너비 계산, 기둥이 인덱스 별로 존재하는 것이 아니기 때문에 다음 인덱스까지의 거리를 곱해서 너비 계산 후 더해줌 
    answer += (currentMaxHeight*(vec[i+1].first-vec[i].first));
  }

  // 중앙을 기준으로 오른쪽 높이 계산
  currentMaxHeight=0;
  for (int i = N-1; i > maxHeightIndex; i--) {
    // 최대 높이 갱신 
    if (vec[i].second > currentMaxHeight) currentMaxHeight = vec[i].second;
    // 현재의 높이를 기준으로 너비 계산 
    answer += (currentMaxHeight*(vec[i].first - vec[i-1].first));
  }

  // 최대 높이짜리 한칸 더해주기 
  answer += maxHeight;
  // 최대 높이가 여러개일 변수를 생각해봤는데,
  // 이는 알아서 오른쪽 높이 계산할 때 계산이 됨 

  cout << answer;
  return 0;
}
