// 1806. 부분합 -> 골드 5

#include <bits/stdc++.h>

using namespace std;

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  
  // 길이가 n짜리 수열이 주어짐. 부분합이 s이상이 되는 것 중 가장 짧은 것의 길이를 구해라
  int n, s;
  cin >> n >> s;

  // 수열을 담을 vector
  vector<int> vec;
  for (int i = 0; i < n; i++) {
    int tmp;
    cin >> tmp;
    vec.push_back(tmp);
  }

  // 투포인터 기법을 이용하여 탐색, in : inclusive(포함), ex : exclusive(비포함)
  // ans : 합이 s이상이 되었을때의 길이를 저장, n의 범위가 최대 100000아가때문에 이보다 큰수로 초기화
  int in=0, ex=0, ans=100001;
  int sum = 0; // 합을 저장할 변수
  while (true) {
    if (sum < s) {
      if (ex == n) break; // 현재의 합이 s보다 작은데, 뒷쪽 포인터가 끝까지 도달했으먄 종료
      sum += vec[ex]; // 뒤는 추가
      ++ex;
    }
    else if (sum >= s) {
      // 현재의 합이 s이상이면 현재의 길이를 구하기 : ex-in
      ans = min(ans, ex-in);
      sum -= vec[in]; // 앞에는 제거
      ++in;
    }
  }

  if (ans == 100001)  cout << "0\n"; // 초기값이면 계산할 수 없음을 의미
  else  cout << ans;

  return 0;
}