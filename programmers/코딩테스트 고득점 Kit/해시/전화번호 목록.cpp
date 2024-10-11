#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool solution(vector<string> phone_book) {
    bool answer = true;
    
    sort(phone_book.begin(), phone_book.end()); // 기본적으로 오름차순
    for (int i = 0; i < phone_book.size() - 1; i++) {
        if (phone_book[i] == phone_book[i + 1].substr(0, phone_book[i].size())) {
            answer = false;
            break;
        }
    }
    return answer;
}

// 아래의 코드는 처음으로 푼 코드 -> 효율성이 좋지 못하고, 예외의 경우 처리를 못하는 것을 볼 수 있음 
// 자바에는 substr이 있었지만, c++에도 substr이 있음을 발견, 이러면 시간복잡도가 O(n^2)에서 O(n)으로 줄일 수 있음
// 그 결과가 위의 코드! 

/*
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool solution(vector<string> phone_book) {
    bool answer = true;
    
    sort(phone_book.begin(), phone_book.end()); // 기본적으로 오름차순
    for (int i = 0; i < phone_book.size() - 1; i++) {
        for (int j = i + 1; j < phone_book.size(); j++) {
            if (phone_book[j].find(phone_book[i]) == 0) { // 접두사인경우만 찾으니깐 젤 처음 위치를 찾았을 경우만
                answer = false;
                break;
            }
        }
    }
    return answer;
}
*/