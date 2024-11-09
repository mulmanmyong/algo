// 1983. 조교의 성적 매기기
// 성적 순 나열 후, 그 인원수의 step에 맞게 확인
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
#include <algorithm>

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

        vector<float> vec;
        string grade[10] = {"A+", "A0", "A-", "B+", "B0", "B-", "C+", "C0", "C-", "D0"};
        float k_score;
        for (int i = 1; i <= N; i++) {
            int mid, fin, hw;
            cin >> mid >> fin >> hw;
            float score = (mid*0.35) + (fin*0.45) + (hw*0.2);
            if (i == K) {
                k_score = score;
            }
            vec.push_back(score);
        }
        sort(vec.rbegin(), vec.rend()); // 내림 차순

        int index = find(vec.begin(), vec.end(), k_score) - vec.begin() + 1; // index는 0번째 부터 시작
        int step = N / 10;
        int j = 0;
        for (int i = step; i <= N; i += step) {
            if (index <= i) {
                cout << "#" << test_case << " " << grade[j] << endl;
                break;
            }
            else {
                j++;
            }
        }

	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}