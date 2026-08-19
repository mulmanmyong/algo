class Solution {
    public int solution(int storey) {
        // 현재 층에서 0층까지 이동하는데 필요한 마법의 돌 최소 개수를 구하는 문제
        
        // 즉, 각 자리 숫자마다 아래로 내릴지 위로 올릴지 선택하면 됨
        // 현재 자리에서 더 적은 횟수가 필요한 방향을 선택해도
        // 이후 자리의 최적 선택에 영향을 주지 않기 때문에 그리디로 풀이 가능
        
        int answer = 0;
        
        while (storey > 0) {
            // 현재 확인할 가장 낮은 자리 숫자
            int current = storey % 10;
            
            // 현재 자리 숫자가 5보다 작으면
            if (current < 5) {
                // 아래로 내리는 것이 더 적은 횟수
                answer += current;
            }
            // 현재 자리 숫자가 5보다 크면
            else if (current > 5) {
                // 위로 올려서 10을 만드는 것이 더 적은 횟수
                answer += 10 - current;
                
                // 10까지 올렸으므로 다음 자리에 1을 올림
                storey += 10;
            }
            // 현재 자리 숫자가 5면 위, 아래 횟수가 같기 때문에
            else {
                // 다음 자리 숫자를 보고 결정
                int next = (storey / 10) % 10;
                
                // 다음 자리도 5 이상이면
                // 현재 자리에서 위로 올리는 것이 유리
                if (next >= 5) {
                    answer += 5;
                    storey += 10;
                }
                // 다음 자리가 5보다 작으면 아래로 내림
                else {
                    answer += 5;
                }
            }
            
            // 현재 자리 처리가 끝났으므로 다음 자리로 이동
            storey /= 10;
        }
        
        return answer;
    }
}