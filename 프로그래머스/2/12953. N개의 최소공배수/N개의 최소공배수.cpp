#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int gcd(int a, int b) {
    // a % b 나머지가 0이면 
    if (a % b == 0) {
        return b;
    }
    // 그렇지 않으면 다음 재귀로
    return gcd(b, a % b);
}

// 최소공배수는 두 수의 곱 나누기 최대공약수
int lcm(int a, int b) {
    return a * b / gcd(a, b);
}

int solution(vector<int> arr) {
    int answer = 0;
    
    // 인근 2개씩 최소공배수를 찾아가면 될 듯
    // 최소 공배수는 최대공배수를 구할 두 수의 곱 나누기 그 두 수의 최대공약수를 나눈 것
    // 최대 공약수는 a b가 있으면 b a%b를 반복하면서 a%b가 0이 될 때까지 반복
    answer = arr[0];
    for (int num : arr) {
        answer = lcm(answer, num);
    }
    
    return answer;
}