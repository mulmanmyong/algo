import java.util.Deque;
import java.util.ArrayDeque;

class Solution
{
    public int solution(String s)
    {
        int answer = -1;

        Deque<Character> dq = new ArrayDeque<>();
        
        for (char c : s.toCharArray()) {
            // 비어 있으면 제일 뒤에 삽입
            if (dq.isEmpty()) {
                dq.offerLast(c);
            }
            // 비어 있지 않고 제일 상단이랑 동일하면 제거
            else if (dq.peekLast() == c) {
                dq.pollLast();
            }
            // 비어 있지 않고 제일 상단이랑 다르면 삽입
            else {
                dq.offerLast(c);
            }
        }
        
        // 비어있으면 전부 다 제거 된 것answer = 1
        if (dq.isEmpty()) {
            answer = 1;
        }
        else {
            answer = 0;
        }

        return answer;
    }
}