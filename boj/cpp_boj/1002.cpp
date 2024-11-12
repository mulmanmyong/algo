#include <iostream>
#include <cmath>

using namespace std;

int main()
{

    // 첫 째 줄에 테스트 케이스 수 T 입력
    int T;
    cin >> T;

    for (int i = 0; i < T; i++)
    {
        // x1 y1 r1 x2 y2 r2 순으로 6개 공백 입력
        int x1, y1, r1, x2, y2, r2;
        cin >> x1 >> y1 >> r1 >> x2 >> y2 >> r2;

        /*특정 a, b까지의 각각의 거리
        계산 식을 세워보니, 원이 나옴 => 두 원의 위치 관계
        두 점의 거리가 d이면,
        완전히 겹칠 경우 무한
        abs(r1 - r2) < d < r1 + r2 일 때 두 점에서 만남
        r1 + r2 = d && abs(r1-r2) = d이면 한 점에서 만남
        else -> 안만남 식으로 생각하면 abs(r1 - r2) > d 또는 d > r1 + r2 또는 d = 0 일 때 만나지 않을듯?
        */

        // 두 원의 중심 간 거리
        double d = sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
        double sub = abs(r1 - r2);
        double sum = r1 + r2;

        if (x1 == x2 && y1 == y2 && r1 == r2)
        {
            // 두 원이 완전히 같은 경우
            cout << -1 << endl;
        }
        else if (sub < d && d < sum)
        {
            // 두 점에서 만나는 경우
            cout << 2 << endl;
        }
        else if (d == sum || d == sub)
        {
            // 한 점에서 만나는 경우
            cout << 1 << endl;
        }
        else
        {
            // 교차하지 않는 경우
            cout << 0 << endl;
        }
    }

    return 0;
}
