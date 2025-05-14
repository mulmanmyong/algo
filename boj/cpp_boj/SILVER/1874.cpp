// 1874. 스택 수열 -> 실버 2

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  int n;
  cin >> n;
  vector<int> vec;
  int k = n;
  while (k--) {
    int a;
    cin >> a;
    vec.push_back(a);
  }

  stack<int> s;
  vector<char> ans;
  int num = 1;
  for (int i = 0; i < n; ++i) {
    int target = vec[i];
    while (num <= target) {
      s.push(num);
      ans.push_back('+');
      num++;
    }
    if (!s.empty() && s.top() == target) {
      s.pop(); ans.push_back('-');
    }
    else { // pop을 못하는 상황은 비어있거나, 현재의 상단이 목표하는 숫자와 다를 떄, 순열은 이루어질 수 없음.
      cout << "NO\n";
      return;
    } 
  }

  for (char c : ans)  cout << c << '\n';

}


int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}