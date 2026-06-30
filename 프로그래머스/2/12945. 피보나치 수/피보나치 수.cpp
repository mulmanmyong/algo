#include <string>
#include <vector>

using namespace std;

int solution(int n) {
    int answer = 0;
    
    // 단순 재귀로 풀면 시간초과 발생
    // DP 활용하여 풀이
    vector<int> fibo(n + 1);
    fibo[0] = 0;
    fibo[1] = 1;
    
    for (int i = 2; i <= n; i++) {
        fibo[i] = (fibo[i - 1] + fibo[i - 2]) % 1234567;
    }
    
    answer = fibo[n];
    
    return answer;
}