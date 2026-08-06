#include <string>
#include <vector>
#include <unordered_set>

using namespace std;

int solution(int N, int number) {
    // N을 몇개써서 number를 만들 수 있냐는 문제
    // 8개보다 많이쓰면 안되니깐?
    // 1개부터 8개 썼을때까지 보면 되고
    // N을 i개 써서 만들 수 있는 숫자들을 저장하고
    // 저장된 숫자들끼리 사칙연산해서 다음 결과 만들기
    // 작은 것부터 만들어가야 하니 바텀업으로 진행

    // N이 number와 같으면 N 하나만 쓰면 되니 1 반환
    if (N == number) {
        return 1;
    }

    // dp[i] = N을 i개 사용해서 만들 수 있는 숫자들
    // 중복되는 숫자는 필요 없고, 숫자가 있는지만 확인하면 되니 unordered_set 사용
    vector<unordered_set<int>> dp(9);

    for (int i = 1; i <= 8; i++) {
        // 5 -> 55 -> 555 같은 숫자 만들기
        int num = 0;
        for (int j = 0; j < i; j++) {
            num = num * 10 + N;
        }

        // 이어붙인 숫자 저장
        dp[i].insert(num);

        // 이전에 만든 결과들끼리 조합
        for (int j = 1; j < i; j++) {
            for (int a : dp[j]) {
                for (int b : dp[i - j]) {
                    // 사칙연산 결과 저장
                    dp[i].insert(a + b);
                    dp[i].insert(a - b);
                    dp[i].insert(a * b);

                    if (b != 0) {
                        dp[i].insert(a / b);
                    }
                }
            }
        }

        // i개를 사용해서 만든 수들 중에서 number가 있으면 지금이 최소
        if (dp[i].find(number) != dp[i].end()) {
            return i;
        }
    }

    // 8개까지 써도 못 만들면 -1 반환
    return -1;
}