// 1859. 백만 장자 프로젝트

/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// float b, c;
// double d, e, f;
// char g;
// char var[256];
// long long AB;
// cin >> a;                            // int 변수 1개 입력받는 예제
// cin >> b >> c;                       // float 변수 2개 입력받는 예제
// cin >> d >> e >> f;                  // double 변수 3개 입력받는 예제
// cin >> g;                            // char 변수 1개 입력받는 예제
// cin >> var;                          // 문자열 1개 입력받는 예제
// cin >> AB;                           // long long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;
// float b = 1.0, c = 2.0;
// double d = 3.0, e = 0.0; f = 1.0;
// char g = 'b';
// char var[256] = "ABCDEFG";
// long long AB = 12345678901234567L;
// cout << a;                           // int 변수 1개 출력하는 예제
// cout << b << " " << c;               // float 변수 2개 출력하는 예제
// cout << d << " " << e << " " << f;   // double 변수 3개 출력하는 예제
// cout << g;                           // char 변수 1개 출력하는 예제
// cout << var;                         // 문자열 1개 출력하는 예제
// cout << AB;                          // long long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////

#include <iostream>

int nums[1000001] = {0,}; // 배열 초기화 

using namespace std;

int main(int argc, char **argv)
{
    int test_case;
    int T;
    /*
       아래의 freopen 함수는 input.txt 를 read only 형식으로 연 후,
       앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
       //여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
       freopen 함수를 이용하면 이후 cin 을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
       따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 함수를 사용하셔도 좋습니다.
       freopen 함수를 사용하기 위해서는 #include <cstdio>, 혹은 #include <stdio.h> 가 필요합니다.
       단, 채점을 위해 코드를 제출하실 때에는 반드시 freopen 함수를 지우거나 주석 처리 하셔야 합니다.
    */
    // freopen("input.txt", "r", stdin);
    cin >> T;
    /*
       여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
    */
    for (test_case = 1; test_case <= T; ++test_case)
    {

        // int 최대 범위 2,147,483,647
        // 하루 최대 판매값, 10000, 최대가격 최대 구매 가능날 999999 -> int 범위 벗어남 최대로 잡기 long long
        long long sum = 0;

        int N;
        cin >> N;
        for (int i = 0; i < N; i++)
        {
            cin >> nums[i];
        }

        int max_price = nums[N - 1];

        // 미리 입력받기
        // 마지막 날은 무조건 파는 것이니 마지날 부터 시작
        // 그렇다면 마지막 날은 최대 값으로 잡고, 이 값보다 작거나 같으면 판매, 크면 맥스값을 갈아치우는 형식으로 진행

        for (int i = N - 1; i >= 0; i--)
        {
            if (max_price >= nums[i])
            {
                sum += (max_price - nums[i]); // 최대값에서 팔기 때문에 최대값에서 구매한 값을 뺀 순이익
            }
            else
            {
                max_price = nums[i];
            }
        }

        cout << "#" << test_case << " " << sum << endl;

        for (int i = 0; i < N; i++)
        {
            nums[i] = 0; // 입력 했던 배열 초기화 
        }
    }

    return 0; // 정상종료시 반드시 0을 리턴해야합니다.
}