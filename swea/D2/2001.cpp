// 2001. 파리 퇴치
// 그냥 반복문 만을 이용해서 구현을 했음
// 하지만 인덱스를 최대 인덱스까지 돌 수 있게 안해서, 몇가지 조건을 만족하지 못해서 1차 제출은 틀렸었음.
// 그거 제외하곤 난이도는 쉬운 편
// 소요시간 : 10분

// 누적합 알고리즘이라는 것이 존재한다고 함
// 지금과 같은 상황에서는 필요가 없을 수도 있지만, 일단은 알고리즘 학습 차원에서 구현을 해봤음

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
        int N, M;
        cin >> N >> M;

        // 누적합 알고리즘 방식
        // N의 최대는 15이기에 16의 사이즈로 미리 생성해두기
        int pre_sum[16][16] = {0}; // 미리 생성 후 초기화
        int num;

        // 누적합 배열 생성
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                cin >> num;
                pre_sum[i][j] = num + pre_sum[i-1][j] + pre_sum[i][j-1] - pre_sum[i-1][j-1];
            }
        }

        int ans = 0, tmp;
        for (int i = M; i <= N; i++) {
            for (int j = M; j <= N; j++) {
                // M*M 의 최대합을 구하기
                tmp = pre_sum[i][j] - pre_sum[i-M][j] - pre_sum[i][j-M] + pre_sum[i-M][j-M]; 
                /*
                1 2 3 
                4 5 6 
                7 8 9

                에서 
                M = 2 이라고 하면
                5 6
                8 9 와 같은 크기의 합을 구하고 자 함
                그럼 이는 9의 위치까지의 합에서 1 2 3 6 7 을 빼면 되는 것인데, 
                이는 7까지의 합을 빼고 3까지의 합을 뺴고, 2중으로 빠진 것을 채워주기 위해 1까지의 합을 더해주면 된다. M크기만큼의 합이니깐 현위치에서 M씩 뺴주면 됨
                */
               if (ans < tmp) {
                ans = tmp;
               }
            }
        }

        cout << "#" << test_case << " " << ans << endl;
        

        // 4중 for문 방식
        /*

        int arr[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cin >> arr[i][j];
            }
        }

        int max_sum = 0;
        // 완전 탐색 -> 가중치의 최대값을 업데이트를 하면서 진행하기
        for (int i = 0; i < N - M + 1; i++) {
            for (int j = 0; j < N - M + 1; j++) {
                
                int sum = 0;
                for (int k = i; k < i + M; k++) {
                    for (int l = j; l < j + M; l++) {
                        sum += arr[k][l];

                        //cout << "index " << i << " " << j << " " << k << " " << l << endl;
                    }
                }
                // cout << endl;

                if (max_sum < sum) {
                    max_sum = sum;
                }

                // cout << "index " << i << " " << j << endl;
            }
        }

        cout << "#" << test_case << " " << max_sum << endl; 
        */

	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}
