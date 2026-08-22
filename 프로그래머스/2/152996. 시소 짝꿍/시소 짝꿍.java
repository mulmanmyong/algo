import java.util.*;

class Solution {
    public long solution(int[] weights) {
        // 자기 자신이 아닌 다른 것들과 비교하며 진행
        // 비율이 1:1 1:2 2:3 3:4 면 시소짝꿍
        long answer = 0;
        
        // 편한 비율 비교를 위해 weights 정렬
        Arrays.sort(weights);
        
        // 완탐
        for (int i = 0; i < weights.length - 1; i++) {
            for (int j = i + 1; j < weights.length; j++) {
                // 정렬했기 때문에 weights[i] <= weights[j]
                
                // 1:1
                if (weights[i] == weights[j]) {
                    answer++;
                }
                // 2:3
                else if (3 * weights[i] == 2 * weights[j]) {
                    answer++;
                }
                // 1:2
                else if (2 * weights[i] == weights[j]) {
                    answer++;
                }
                // 3:4
                else if (4 * weights[i] == 3 * weights[j]) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
}