#include <string>
#include <vector>
#include <sstream>

using namespace std;

vector<int> solution(string s) {
    vector<int> answer;
    
    // string을 각 자리수를 보면서 0의 개수를 count, 그리고 0 제거 후 길이는 1의 개수, 즉 1의 개수를 다음 이진 변환
    // s가 1이 될 때까지 반복
    int changeCount = 0;
    int zeroCount = 0;
    
    while (s != "1") {
        int oneCount = 0;
        for (char c : s) {
            if (c == '0') {
                zeroCount++;
            }
            else {
                oneCount++;
            }
        }
        
        // oneCount를 다름 이진 string으로 변환
        s = "";
        while (oneCount > 0) {
            s = char(oneCount % 2 + '0') + s;
            oneCount /= 2;
        }
        
        // 변환 Count 증가
        changeCount++;
    }
    
    // {변환 횟수, 제거한 0의 개수}
    answer = {changeCount, zeroCount};
    return answer;
}