import java.util.*;
import java.io.*;

class Solution {
    public long solution(int n, int[] times) {
        // 이분탐색을 이용해서 모든 사람이 심사를 받는데 걸리는 최소 시간의 경계를 찾으면 됨
        
        // 탐색 범위는 1분부터 가장 빠른 심사관이 n명을 모두 심사하는 시간까지
        // times 배열은 정렬되어 있다는 보장이 없기 때문에 가장 빠른 심사 시간을 따로 구해야 함
        
        // 최대 시간이 int 범위를 넘어갈 수 있기 때문에
        // 탐색 범위와 관련된 연산은 long으로 진행
        
        // 시간이 빠른 순서대로 오름차순
        Arrays.sort(times);
        
        // 이분탐색을 위한 left, right 설정 
        // right가 제일 빠른 사람이 n명을 독박으로 처리할 때
        long left = 1;
        long right = (long) times[0] * n;
        
        long answer = right;
        while (left <= right) {
            // 현재 탐색 범위의 가운데 시간을 계산
            long mid = left + (right - left) / 2;

            // mid분 동안 심사관이 처리할 수 있는 사람 수 계산
            long count = 0;
            
            for (int time : times) {
                count += (mid / time);
                
                // 이미 n명 이상 처리했으면 더 계산해볼 필요 없음
                if (count >= n) {
                    break;
                }
            }
            
            // n명이상 처리가 가능하면
            if (count >= n) {
                answer = mid; // 정답 갱신
                
                // mid보다 더 짧은 시간도 가능한 지 확인
                right = mid - 1;
            }
            // 불가능하다면
            else {
                // mid 시간으로 처리가 불가능하다는 것
                // mid보다 더 긴 시간으로 확인
                left = mid + 1;
            }
        }

        return answer;
    }
}