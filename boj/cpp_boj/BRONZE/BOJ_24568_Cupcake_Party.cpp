#include <iostream>

using namespace std;

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int r, s;
  cin >> r >> s;

  int answer = r*8 + s*3 - 28;
  if (answer < 0) answer = 0;
  cout << answer;

  return 0;
}