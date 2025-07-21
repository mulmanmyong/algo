// 1296. 팀 이름 정하기 -> 브론즈 1
#include <bits/stdc++.h>

using namespace std;

int main(void) {
  ios::sync_with_stdio(0); cin.tie(0);
  
  string yeondu;
  cin >> yeondu;

  int N;
  cin >> N;

  // 첫글자부터 글자를 담고, 이중포문으로 각 자리의 수를 더한 것들의 곱의 mod 100이기 때문
  string winner = "";
  int winPercentage=-1;
  while (N--) {
    string name; // 현재 이름 후보
    cin >> name;
    
    // 연두와 현재후보의 이름 결합
    string combined = yeondu + name;
    
    // 이름의 알파벳과 일치하는 것의 개수를 담을 배열
    int arr[4] = {0}; // 0 1 2 3 L O V E
    // LOVE 배열
    char love[4] = {'L', 'O', 'V', 'E'};

    for (int i = 0; i < 4; i++) {
      for (char c : combined) {
        if (c == love[i]) arr[i]++;
      }
    }

    int curPercentage=1; // 현재 후보의 우승확률
    // 우승확률계산
    for (int i = 0; i < 3; i++) {
      for (int j = i+1; j < 4; j++) {
        curPercentage *= (arr[i]+arr[j]);
      }
    }

    // mod 100
    curPercentage %= 100;

    // 우승확률 높은 후보 고르기
    if (curPercentage > winPercentage || (curPercentage == winPercentage && name < winner)) {
      winPercentage = curPercentage;
      winner = name;
    }
  }
  cout << winner;

  return 0;
}