class Solution {
    public String solution(int n) {
        // 124 나라에는 1, 2, 4만 존재
        // 일반적인 3진법과 비슷하지만 0이 없다는 차이가 있음
        
        // n에서 1을 빼고 3으로 나눈 나머지를 이용하면
        // 나머지 0, 1, 2를 각각 1, 2, 4로 대응시킬 수 있음
        String[] numbers = {"1", "2", "4"};
        
        String answer = "";
        
        while (n > 0) {
            // 124 나라에는 0이 없기 때문에
            // 현재 숫자에서 1을 빼고 계산
            n--;
            
            // 나머지에 따라 현재 자리의 숫자를 결정
            answer = numbers[n % 3] + answer;
            
            // 다음 자리 계산
            n /= 3;
        }
        
        return answer;
    }
}