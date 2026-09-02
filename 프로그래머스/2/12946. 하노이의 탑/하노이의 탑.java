class Solution {
    public int[][] solution(int n) {
        // 하노이의 탑
        // 근데 이제 최소 이동 횟수가 아닌
        // 1번 원판에 있는 n개의 판을 3번으로 옮기는 과정을 출력
        
        // 조건은
        // 1. 한 번에 하나의 원판만 옮길 수 있습니다.
        // 2. 큰 원판이 작은 원판 위에 있어서는 안됩니다.
        
        // n개의 원판을 옮기는 최소 이동 횟수는 2^n - 1
        // 따라서 이동 횟수만큼 미리 배열 생성
        int[][] answer = new int[(1 << n) - 1][2];
        
        // 1번 기둥에서 3번 기둥으로 이동
        hanoi(n, 1, 3, 2, answer, 0);
        
        return answer;
    }
    
    private int hanoi(int n, int from, int to, int via, int[][] answer, int index) {
        // 원판이 하나라면 바로 목적지 기둥으로 이동
        if (n == 1) {
            answer[index][0] = from;
            answer[index][1] = to;
            
            return index + 1;
        }
        
        // 가장 큰 원판 위의 n - 1개를 보조 기둥으로 이동
        index = hanoi(n - 1, from, via, to, answer, index);
        
        // 가장 큰 원판을 목적지 기둥으로 이동
        answer[index][0] = from;
        answer[index][1] = to;
        index++;
        
        // 보조 기둥의 n - 1개를 목적지 기둥으로 이동
        index = hanoi(n - 1, via, to, from, answer, index);
        
        return index;
    }
}