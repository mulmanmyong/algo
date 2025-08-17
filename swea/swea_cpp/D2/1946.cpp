// 1946. 간단한 압축 풀기
// 원본문서 너비 10으로 고정, 그냥 입력받을 때 개수 잘 파악하기만 하면 순서대로 출력하면됨
// 근데 문제 이해하다가 좀 걸림
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
#include <vector>
#include <algorithm>

using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;
	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{

        int N, total = 0;
        cin >> N;

        vector< pair<char, int> > vec;
        for (int i = 0; i < N; i++) {
            char c;
            int n;
            cin >> c >> n;
            vec.push_back(make_pair(c, n));
        }

        int cnt = 0;
        cout << "#" << test_case << endl;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < vec[i].second; j++) {
                if (cnt == 10)  {
                    cout << endl;
                    cnt = 0;
                }
                cout << vec[i].first;
                cnt++;
            }
        }
        cout << endl;

	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}