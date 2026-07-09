#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

bool check(vector<string>& want, unordered_map<string, int>& wants, unordered_map<string, int>& cart) {
    for (int i = 0; i < want.size(); i++) {
        if (wants[want[i]] != cart[want[i]]) {
            return false;
        }
    }
    
    return true;
}

int solution(vector<string> want, vector<int> number, vector<string> discount) {
    // 일정한 금액 지불하면 10일 동안 회원 자격 부여
    // 회원을 대상으로 매일 한 가지 제품 할인하는 행사 진행
    // 그래서 자신이 원하는 제품과 수량이 할인하는 날짜와 10일 연속으로 일치할 경우에 맞춰서 회원가입
    
    // 슬라이딩 윈도우로 할 수 있지 않을까?
    // 먼저 10일치 물품에 대해서 unordered_map으로 저장을 하고, key를 기준으로 넣고 빼고
    // 원하는 물품의 개수가 모두 맞다면 answer + 1!
    
    // 그럼 먼제 want와 number에 대한 정답 map 생성
    unordered_map<string, int> wants;
    for (int i = 0; i < want.size(); i++) {
        wants[want[i]] = number[i];
    }
    
    // 슬라이딩 윈도우를 위한 첫번째 구간 (10일 연속이니 10개 담기)
    unordered_map<string, int> cart;
    for (int i = 0; i < 10; i++) {
        cart[discount[i]]++;
    }
    
    // 연속 확인
    int answer = 0;
    for (int i = 0; i <= discount.size() - 10; i++) {
        // 개수 확인
        if (check(want, wants, cart)) {
            answer++;
        }
        
        // 제일 뒤는 인덱스를 벗어나게 되므로 중단 로직
        if (i == discount.size() - 10) {
            break;
        }
        
        cart[discount[i]]--; // 젤 앞에거 제거
        cart[discount[i + 10]]++; // 젤 뒤에 추가
    }
    
    return answer;
}