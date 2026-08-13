class Solution {
    public int solution(int n, int[][] results) {
        // 결과가 주어지는 데 A선수가 B선수를 이겼다는 뜻
        // 순위를 정확하게 매길 수 있는 선수의 수를 count
        
        // 우선 승리가 가능한지 results 배열을 통해서 저장
        // 1-based니깐 n + 1로 생성
        boolean[][] isWin = new boolean[n + 1][n + 1];
        for (int[] result : results) {
            // [A, B]는 A가 B를 이겼다는 뜻. 즉, A가 증자 B가 패자
            int winner = result[0];
            int loser = result[1];
            
            // 배열 초기는 false로 초기화니깐 승리 갱신
            // 동일하게 isWin [A, B]가 true면 A가 승자 B가 패자 (승패 결정 가능)
            isWin[winner][loser] = true;
        }
        
        
        // 그리고 이 관계를 이용해서 플루이드워셜을 이용해서 관계를 통해 또다른 관계를 찾아서 업데이트
        // i j k를 이용해서 i가 j를 이기고 j가 k를 이겼다면 i는 k보다 순위가 높다는 관계를 이용
        // ++ 플로이드워셜에서는 중간노드가 가장 바깥쪽 반복문이어야 한다고함.. 이거때문에 틀림....
        // 즉, 관계가 i > j > k 순이라면 반복문은 j i k 순이어야함
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= n; i++) {
                for (int k = 1; k <= n; k++) {
                    if (isWin[i][j] && isWin[j][k]) {
                        isWin[i][k] = true;
                    }
                }
            }
        }
        
        // 그리고 각 선수마다 모두 승패를 알 수 있으면 카운트 증가
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            // 자기 자신 빼고 승패가 저장되어있는지 count
            int count = 0;
            for (int j = 1; j <= n; j++) {
                // 자기 자신은 패스
                if (i == j) {
                    continue;
                }
                
                // [i, j] 또는 [j, i] 중에 승이 하나라도 존재하면 승패 확인 가능한 것
                if (isWin[i][j] || isWin[j][i]) {
                    count++;
                }
            }
            
            // 비교 끝났는데 자기 자신빼고 다 승패가 존재한다?
            // 그럼 순위 확실히 알 수 있는 것
            if (count == n - 1) {
                answer++;
            }
        }
        
        return answer;
    }
}