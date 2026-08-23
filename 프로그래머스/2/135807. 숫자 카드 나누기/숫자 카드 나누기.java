class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        // 두 조건 중 하나를 만족하는 가장 큰 양의 정수 a 구하기
        // 철수의 카드를 다 나눌 수 있고, 영희의 카드를 하나도 나눌 수 없는 a
        // 또는 그 반대
        // 철수카드는 A 배열, 영희카드는 B 배열
        
        // 중복된 수가 존재 가능함
        // 각 배열의 모든 수를 나눌 수 있는 가장 큰 수는 최대공약수
        // 따라서 각 배열의 최대공약수를 구하고, 상대 배열의 수들을 나눌 수 없는지 확인
        
        // 각 배열의 최대공약수 구하기
        int gcdA = calculateGCD(arrayA);
        int gcdB = calculateGCD(arrayB);
        
        int answer = 0;
        
        // A 배열의 최대공약수가 B 배열의 수를 하나도 나누지 못한다면 가능
        if (!canDevide(gcdA, arrayB)) {
            answer = gcdA;
        }
        
        // B 배열의 최대공약수가 A 배열의 수를 하나도 나누지 못한다면 가능
        if (!canDevide(gcdB, arrayA)) {
            answer = Math.max(answer, gcdB);
        }
        
        return answer;
    }
    
    // 배열 전체의 최대공약수 구하는 메서드
    private int calculateGCD(int[] array) {
        int gcd = array[0];
        
        // 앞에서 구한 최대공약수와 다음 수의 최대공약수를 계속 구하면
        // 배열 전체의 최대공약수를 구할 수 있음
        for (int i = 1; i < array.length; i++) {
            gcd = gcd(gcd, array[i]);
        }
        
        return gcd;
    }
    
    // 유클리드 호제법을 이용해서 두 수의 최대공약수 구하기
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        
        return a;
    }
    
    // divisor가 배열의 수 중 하나라도 나눌 수 있는지 확인
    private boolean canDevide(int divisor, int[] array) {
        for (int num : array) {
            // 하나라도 나누어 떨어진다면 나눌 수 있음
            if (num % divisor == 0) {
                return true;
            }
        }
        
        return false;
    }
}