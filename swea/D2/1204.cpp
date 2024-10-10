// 1204. [S/W 문제해결 기본] 1일차 - 최빈수 구하기

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
#include<algorithm>
#include<vector>
#include<map>

using namespace std;

bool cmp(const pair<int,int>& a, const pair<int,int>& b);

int main(int argc, char** argv)
{
	int test_case;
	int T;
	/*
	   아래의 freopen 함수는 input.txt 를 read only 형식으로 연 후,
	   앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
	   //여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
	   freopen 함수를 이용하면 이후 cin 을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
	   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 함수를 사용하셔도 좋습니다.
	   freopen 함수를 사용하기 위해서는 #include <cstdio>, 혹은 #include <stdio.h> 가 필요합니다.
	   단, 채점을 위해 코드를 제출하실 때에는 반드시 freopen 함수를 지우거나 주석 처리 하셔야 합니다.
	*/
	//freopen("input.txt", "r", stdin);
	cin>>T;
	/*
	   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
	*/
	for(test_case = 1; test_case <= T; ++test_case)
	{
        int tmp;
        cin >> tmp;
        
        // 최빈값 찾기
        // 점수의 범위는 0이상 100이하 
        // key와 value를 가지는 map을 이용
        map<int, int> mp; // 데이터 타입이 int인 key와 int이 value를 가지는 map을 생성
        int num;
        for (int i = 0; i < 1000; i++){
            cin >> num;
            mp[num] += 1; // map의 특성, 만약 num을 key값으로 가지는 데이터가 없다면, {num,0} 을 삽입하고, 존재한다면 기존 value값에 1을 더한다.
        }

        // 입력했으면 value 기준 정렬
        // c++에서는 map을 value기준으로 정렬하려면 우선 vector로 변환을 해줘야함
        // 그 후 vector의 second기준으로 정렬
        vector< pair<int, int> > vec(mp.begin(), mp.end());
        sort(vec.begin(), vec.end(), cmp);

        cout << "#" << test_case << " " << vec[0].first << endl; // 첫 번째 원소의 key값 출력 (최빈값 중 가장 큰 점수)


	}
	return 0;//정상종료시 반드시 0을 리턴해야합니다.
}

// 미리 정의해주는 함수 -> value(빈도) 기준 정렬, 빈도가 같을 경우 key(점수) 기준 내림차순 정렬
bool cmp(const pair<int,int>& a, const pair<int,int>& b) {
	if (a.second == b.second) return a.first > b.first; // 빈도 같으면 큰 점수 우선
	return a.second > b.second; // 빈도 높은 순으로 정렬
}