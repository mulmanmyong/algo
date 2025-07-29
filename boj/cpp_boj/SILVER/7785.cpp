// 7785. 회사에 있는 사람-> 실버 5

#include <bits/stdc++.h>

using namespace std;

bool cmp(string s1, string s2) {
  return s1 > s2; // 앞에꺼가 뒤에꺼보다 커야함 -> 내림차순
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  // 출입 기록의 수 n
  int n;
  cin >> n;

  set<string, greater<string>> s; // set은 기본 오름차순 삽임, greater<>를 이용하여 내림차순으로 삽입할 수 있도록
  for (int i = 0; i < n; i++) {
    string name; string state;
    cin >> name >> state;

    if (state == "enter") {
      s.insert(name); // 추가
    }
    else if (state == "leave") {
      // vec.erase(remove(vec.begin(), vec.end(), name)); // 시간 초과 // remove는 뒤로 보내는 것, erase가 지우는 것
      s.erase(name); // 삭제
    }
  }

  for (string n : s) {
    cout << n << '\n';
  }

  return 0;
}