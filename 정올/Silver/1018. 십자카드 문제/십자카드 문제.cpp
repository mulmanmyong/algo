#include <iostream>

using namespace std;

// 회전하며 최소 시계수 찾는 함수
int findMinClockNum(int num1) {
    // a b c d -> 기존숫자
    int a = num1 / 1000;
    int b = (num1 % 1000) / 100;
    int c = (num1 % 100) / 10;
    int d = num1 % 10;

    // 1회전 d a b c
    int num2 = 1000*d + 100*a + 10*b + c;
    // 2회전 c d a b
    int num3 = 1000*c + 100*d + 10*a + b;
    // 3회전 b c d a
    int num4 = 1000*b + 100*c + 10*d + a;
    // 4회전은 원복

    // 최소 시계수 찾기
    return min(min(num1, num2), min(num3, num4));
}

// 0이 포함된 숫자인지 확인하는 함수
bool isValidNum(int num) {
    // 한 자리씩 확인하며 0이 있으면
    // 십자카드가 될 수 없는 숫자
    while (num) {
        if (num % 10 == 0) {
            return false;
        }
        num /= 10;
    }

    return true;
}

int main() {
    // 시계수를 어떻게 구할 것인가..
    // 시계수의 순서를 찾는 규칙을 찾아보려 했으나 규칙이 존재하진 않음
    // 그리고 생각해보니 1111에서 9999까지는 N이 그렇게 크지도 않고,
    // 최악 4회전이라고 쳐도 N최대가 35000도 안넘는다..

    // 완탐으로 진행
    // 1. 회전했을 때 작은 시계수를 찾는 함수
    // 2. 1111부터 9999까지 시계수 진행
    //   2-1. 이때 나온 시계수가 최소 시계수랑 동일하면 최소 시계수다! count 증가
    //   2-2. 다르다면 시계수가 아님!

    // 시계수 입력
    int targetClockNum = 0;
    for (int i = 0; i < 4; i++) {
        int num;
        cin >> num;
        targetClockNum = targetClockNum * 10 + num;
    }
    // 최소 시계수로 변환
    targetClockNum = findMinClockNum(targetClockNum);

    // 몇번째 시계수 인지 찾기
    int count = 0;
    for (int num = 1111; num <= 9999; num++) {
        // 0처리 로직을 빼먹음
        // 0이 포함된 숫자는 십자카드가 아니므로 패스
        if (!isValidNum(num)) {
            continue;
        }

        // 최소 시계수냐?
        if (findMinClockNum(num) == num) {
            count++;
        }

        // 근데 이게 정답이냐?
        if (num == targetClockNum) {
            cout << count;
            break;
        }
    }

    return 0;
}