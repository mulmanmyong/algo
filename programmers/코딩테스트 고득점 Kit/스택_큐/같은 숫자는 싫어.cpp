#include <vector>
#include <iostream>

using namespace std;

vector<int> solution(vector<int> arr) 
{
    vector<int> answer;
    int index = 0;
    
    answer.push_back(arr[0]); // 초기값 설정
    for (int i = 1; i < arr.size(); i++) {
        // 연속된 값은 넣지 않으니 현재 있는 값과 같으면 pass
        if (answer[index] == arr[i]) { 
            continue;
        }
        // 연속되지 않으면 입력하고 check할 index를 증가시킴
        else {
            answer.push_back(arr[i]);
            index++;
        }
    }
    
    return answer;
}