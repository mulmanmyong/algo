#include <iostream>
using namespace std;
int main() {
  int n;
  cin >> n;
  string str;
  while (n --> 0) {
    cin >> str;
    int strLen = str.length();
    if (6 <= strLen && strLen <= 9) {
      cout << "yes\n";
    }
    else {
      cout << "no\n";
    }
  }
  return 0;
}