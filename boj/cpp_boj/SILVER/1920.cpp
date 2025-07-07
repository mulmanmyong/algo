// 1920. 수 찾기 -> 실버 4

#include <bits/stdc++.h>

using namespace std;

void func1() {
  vector<int> vec;
  int n;
  cin >> n;
  while (n--) {
    int tmp;
    cin >> tmp;
    vec.push_back(tmp);
  }

  sort(vec.begin(), vec.end()); // 이진탐색을 위한 정렬
  int m;
  cin >> m;
  while (m--) {
    int tmp;
    cin >> tmp;
    /* 시간초과
    // find를 했을 때 존재하는 값이면 해당 인덱스릃 출력, 없으면 벡터의 사이즈를 출력
    if (find(vec.begin(), vec.end(), tmp) - vec.begin() == vec.size())  cout << "0\n";
    else  cout << "1\n";
    */
    if (binary_search(vec.begin(), vec.end(), tmp)) cout << "1\n";
    else  cout << "0\n";
  }
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
}