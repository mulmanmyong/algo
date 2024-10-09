#include <string>
#include <vector>

using namespace std;

int solution(int num)
{
    int cnt = 0;
    long renum = num; // int의 범위를 넘어난 연산을 하여서 long으로 재선언
    // solution 부분에서 할 시에는 체점하는 main함수에서 solution(int)를 호출하기에,
    // 함수부분에서는 변경 불가능하고, 재선언을 함

    while (renum != 1)
    {
        if (cnt == 500)
        {
            return -1;
        }
        if (renum % 2 == 0)
        {
            renum /= 2; // 짝수일 경우
        }
        else
        {
            renum = 3 * renum + 1; // 홀수일 경우
        }
        cnt++;
    }

    return cnt;
}
