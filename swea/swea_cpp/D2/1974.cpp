// 1974. 스도쿠 검증
// 스도쿠 풀이를 그냥 구현
// 가로 줄 판단
// 세로 줄 판단
// 3*3 박스 판단
// 노가다 구현이라 생각보다 오래 걸렸음
// 소요시간 : 30분

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

        int sudoku[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                cin >> sudoku[i][j];
            }
        }


        int num, esc = 1;
        for (int i = 0; i < 9; i++) {
            if (esc == 0) {
                break;
            }
            int check[10] = {0, };
            for (int j = 0; j < 9; j++) {
                if (check[sudoku[i][j]] == 0) {
                    check[sudoku[i][j]]++;
                }
                else {
                    esc = 0;
                    break;
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            if (esc == 0) {
                break;
            }
            int check[10] = {0, };
            for (int j = 0; j < 9; j++) {
                if (check[sudoku[j][i]] == 0) {
                    check[sudoku[j][i]]++;
                }
                else {
                    esc = 0;
                    break;
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            if (esc == 0) {
                break;
            }
            for (int j = 0; j < 3; j++) {
                int check[10] = {0, };

                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        if (check[sudoku[3*i + k][3*j + l]] == 0) {
                            check[sudoku[3*i + k][3*j + l]]++;
                        }
                        else {
                            esc = 0;
                            break;
                        }
                    }
                }
            }
        }

        cout << "#" << test_case << " " << esc << endl;

	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}