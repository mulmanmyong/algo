import java.util.*;

class Solution {
    public int[] solution(int n, long k) {
        // n명의 사람이 일렬로 줄을 섬
        // 1번부터 n번까지 번호가 매겨져 있음
        
        // 모든 순열을 직접 만들지 않고 팩토리얼을 이용하면 k번째 순열을 바로 찾을 수 있을 듯?
        // 현재 자리에 한 사람을 고정하면 남은 사람들로 만들 수 있는 순열은 (남은 사람 수)!개
        // 따라서 팩토리얼을 기준으로 k번째 순열에서 현재 자리에 올 사람을 차례대로 구하면 됨
        
        List<Integer> people = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            people.add(i);
        }
        
        // n명이 줄 서는 전체 경우의 수는 n!
        long fact = 1;
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        
        // k를 0-based로 변경
        k--;
        
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            // 현재 사람 한 명을 고정하면
            // 남은 사람들이 만들 수 있는 경우의 수는 (남은 사람 수)!
            fact /= (n - i);
            
            // k번째 순열이 몇 번째 묶음에 있는지 계산
            int index = (int) (k / fact);
            
            // 해당 사람을 현재 자리에 배치
            answer[i] = people.get(index);
            // 이미 배치한 사람은 후보에서 제거
            people.remove(index);
            
            // 선택한 묶음 안에서 다시 몇 번째인지 계산
            k %= fact;
        }
        
        return answer;
    }
}