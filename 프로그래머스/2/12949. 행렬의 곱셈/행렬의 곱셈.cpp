#include <string>
#include <vector>

using namespace std;

vector<vector<int>> solution(vector<vector<int>> arr1, vector<vector<int>> arr2) {
    // 행렬의 곱을 구하는 것
    // arr1의 행과 arr2의 열을 각각 곱하고 더한 것들이 곱한 것의 값..
    // 예를 들어 입출력 1을 보면
    // arr1의 1행과 arr2의 1열을 각각 곱하고 더하면 answer의 1행 1열
    // arr1의 1행과 arr2의 2열을 각각 곱하고 더하면 answer의 1행 2열이 됨
    // 이를 계산식으로 정리
    
    vector<vector<int>> answer;
    // arr1의 행을 순회
    for(int i = 0; i < arr1.size(); i++){
        vector<int> vec;
        // arr2의 열을 순회
        for(int j = 0; j < arr2[0].size(); j++){
            int sum = 0;
            // 각 자리 수의 합을 구함
            for(int k = 0; k < arr1[0].size(); k++){
                sum += arr1[i][k] * arr2[k][j];
            }
            // 각 행의 열 값을 추가
            vec.push_back(sum);
        }
        // 한 행의 결과가 완료됨
        // 행을 추가
        answer.push_back(vec);
    }
    
    return answer;
}