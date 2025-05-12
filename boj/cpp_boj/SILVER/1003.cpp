// 1003. 피보나치 함수 -> 실버 3

#include <iostream>

using namespace std;

pair<int, int> fibonacci(int N);

long long cnt0[41];
long long cnt1[41];

int main()
{

    int T;
    cin >> T;

    for (int i = 0; i < T; i++)
    {
        int N;
        cin >> N;

        fibonacci(N);
        cout << cnt0[N] << " " << cnt1[N] << endl;
    }

    return 0;
}

pair<int, int> fibonacci(int n)
{

    /*
    생각을 해보면
    0 -> 1 0
    1 -> 0 1
    2 -> 1 1 여기부터 n-1 이랑 n-2랑 더함 -> cnt0이랑 cnt1 배열 따로 만들 어 봐야할듯
    3 -> 1 2
    */

    cnt0[0] = 1;
    cnt1[0] = 0;
    cnt0[1] = 0;
    cnt1[1] = 1;

    if (n == 0)
    {
        return {cnt0[0], cnt1[0]};
    }
    else if (n == 1)
    {
        return {cnt0[1], cnt1[1]};
    }

    if (cnt0[n] != 0 && cnt1[n] != 0) // 여기 오타났었음 cnt1[0]이라고..
    {
        // 둘 다 0이 아니면
        return {cnt0[n], cnt1[n]};
    }

    return {cnt0[n] = fibonacci(n - 1).first + fibonacci(n - 2).first, cnt1[n] = fibonacci(n - 1).second + fibonacci(n - 2).second};
}