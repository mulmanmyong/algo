// 5430. AC -> 골드 5

#include <bits/stdc++.h>

using namespace std;

void func1(void) {
  int t;
  cin >> t;
  
  while (t--) {
    string cmd;
    int n, num=0;
    bool reverse = false;
    bool isError = false;
    string arr;
    deque<int> dq;

    cin >> cmd;
    cin >> n;
    cin >> arr;
    if (n != 0) {
      for (int i = 1; i < arr.length()-1; i++) {
        if (arr[i] == ',') {
          dq.push_back(num); // 쉼표를 기준으로 숫자 push
          num=0;
        }
        else {
          num = num*10 + (arr[i]-'0'); // 10의 자리가 나올 수 있으므로 숫자 만들기
        }
      }
      if (num != 0) dq.push_back(num); // 마지막 숫자 push
    }
    for (char c : cmd) {
      if (c == 'R') reverse = !reverse;
      else if (c == 'D' && dq.empty()) {
        isError = true;
        break;
      }
      else if (c == 'D' && !dq.empty()){
        if(reverse) dq.pop_back();
        else  dq.pop_front();
      }
    }

    if (isError)  cout << "error\n";
    else {
      cout << '[';
      while (!dq.empty()) {
        if (reverse) {
          cout << dq.back();
          dq.pop_back();
          if (!dq.empty())  cout << ',';
        }
        else {
          cout << dq.front();
          dq.pop_front();
          if (!dq.empty())  cout << ',';
        }
      }
      cout << "]\n";
    }
  }
}


int main(void)
{
  ios::sync_with_stdio(0);
  cin.tie(0);
  func1();
  return 0;
}