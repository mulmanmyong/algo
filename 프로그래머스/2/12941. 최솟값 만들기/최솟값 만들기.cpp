#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> A, vector<int> B)
{
    int answer = 0;
    
    // 길이가 같은 두 배열이 존재.
    // 배열의 한자리씩 곱해서 다 합쳤을 때 최소 구하기.
    // 최소는 작은수와 큰수와 곱해서 합하는게 맞을 듯
    
    // 방법은 2가지 A 배열 작은거에서 큰거, B 배열 큰거에서 작은거로 줄어들면서 곱하기
    //           A 배열 큰거에서 작은거, B 배열 작은거에서 큰거로 줄어들면서 곱하기
    // 둘 중 아무거나 해도 값은 동일하게 나올 것
    
    // A 배열 작은거에서 큰거, B 배열 큰거에서 작은거로 줄어들면서 곱하는 방안으로 정렬 진행
    // 정렬 시간복잡도 O(nlogn)
    sort(A.begin(), A.end()); // 오름차순
    sort(B.begin(), B.end(), greater<int>()); // 내림차순
    
    for (int i = 0; i < A.size(); i++) {
        answer += (A[i] * B[i]);
    }
    
    return answer;
}