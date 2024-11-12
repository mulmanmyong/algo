#include <iostream>
#include <cmath>

using namespace std;

int main()
{

    int T;
    cin >> T;

    for (int i = 0; i < T; i++)
    {
        int x1, y1, x2, y2;
        cin >> x1 >> y1 >> x2 >> y2;

        int n;
        cin >> n;

        int cx, cy, r, cnt = 0;
        for (int j = 0; j < n; j++)
        {
            cin >> cx >> cy >> r;

            // 제곱근을 하면 소수점이 나올 수 있는데 int로 하면 소수점을 무시 해버려서 정확한 계산이 안됨. 그 점 주의 할 것!!!
            double d1 = sqrt(pow(x1 - cx, 2) + pow(y1 - cy, 2));
            double d2 = sqrt(pow(x2 - cx, 2) + pow(y2 - cy, 2));

            // 최소 진입/이탈 횟수 => 좌표1이 원 안에 있고, 좌표2가 원 밖에 있으면 진입/이탈 발생, 좌표1이 원 밖에 있고, 좌표2가 원 안에 있으면 진입/이탈 발생
            if ((d1 < r && d2 > r) || (d1 > r && d2 < r))
            {
                cnt++;
            }
        }
        cout << cnt << endl;
    }

    return 0;
}