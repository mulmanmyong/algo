// 2063. 중간값 찾기

#include<iostream>
#include <algorithm>

using namespace std;

int main(int argc, char** argv)
{
	int n;
  cin >> n;
  vector<int> vec;
  for (int i = 0; i < n; i++) {
    int tmp;
    cin >> tmp;
    vec.push_back(tmp);
  }
  sort(vec.begin(), vec.end());

  cout << vec[n/2];
	return 0;
}