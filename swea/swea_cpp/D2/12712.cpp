// 12712. 파리퇴치3

#include<iostream>

using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;
	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
    int n, m;
    cin >> n >> m;
    
    int arr[16][16];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        cin >> arr[i][j];
      }
    }

    int dx[4] = {0, 1, 0, -1}; 
    int dy[4] = {1, 0, -1, 0};

    int di[4] = {1, -1, 1, -1};
    int dj[4] = {1, -1, -1, 1};

    int ans=0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        int cnt1 = arr[i][j];
        int cnt2 = arr[i][j];
        for(int k = 0; k < 4; k++) {
          for (int l = 1; l < m; l++) {
            int a = i + dx[k]*l;
            int b = j + dy[k]*l;

            int c = i + di[k]*l;
            int d = j + dj[k]*l;

            if (0 <= a && a < n && 0 <= b && b < n) {
              cnt1 += arr[a][b];
            }
            if (0 <= c && c < n && 0 <= d && d < n) {
              cnt2 += arr[c][d];
            }
          }
        }
        ans = max(ans, max(cnt1, cnt2));
      }
    }
    cout << '#' << test_case << ' ' << ans << '\n';

	}
	return 0;
}