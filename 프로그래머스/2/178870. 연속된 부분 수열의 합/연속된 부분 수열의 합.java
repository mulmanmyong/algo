class Solution {
    public int[] solution(int[] sequence, int k) {
        // 연속된 부분 수열 중 합이 k가 되는 구간을 찾아야 함
        
        // 조건을 만족하는 구간이 여러 개라면 길이가 가장 짧은 구간을 선택하고
        // 길이까지 같다면 시작 인덱스가 가장 작은 구간을 선택
        
        // sequence의 모든 원소가 양수이기 때문에
        // 투 포인터를 이용해서 현재 구간의 합을 계산할 수 있다
        
        // left와 right는 현재 확인하고 있는 연속 부분 수열의 범위
        int left = 0;
        int right = 0;
        
        // 현재 [left, right] 구간의 합
        int sum = sequence[0];
        
        // 시작 인덱스와 끝 인덱스
        int answerLeft = 0;
        int answerRight = sequence.length - 1;
        
        // 배열 범위 안에 있을 동안 진행
        while (left < sequence.length && right < sequence.length) {
            // 현재 구간의 합이 k와 같다면
            if (sum == k) {
                // 기존 정답보다 현재 구간의 길이가 더 짧은 경우 갱신
                if (right - left < answerRight - answerLeft) {
                    answerLeft = left;
                    answerRight = right;
                }
                
                // 현재 구간에서 더 짧은 구간이 가능한지 확인하기 위해
                // 왼쪽 값을 빼고 left를 오른쪽으로 이동
                sum -= sequence[left];
                left++;
            }
            // 현재 구간의 합이 k보다 작다면
            else if (sum < k) {
                // 오른쪽 범위를 늘려서 합을 증가시킴
                right++;
                
                // 배열 범위를 벗어나지 않는 경우에만 새로운 값을 더함
                if (right < sequence.length) {
                    sum += sequence[right];
                }
            }
            // 현재 구간의 합이 k보다 크다면
            else {
                // 왼쪽 값을 빼서 합을 감소시킴
                sum -= sequence[left];
                left++;
            }
        }
        
        int[] answer = {answerLeft, answerRight};
        return answer;
    }
}