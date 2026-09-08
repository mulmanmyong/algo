class Solution {
    int answer = 0;
    int[] queens;

    public int solution(int n) {
        // N-Queen
        // nxn 체스판에 n개의 퀸을 서로 공격할 수 없도록 배치
        
        // 퀸은 가로, 세로, 대각선으로 공격 가능
        // 한 행에는 퀸을 하나만 놓으면서 진행하면
        // 같은 행은 따로 검사할 필요가 없음
        
        // queens[row] = 해당 row에 놓인 퀸의 col 위치
        queens = new int[n];
        
        // 첫 번째 행부터 퀸 배치 시작
        dfs(0, n);
        
        return answer;
    }
    
    private void dfs(int row, int n) {
        // 모든 행에 퀸을 배치했다면 하나의 경우 완성
        if (row == n) {
            answer++;
            return;
        }
        
        // 현재 행의 각 열에 퀸을 놓아보기
        for (int col = 0; col < n; col++) {
            if (canPlace(row, col)) {
                queens[row] = col;
                dfs(row + 1, n);
            }
        }
    }
    
    private boolean canPlace(int row, int col) {
        // 이전 행에 놓은 퀸들과 충돌하는지 확인
        for (int prevRow = 0; prevRow < row; prevRow++) {
            int prevCol = queens[prevRow];
            
            // 같은 열이면 배치 불가능
            if (prevCol == col) {
                return false;
            }
            
            // 행의 차이와 열의 차이가 같으면 같은 대각선
            if (Math.abs(row - prevRow) == Math.abs(col - prevCol)) {
                return false;
            }
        }
        
        return true;
    }
}