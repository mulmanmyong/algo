// 1181. 단어 정렬 -> 실버 5

#include <bits/stdc++.h>

using namespace std;

string word[20001];

bool compare(string a, string b) {
  if (a.length() == b.length()) { // 길이 같으면 사전 순
    return a < b;
  }
  return a.length() < b.length(); // 기본은 길이 짧은 순
}


void func1(void)
{
  int N;
  cin >> N;

  // map을 이용해서 단어길이 단어 이런식으로 저장
  // 그 후 짧은 것 정렬 후, 길이가 같으면 사전순 정렬 할 수 있도록 compare 함수 작성
  for (int i = 0; i< N; i++) {
    cin >> word[i];
  }

  sort(word, word+N, compare);

  for (int i = 0; i < N; i++) {
    if (word[i] == word[i-1]) continue; // 중복제거
    cout << word[i] << '\n';
  }
}

int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}
