import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        // A팀의 출전 순서는 정해져 있고
        // B팀은 순서를 자유롭게 정해서 최대 승점을 얻어야 함
        
        // 결국 B의 숫자들을 A의 숫자들과 어떻게 매칭할지가 중요
        // 둘 다 오름차순으로 정렬한 뒤
        // B가 이길 수 있는 가장 작은 A부터 처리하면 됨
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int aIndex = 0;
        int bIndex = 0;
        int answer = 0;
        
        while (aIndex < A.length && bIndex < B.length) {
            // B의 현재 숫자가 A를 이길 수 있다면
            // 이 B보다 작은 숫자로는 이기기 어려울 수 있으므로
            // 현재 A와 매칭해서 승점 획득
            if (B[bIndex] > A[aIndex]) {
                answer++;
                aIndex++;
                bIndex++;
            }
            // B의 현재 숫자가 A의 가장 작은 숫자도 이길 수 없다면
            // 뒤에 있는 더 큰 A도 이길 수 없으므로 이 숫자는 버림
            else {
                bIndex++;
            }
        }
        
        return answer;
    }
}