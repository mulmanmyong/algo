// 1110. 더하기 사이클 -> 브론즈 1
#include <bits/stdc++.h>

using namespace std;

void func1(void) {
	int n, cnt=0;
	cin >> n;
	int init_num = n;

	while (1) {
		int a = n / 10;
		int b = n % 10;
		n = (b * 10) + ((a + b) % 10);
		cnt++;
		if (init_num == n)	break;
	}

	cout << cnt << '\n';

}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);
	func1();
	return 0;
}