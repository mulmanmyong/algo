#include <iostream>
#include <vector>

using namespace std;

// 소수 판별
vector<bool> getPrime(int maxNum) {
    // 처음에는 모두 소수라고 가정
    vector<bool> prime(maxNum + 1, true);

    // 0과 1은 소수가 아님
    if (maxNum >= 0) prime[0] = false;
    if (maxNum >= 1) prime[1] = false;

    // 에라토스테네스의 체
    for (int i = 2; i * i <= maxNum; i++) {
        if (prime[i]) {
            // i의 배수는 모두 소수가 아님
            for (int j = i * i; j <= maxNum; j += i) {
                prime[j] = false;
            }
        }
    }

    return prime;
}

int main() {
    // 학생 수, 마지막 번호, 알고 싶은 학생 번호
    int N, M, K;
    cin >> N >> M >> K;

    // M까지의 소수 정보 구하기
    vector<bool> prime = getPrime(M);

    // K번 학생이 박수를 친 횟수
    int count = 0;

    // 1부터 M까지 차례대로 진행
    for (int num = 1; num <= M; num++) {
        // 현재 번호를 부르는 학생 번호
        int student = (num - 1) % N;

        // 소수이고 K번 학생의 차례라면 박수
        if (prime[num] && student == K) {
            count++;
        }
    }

    // 결과 출력
    cout << count;

    return 0;
}
