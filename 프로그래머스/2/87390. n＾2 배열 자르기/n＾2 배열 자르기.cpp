#include <string>
#include <vector>

using namespace std;

vector<int> solution(int n, long long left, long long right) {
    vector<int> answer;
    
    // 2차원 배열은 1-based 인덱스, 자른 1차원 배열은 0-based 인덱스
    // 즉, left와 right는 0-based 인덱스이다.
    // 배열을 만들 필요 없이 
    // 현재 인덱스(idx)를 n으로 나눈 몫은 row, 나머지는 col임을 알 수 있음
    // 이때 row+1, col+1이 2차원 배열에서의 행과 열 인덱스가 됨
    // 2차원 배열에는 행과 열 중 큰 값이 들어가는 규칙을 발견
    // ex) (1,1)은 1 / (1,2), (2,1), (2,2)는 2 / (1,3), (3,1), (3,3)은 3

    // 이를 left부터 right까지 진행하며 값을 구함
    // 필요한 구간만 순회하므로 시간복잡도는 O(right - left) 
    // right - left는 최대 10^5
    for (long long idx = left; idx <= right; idx++) {
        int row = (int) (idx / n);
        int col = (int) (idx % n);
        
        answer.push_back(max(row, col) + 1);
    }
    
    return answer;
}