#include <bits/stdc++.h>
using namespace std;

// 정수 n, k 주어짐
int n, k;
// 크기가 n인 배열 a
vector<int> vec;

void init()
{
  // 정수 n, k 주어짐
  cin >> n >> k;
  // n개의 크기로 설정
  vec.resize(n);
  // n개의 수 주어짐
  for (int i = 0; i < n; i++)
  {
    cin >> vec[i];
  }
}

// 완탐으로 해서 시간초과가 나버림
// int sol(int size) {
//   int cnt = 0;

//   // 초기 size인 것의 합
//   int sum = 0;
//   for (int i = 0; i < size; i++) {
//     sum += vec[i];
//   }

//   // 해당 크기의 부분합이 k가 되면 개수 증가
//   if (sum == k) cnt++;

//   // size가 인덱스인 것부터 하나빼고 하나 더하고 식으로 끝까지 가지
//   for (int i = size; i < n; i++) {
//     sum += (vec[i]-vec[i-size]);
//     if (sum == k) cnt++;
//   }

//   return cnt;
// }

// 완탐 대신 해시맵을 이용한 O(n) 최적화
long long sol()
{
  // mp: 지금까지 나온 누적합(sum)의 횟수를 저장
  // key = 누적합, value = 등장 횟수
  unordered_map<int, long long> mp;

  // 합이 k인 부분배열 개수
  long long cnt = 0;
  // 현재까지 누적합
  int sum = 0;

  // 첫 원소부터 k 일 수도 있음
  // 따라서 sum-k가 0이면 현재까지의 누적합이 k인 개수가 1이다 라는 것을 알려야함
  // 최초로 선언만 했을 때는 존재하지 않음
  // [] 연산자를 쓰면 key가 생성됨 
  mp[0] = 1; // 이런식으로 초기화 

  // 이제 n만큼 순회하기
  for (int i = 0; i < n; i++) {
    // 지금까지의 누적합에 vec[i] 더하기
    sum += vec[i];

    // sum-k 즉, 지금까지 누적합 k가 되는 횟수 찾기
    // []연산자를 쓰기전까진 key가 없기 때문에 나온적이 없다면? 으로 쓸 수 있음 
    if (mp.count(sum-k)) {
      // 0보다 크면 나온 적이 있다!
      // count에 더하기
      cnt += mp[sum-k];
    }

    // 이제 누적합 sum 나왔었으니 이를 1증가
    // 얘를 들어 얘가 뒤에 또 나왔으면 지금까지 나왔던 것들에 
    // 뒤에 가능한 부분을 또 이어 붙이는 것이기 때문에 나왔던 것의 개수를 계속 증가 시키는 것
    // 4 0
    // 2 -2 2 -2 면 이제
    // 2 -2 일 때 cnt를 하나 증가시킴 -> 얘는 이제 안되는 것이기 때문
    // 얘는 sum = 0이기에 mp[0]++해서 mp[0] = 2가 됨
    // 2 -2 가 하나 더 나오는데 얘는 이전에 나왔던 2 -2 뒤에 붙이면
    // 2 -2 2 -2가 되는 것이기 때문에 뒤에 2 -2의 경우에는 2개의 경우가 더 생기는 것이지
    // 이러한 방식 때문에 지금까지 되는 누적합이 나온 횟수를 계속 기록하는 것 
    mp[sum]++;
  }

  return cnt;
}

int main()
{
  ios::sync_with_stdio(0);
  cin.tie(0);

  init();

  // 합이 k가 되는 부분합의 개수 구하기
  // 슬라이딩 윈도우를 이용하여 풀어보기 1~n의 크기

  // 문제를 잘읽자
  // N×(N+1)/2개의 부분합 -> N=200000개가 최대 이러면 400억이 넘음 ㅋㅋ
  long long ans = 0;

  // 슬라이딩 윈도우를 이용하여 풀어보기 1~n의 크기
  // for (int size = 1; size <= n; size++) {
  //   ans += sol(size);
  // }

  // 완탐으로 하면 시간초과 해시맵을 이용해보기
  ans = sol();

  cout << ans;

  return 0;
}