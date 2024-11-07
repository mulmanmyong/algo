// 1940. 가랏! RC카!
// 단순 구현 문제
// 소요시간 : 5분

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

		int N, move = 0, cur_speed = 0;
		cin >> N;

		int cmd, speed;
		for (int i = 0; i < N; i++) {
			cin >> cmd;

			if (cmd == 1) {
				cin >> speed;
				cur_speed += speed;
			}
			else if (cmd == 2) {
				cin >> speed;
				cur_speed -= speed;

				if (cur_speed <= 0) { // 현재 속도보다 감속하려는 속도가 더 크면 속도는 0
					cur_speed = 0;
				}
			}

			move += cur_speed; // m/s이고 1초에 한번 속도 커맨들가 들어오기 때문에, 현재속도가 이동거리
		}

		cout << "#" << test_case << " " << move << endl;		     

	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}