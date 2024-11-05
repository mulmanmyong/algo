// 2007. 패턴 마디의 길이
// substr을 이용하여 간단하게 구현함 -> 하지만 걱정이 있다면 두글자 연속 똑같은 걸로 시작하는 단어가 있어버리면 문제가 생길 거 같음
// 하지만 내 머리속에 그런 단어가 떠오르진 않음
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

	for(test_case = 1; test_case <= T; ++test_case)
	{

        string str, result;
        cin >> str;

        for (int i = 1; i < 30; i++) {
            //cout << i << " " << str.substr(0, i) << " " << str.substr(i, i) << endl;
            if (str.substr(0, i) == str.substr(i, i)) {
                result = str.substr(0,i);
                break;
            }
        }

        cout << "#" << test_case << " " << result.length() << endl;

	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}