// 1486. 장훈이의 높은 선반
#include <iostream>

using namespace std;

int n, b, ans;
int height[10001];

void tower(int index, int towerHeight) {
  if (index==n) {
    if (b<=towerHeight) {
      ans = min(ans, towerHeight-b);
    }
  }
  else if (index < n) {
    tower(index+1, towerHeight+height[index]); // 현재의 높이를 더하는 경우
    tower(index+1, towerHeight); // 현재의 높이를 더하지 않는 경우      
  }
}

int main(int argc, char** argv)
{
	int test_case;
	int T;
	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
    cin >> n >> b;

    for (int i = 0; i < n; i++) {
      cin >> height[i];
    }

    // 현재 높이의 탑을 쌓는 경우와 안 쌓는 경우가 있어야 함.
    // 끝까지 도달했을 때, B이상일 경우 탑의 높이와 B의 차이가 최소인 것으로 update
    ans=200001;
    tower(0, 0); // index, towerHeight

    cout << '#' << test_case << ' ' << ans << '\n';
	}

	return 0;
}