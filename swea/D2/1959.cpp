// 1959. 두 개의 숫자열
// 단순 비교후 곱셈인데, 멍청하게 중복내용 복붙 잘못해서 이상한 출력떠서 고민함 -> 결론 오타
// 소요시간 : 20분 

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

using namespace std;

int main(int argc, char** argv)
{
	int test_case;
	int T;
	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{
        int N, M, input;
        cin >> N >> M;

        vector<int> A;
        for (int i = 0; i < N; i++) {
            cin >> input;
            A.push_back(input);
        }

        vector<int> B;
        for (int i = 0; i < M; i++) {
            cin >> input;
            B.push_back(input);
        }

        int max_sum = -1234567890, sum;
        if (N == M) {
            sum = 0;
            for (int i = 0; i < N; i++) {
                sum += (A[i]*B[i]);
            }
            cout << "#" << test_case << " " << sum << endl;
            break;
        }
        else {
            if (N < M) {
                for (int i = 0; i <= M - N; i++) {
                    sum = 0;
                    for (int j = 0; j < N; j++) {
                        sum += (A[j]*B[j+i]);
                    }
                    if (max_sum <= sum) {
                        max_sum = sum;
                    }
                }
            }
            else {
                for (int i = 0; i <= N - M; i++) {
                    sum = 0;
                    for (int j = 0; j < M; j++) {
                        sum += (B[j]*A[j+i]);
                    }
                    if (max_sum <= sum) {
                        max_sum = sum;
                    }
                }
            }
        }
        cout << "#" << test_case << " " << max_sum << endl;

	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}