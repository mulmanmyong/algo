// 1928. Base64 Decoder
// 비트 연산자의 사용을 익히는 문제
// 비트 연산자에 좀 약해서 이것저것 여러 방식으로 접근을 해봤지만
// 제일 쉬운 방식을 검색한 끝에 비트 연산자만을 사용하면 된다는 것을 알게됨
// 그래서 비트연산자를 이해 후 적용
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

#include <iostream>

using namespace std;

// decoding을 위함
char decoding(char temp) {
    if(temp >= 'A' & temp <= 'Z') {
        temp = temp - 'A';          // A-Z 는 0~25에 대응
    } else if(temp >= 'a' & temp <= 'z') {
        temp = temp - 'a' + 26;     // a-z 는 26~51에 대응
    } else if(temp >= '0' & temp <= '9') {
        temp = temp - '0' + 52;     // 0-9 는 52~61에 대응
    } else if(temp == '+') {
        temp = 62;                  // '+' 는 62에 대응
    } else {
        temp = 63;                  // '/'는 63에 대응 (남은거긴함)
    }

    return temp;
}

int main(int argc, char** argv)
{
	int test_case;
	int T;
	cin>>T;

	for(test_case = 1; test_case <= T; ++test_case)
	{

        string input, output = "";
        cin >> input;

        // 4글자씩 처리 -> 6비트 4개 -> 24비트
        // 24비트 -> 8비트씩 3개의 바이트로 변환
        for (int i = 0; i < input.length(); i+= 4) {
            // 4개의 글자처리
            char tmp1 = decoding(input[i]);
            char tmp2 = decoding(input[i + 1]);
            char tmp3 = decoding(input[i + 2]);
            char tmp4 = decoding(input[i + 3]);

            // 3개의 바이트로 변환
            
            // 첫번째 바이트
            // 6비트 구성이니 -> 첫번째 문자 왼쪽으로 2비트 이동해서 8비트 중 상위 6개의 비트를 구성
            // 두번째 문자 오른쪽 4비트를 이동해서 하위 2개의 비트를 구성
            output += (tmp1 << 2) | (tmp2 >> 4);

            // 두번째 바이트
            // 두번째 문자는 첫번째 바이트에서 상위 2비트를 사용, 즉 왼쪽으로 4비트를 이동하면 8비트 중 하위 4비트 사용가능하고 이는 두번째 바이트의 상위 4비트를 구성
            // 세번째 문자에서 오른쪽으로 2비트를 이동해서 하위 4개의 비트를 구성
            output += (tmp2 << 4) | (tmp3 >> 2);

            // 세번째 바이트
            // 왼쪽으로 6비트 이동하면 사용하지않은 하위 2비트가 세번째 바이트의 8비트중 상위 2비트로 구성된다.
            // 그 후 나머지 6비트를 세번째 바이트의 하위 6비트를 구성
            output += (tmp3 << 6) | tmp4;

        }

        cout << "#" << test_case << " " << output << endl;

	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}