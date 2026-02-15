#include <iostream>
#include <cmath>

using namespace std;

int main() {

  ios::sync_with_stdio(0); cin.tie(0);
  int t, x;
  
  cin >> t;
  while (t-- > 0) {
    cin >> x;

    if (abs(x) % 2 == 0) cout << x << " is even\n";
    else            cout << x << " is odd\n";
  }

  return 0;
}