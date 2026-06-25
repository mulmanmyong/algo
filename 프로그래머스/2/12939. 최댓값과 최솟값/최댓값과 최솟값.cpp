#include <string>
#include <vector>
#include <sstream>
#include <algorithm>

using namespace std;

string solution(string s) {
    string answer = "";

    // 문자열을 입력 스트림처럼 사용할 수 있도록 stringstream 생성
    stringstream ss(s);

    vector<int> vec;
    int num;

    // 문자열 끝까지 숫자를 하나씩 읽어서 벡터에 저장
    while (ss >> num) {
        vec.push_back(num);
    }

    // 오름차순 정렬
    sort(vec.begin(), vec.end());

    // 가장 앞 = 최소값, 가장 뒤 = 최대값
    answer = to_string(vec.front()) + " " + to_string(vec.back());

    return answer;
}