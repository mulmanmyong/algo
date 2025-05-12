// 11723. 집합 -> 실버 5

#include <iostream>
#include <cstring>

using namespace std;

int main()
{

    ios_base::sync_with_stdio(0);
    cin.tie(0);

    // S에 있으면 넣고, 없으면 넣지 말라 1 ~ 20
    // 출력방식도 있으면 1 없으면 0을 출력하는 것
    // 그러면 0으로 초기화 하고, 있으면 1이 되게 하면 된다.

    int S[21] = {0};
    int M;
    cin >> M;

    string str = "";
    int x;
    for (int i = 0; i < M; i++)
    {

        cin >> str;
        if (str == "add")
        {
            cin >> x;
            if (S[x] == 0)
            {
                S[x] = 1;
            }
        }
        else if (str == "remove")
        {
            cin >> x;
            if (S[x] == 1)
            {
                S[x] = 0;
            }
        }
        else if (str == "check")
        {
            cin >> x;
            if (S[x] == 1)
            {
                cout << "1\n";
            }
            else
            {
                cout << "0\n";
            }
        }
        else if (str == "toggle")
        {
            cin >> x;
            if (S[x] == 1)
            {
                S[x] = 0;
            }
            else
            {
                S[x] = 1;
            }
        }
        else if (str == "all")
        {
            for (int j = 1; j <= 20; j++)
            {
                S[j] = 1;
            }
        }
        else if (str == "empty")
        {
            memset(S, 0, sizeof(S));
        }
    }

    return 0;
}