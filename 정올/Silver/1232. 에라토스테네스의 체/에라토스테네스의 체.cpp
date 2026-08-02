#include <iostream>
#include <vector>

using namespace std;

int main() {
    // 소수를 찾는 과정인 에라토스테네스의 체에서 K번째로 지워지는 수를 그냥 출력하는 것
    // N까지의 수, K번째로 지워지는 수
    int N, K;
    cin >> N >> K;

    // 처음에는 아무 수도 지워지지 않았다고 가정
    vector<bool> erased(N + 1, false);

    // 지금까지 지운 횟수
    int count = 0;

    // 2부터 N까지 차례대로 확인
    for (int i = 2; i <= N; i++) {
        // 이미 지워졌다면 넘어감
        if (erased[i]) continue;

        // i의 배수를 차례대로 지움
        for (int j = i; j <= N; j += i) {
            // 아직 지워지지 않았다면
            if (!erased[j]) {
                erased[j] = true;
                count++;

                // K번째로 지운 수라면 출력 후 종료
                if (count == K) {
                    cout << j;
                    return 0;
                }
            }
        }
    }

    return 0;
}