#include <iostream>
using namespace std;

int solution(int n)
{
    int ans = 0;
    
    // K칸 앞으로 점프하거나, (현재까지 온 거리)*2만큼 순간이동
    // K칸 이동은 건전지 K만큼 소모, 순간이동은 소모 안함
    
    // 건전지 소모를 최소화 하기 위해 점프를 최소화
    // N만큼 이동하려 할 때 건전지 사용량의 최솟값을 return
    
    // 그러면 역으로 N이 0이 될때까지 2로 나누고,
    // 나누어지지 않으면 -1하고, ans +1하면 될 듯?
    
    while (n > 0) {
        // 나누어 떨어지면 2로 나눔
        if (n % 2 == 0) {
            n /= 2;
        }
        // 그렇지 않으면 -1을 빼고, ans+1로 점프를 함
        else {
            n -= 1;
            ans += 1;
        }
    }

    return ans;
}