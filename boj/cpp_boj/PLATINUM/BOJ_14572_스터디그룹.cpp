#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

/*
그룹 내에서 가장 잘 하는 학생과 가장 못 하는 학생의 실력 차이가 D 이하여야 함 
그룹의 효율성 E = (그룹 내의 학생들이 아는 모든 알고리즘의 수 - 그룹 내의 모든 학생들이 아는 알고리즘의 수) * 그룹원의 수 

조건을 만족하는 학생들의 부분집합 중 효율성이 최대가 되는 그룹을 뽑아 스터디 그룹으로 만들 예정
그럼 그룹의 효율성을 얼마가 될까?

압력으로 첫줄에 
학생의 수 N, 알고리즘의 수 K, 가장 잘하는 학생과 가장 못하는 학생의 실력차이 D 가 주어짐
그 다음 N명의 학생에 대해 정보가 주어짐
  해당 학생이 알고 있는 알고리즘의 수 M, 해당 학생의 실력 d
  그 다음 줄에 해당 학생이 알고있는 알고리즘이 M개 주어짐

실력을 기준으로 오름차순 정렬 후 투포인터로 진행 
알고리즘 배열을 2개 생성
학생들이 아는 모든 알고리즘의 수 (알고리즘count가 0이 아닌 거)
모든 학생들이 아는 알고리즘의 수 (알고리즘count가 학생의 수 인거)
*/

int N, K, D;
// 학생의 번호가 상관 없이, 실력순으로 정렬할 수 있게끔
// 즉, 실력과 해당 학생이 알고있는 알고리즘을 담을 배열을 인자로 선언 
vector<pair<int, vector<int>>> vec;
int algorithmCount[31]; // 알고리즘의 수는 1~30개 
int personCount; // 그룹원의 수 

int calculateE() {
  // 그룹의 효율성 구하기
  // 그룹의 효율성 E = (그룹 내의 학생들이 아는 모든 알고리즘의 수 - 그룹 내의 모든 학생들이 아는 알고리즘의 수) * 그룹원의 수 
  
  // knowCount : 그룹 내의 학생들이 아는 모든 알고리즘 수
  // commonKnowCount : 그룹 내의 모든 학생들이 아는 알고리즘 수 
  int knowCount = 0;
  int commonKnowCount = 0;
  for (int count : algorithmCount) {
    if (count == personCount) commonKnowCount++;
    if (count > 0) knowCount++;
  }

  // personCount : 그룹원의 수 

  int E = (knowCount - commonKnowCount) * personCount;
  // cout << E << '\n';
  return E;
}

int main() {
  ios::sync_with_stdio(0); cin.tie(0);

  cin >> N >> K >> D;

  // N명의 학생의 정보를 입력받음
  for (int i = 0; i < N; i++) {
    int M, d;
    cin >> M >> d;

    // M개의 알고리즘 개수를 입력하기 
    vector<int> algos; // 학생이 알고 있는 알고리즘 리스트 
    for (int j = 0; j < M; j++) {
      int a;
      cin >> a;
      algos.push_back(a);
    }

    vec.push_back({d, algos});
  }

  // 실력이 작은순부터 큰순으로 정렬
  sort(vec.begin(), vec.end());

  // 투 포인터로 끝과끝의 실력차를 통해 가능한 실력차일때, 최대 효율성 구하기 
  int st = 0; // 포함
  int ed = 0; // 비포함
  personCount = 0;

  int maxE = 0;
  while (st < N) {

    // 끝의 범위가 ed가 N보다 작고, 효율성이 계산 가능한 상태일 때
    // 현재 학생들의 알고리즘 상태들 계산하기 
    // 효율성 계산 가능할 때까지 ed인덱스 증가 시키며, 알고리즘 카운트 계산
    while (ed < N && vec[ed].first - vec[st].first <= D) {
      for (int a : vec[ed].second)  algorithmCount[a]++;
      personCount++;
      ed++;
    }

    // 실력차를 만족하는 최대까지 ed 증가시켰음,
    // 현재 구간의 효율성 계산하고 최대 갱신 
    if (personCount > 0) {
      int curreentE = calculateE();
      maxE = max(maxE, curreentE);
    }

    // 현재 구간에서 ed는 증가시킬 수 없으므로
    // st를 증가시켜서 st 학생의 알고리즘 수와 인원 수 제거 후 
    // 다음 가능성 확보 
    for (int a : vec[st].second)  algorithmCount[a]--;
    personCount--;
    st++;
  }

  // 결과 출력
  cout << maxE;

  return 0;
}