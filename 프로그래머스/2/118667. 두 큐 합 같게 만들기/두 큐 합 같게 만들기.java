import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        // 두 큐에서 원소를 하나씩 옮겨서
        // 두 큐의 합을 같게 만드는 최소 횟수를 구하는 문제
        
        // 모든 원소가 양수이기 때문에
        // 합이 큰 큐에서는 원소를 빼고
        // 합이 작은 큐에서는 원소를 받아오는 방향으로 진행하면 됨
        // -> 그리디
        
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        
        // 원소의 크기가 최대 10^9이고 개수도 많기 때문에
        // 큐의 합은 int 범위를 넘어갈 수 있어서 long 사용
        long sum1 = 0;
        long sum2 = 0;
        
        for (int num : queue1) {
            q1.offer(num);
            sum1 += num;
        }
        
        for (int num : queue2) {
            q2.offer(num);
            sum2 += num;
        }
        
        // 두 큐의 전체 합이 홀수라면
        // 절반으로 나눌 수 없으므로 합을 같게 만들 수 없음
        if ((sum1 + sum2) % 2 != 0) {
            return -1;
        }
        
        long target = (sum1 + sum2) / 2;
        int count = 0;
        
        // 원소를 계속 서로 옮기다 보면 같은 상태가 반복될 수 있으므로
        // 충분한 횟수까지만 확인
        // 두 큐의 모든 원소를 여러 번 순회할 정도면 가능한 경우는 이미 확인 가능
        int maxCount = queue1.length * 4;
        
        while (count < maxCount) {
            // queue1의 합이 목표값이면
            // queue2도 자동으로 목표값이므로 종료
            if (sum1 == target) {
                return count;
            }
            
            if (sum1 > target) {
                // queue1의 합이 더 크면
                // queue1의 맨 앞 원소를 queue2로 이동
                int num = q1.poll();
                
                sum1 -= num;
                sum2 += num;
                
                q2.offer(num);
            } else {
                // queue1의 합이 더 작으면
                // queue2의 맨 앞 원소를 queue1으로 이동
                int num = q2.poll();
                
                sum2 -= num;
                sum1 += num;
                
                q1.offer(num);
            }
            
            count++;
        }
        
        // 충분히 원소를 이동했는데도 만들 수 없다면 불가능
        return -1;
    }
}