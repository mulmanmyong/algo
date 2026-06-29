#include <string>
#include <vector>
#include <bitset>

using namespace std;

int solution(int n) {
    // bitset 라이브러리를 이용하면 비트 연산과 관련된 함수를 사용할 수 있음
    // n의 최댓값이 100만이므로 20비트면 충분하며, 20비트 이진수로 변환하여 1의 개수를 계산
    int originOneCount = bitset<20>(n).count();

    while (true) {
        n++;

        // 현재 수의 1의 개수를 계산하여
        // 원래 n의 1의 개수와 같으면 반복 중단
        if (bitset<20>(n).count() == originOneCount) {
            break;
        }
    }

    return n;
}