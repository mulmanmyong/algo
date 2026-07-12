#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int countTopping(int toppings, vector<int>& topping) {
    int count = 0;
    for (int i = 1; i <= toppings; i++) {
        if (topping[i] > 0) {
            count++;
        }
    }
    
    return count;
} 

int solution(vector<int> topping) {
    // 롤케이크 두 조각으로 잘라서 동생과 한 조각씩 나눠 먹을 것임
    // 크기는 신경안쓰고, 올려진 토핑들의 종류에 더 관심이 많음
    // 동일한 가짓수의 토핑이 올라가면 공평하게 롤케이크가 나눈 것으로 판단
    
    // 2개의 토핑확인용 배열로 체크
    int toppings = *max_element(topping.begin(), topping.end());
    int answer = 0;
    vector<int> left(toppings + 1);
    vector<int> right(toppings + 1);
    
    // 종류도 변수로 체크, 옮길때마다 체크하니 예상대로 시간초과 발생
    int leftKinds = 1; // 초기에는 하나니깐
    int rightKinds = 0;
    // 초기 케이크 절단
    left[topping[0]]++;
    for (int i = 1; i < topping.size(); i++) {
        // 증가하기전에 0이라면 새로운 친구
        if (right[topping[i]] == 0) {
            rightKinds++;
        }
        right[topping[i]]++;
    }
    
    // 케이크 자르기 진행
    for (int i = 1; i < topping.size(); i++) {
        // 먼저 공평하게 잘랐는지 확인
        if (leftKinds == rightKinds) {
            answer++;
        }
        
        // 다시 나누기
        
        // 왼쪽은 새롭게 넣는 것이니깐 기존에 0이라면 새롭게 들어오는 거니 개수 추가
        if (left[topping[i]] == 0) {
            leftKinds++;
        }
        left[topping[i]]++;
        
        // 오른쪽은 기존꺼에서 빼는 것
        right[topping[i]]--;
        // 뺏을때 이 뺀 개수가 0이 되면 종류 감소
        if (right[topping[i]] == 0) {
            rightKinds--;
        }
    }
    
    return answer;
}