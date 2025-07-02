// 1018. 체스판 다시 칠하기 -> 실버 4

#include <bits/stdc++.h>

using namespace std;

char board[51][51];

int count(int a, int b) {
    int wstart=0, bstart=0;
    for (int i = a; i < a+8; i++) {
        for (int j = b; j < b+8; j++) {
            if ((i+j)%2 == 0) {
                if (board[i][j] == 'B') wstart++;
                if (board[i][j] == 'W') bstart++;
            }
            else {
                if (board[i][j] == 'W') wstart++;
                if (board[i][j] == 'B') bstart++;
            }
        }
    }

    return min(wstart, bstart);
}

void func1(void) {
    int n, m, ans=65;
    cin >> n >> m;
    for (int i = 0; i < n; i++) {
        string s;
        cin >> s;
        for (int j = 0; j < m; j++) {
            board[i][j] = s[j];
        }
    }

    for (int i = 0; i < n-7; i++) {
        for (int j = 0; j < m-7; j++) {
            
            ans = min(ans, count(i, j));

        }
    }

    cout << ans << '\n';
}

int main(void) {
    ios::sync_with_stdio(0); cin.tie(0);
    func1();
    return 0;
} 