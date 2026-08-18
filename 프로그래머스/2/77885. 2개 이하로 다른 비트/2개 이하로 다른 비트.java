class Solution {
    public long[] solution(long[] numbers) {
        // 각 숫자보다 크면서
        // 비트가 1개 또는 2개만 다른 숫자 중 가장 작은 값을 찾으면 됨
        long[] answer = new long[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            long number = numbers[i];
            
            // 짝수인 경우
            // 가장 마지막 비트가 항상 0이므로
            // 마지막 비트를 1로 바꾼 number + 1이 정답
            if (number % 2 == 0) {
                answer[i] = number + 1;
            }
            // 홀수인 경우
            else {
                // 오른쪽부터 처음 등장하는 0을 찾음
                long bit = 1;
                
                // 현재 위치의 비트가 1인 동안 왼쪽으로 이동
                while ((number & bit) != 0) {
                    bit <<= 1;
                }
                
                // 처음 발견한 0을 1로 바꾸고
                // 바로 오른쪽의 1을 0으로 바꾸면
                // 원래 숫자보다 크면서 비트가 2개만 다른 가장 작은 숫자가 됨
                answer[i] = number + bit - (bit >> 1);
            }
        }
        
        return answer;
    }
}