// 1948. 날짜 계산기
// 단순 구현
// 소요시간 : 10분

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

#include<iostream>

using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;
	cin>>T;

    int days[13] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};

	for(test_case = 1; test_case <= T; ++test_case)
	{ 
        // 두번째 날짜가 첫번째 날짜의 며칠쨰인지?
        int fm, fd, sm, sd, d = 0;
        cin >> fm >> fd >> sm >> sd;

        // 첫번째와 두번째가 같은 달이면
        if (fm == sm) {
            d = days[fm] - fd + 1;
            cout << "#" << test_case << " " << d << endl;
            continue;
        }

        // 두 번째 날짜가 첫 번째 날짜보다 항상 크게 주어진다.
        d = days[fm] - fd + 1;
        for (int i = fm + 1; i <= sm; i++) {
            d += ((i == sm) ? sd : days[i]);
        }
        cout << "#" << test_case << " " << d << endl;

	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}