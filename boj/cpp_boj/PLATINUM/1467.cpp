// 1467. 수 지우기 -> 플래티넘 2

#include <bits/stdc++.h>

using namespace std;

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  string n, delN;
  cin >> n >> delN;


  // 숫자의 개수를 담을 배열
  int numCnt[10] = {0};
  // 각 자리마다 숫자가 출현 횟수 count
  for (char c : n) {
    numCnt[c-'0']++;
  }

  // 삭제할 숫자의 개수를 담을 배열
  int delCnt[10] = {0};
  // 삭제할 숫자들 갯수 count
  for (char c : delN) {
    delCnt[c-'0']++;
    numCnt[c-'0']--; // 사용할 수 있는 숫자만 남기기, (삭제될 숫자들 갯수 미리 줄이기)
  }

  int numLength = n.length() - delN.length(); // 정답의 길이
  string ans = ""; // 정답을 담을 것
  int cur = 0; // string에서 가르키고 있는 위치
  while (numLength--) { // 숫자를 다 만들면 종료
    
    // 큰 수부터 앞에 배치를 할 수 있는 지 판단
    // 해당 수를 사용할 수 있어야 하고, 해당 수 의 앞부분에 있는 수를 모두 지울 수 있어야 함
    // 자연수임. 0이랑 음수는 안된다는 것
    for (int chkNum = 9; chkNum >= 1; chkNum--) {
      if (numCnt[chkNum] == 0) continue; // 배치할 수 있는 숫자가 없음

      int chkCnt[10] = {0}; // chkNum이 나올 때 까지 나오는 숫자 갯수 담을 배열
      for (int index = cur; n[index]-'0' != chkNum; index++)  chkCnt[n[index]-'0']++;
    
      // 다 지울 수 있는 지 확인
      bool canDel = true;
      for (int i = 1; i <= 9; i++) {
        if (chkCnt[i] > delCnt[i]) {
          canDel = false;
          break;
        }
      }

      if (canDel) { // 지울 수 있으면
        // 지금 숫자 전까지 다 지워 버리기
        for(;n[cur]-'0' != chkNum; cur++) { // 배치할 글자 전까지 반복하며
          delCnt[n[cur]-'0']--; // 지워야 할 놈들 목록에서 제거
        }

        ans.push_back(chkNum+'0'); // 정답에 담고
        numCnt[chkNum]--; // 담을 수 있는 숫자에서도 줄이고
        cur++; // 현재 위치의 글자를 배치했으니깐 다음 위치로 업데이트 하고
        break; // 다음으로!
      }
    }
  }

  cout << ans;

  return 0;
}