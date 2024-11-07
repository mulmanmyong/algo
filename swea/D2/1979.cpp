// 1979. 어디에 단어가 들어갈 수 있을까
// 딱 K 길이만큼 일치해야 그 자리에 글자가 들어갈 수 있으며, 글자는 한 줄로 이루어져야함
// 그래서 그냥 반복문을 통해 가로로 글자입력 가능한 수와 새로로 글자입력 가능한 수를 나누어서 진행
// bfs로 복잡하게 구현할 필요는 없었음
// 소요시간 : 15분

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
        int N, K;
        cin >> N >> K;

        int arr[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cin >> arr[i][j]; // 흰색 부분이 1, 검은색 부분이 0 -> 흰색 부분에 단어입력
            }
        }

        int answer = 0;
        // 가로
        for (int i = 0; i < N; i++) {
            int cnt = 0;
            int j = 0;

            while (1) {
                
                if (arr[i][j] == 0) {
                    if (cnt == K) {
                        answer++;
                    }
                    cnt = 0;
                    j++;
                }
                else {
                    cnt++;
                    j++;
                }

                if (j == N) {
                    if (cnt == K) {
                        answer++;
                    }
                    break;
                }

            }
        }

        // 세로
        for (int i = 0; i < N; i++) {
            int cnt = 0;
            int j = 0;

            while (1) {
                
                if (arr[j][i] == 0) { 
                    if (cnt == K) {
                        answer++;
                    }
                    cnt = 0;
                    j++;
                }
                else { 
                    cnt++;
                    j++;
                }

                if (j == N) {
                    if (cnt == K) {
                        answer++;
                    }
                    break;
                }

            }
        }
        
        cout << "#" << test_case << " " << answer << endl;

	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}
