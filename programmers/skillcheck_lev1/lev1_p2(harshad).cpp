#include <string>
#include <vector>

using namespace std;

bool solution(int x)
{
    int origin = x;
    int sum = 0;

    // 모든 자릿수를 더하는 과정
    while (x > 0)
    {
        sum += x % 10;
        x /= 10;
    }

    // sum이 0이 아닐 때만 나눗셈을 수행
    if (sum != 0 && origin % sum == 0)
    {
        return true;
    }

    return false;
}
