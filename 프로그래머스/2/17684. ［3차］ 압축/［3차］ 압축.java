import java.util.*;

class Solution {
    public int[] solution(String msg) {
        // LZW 알고리즘
        // 알파벳이 map 구조로 미리 담겨있고,,
        // 있으면 그 인덱스 출력하고, 뒷글자와 조합한 새로운 거 넣고,,
        // 이러한 방식으로 글자 끝까지 반복인듯
        
        // 사전에 A ~ Z까지 먼저 저장
        // 알파벳의 인덱스는 1부터 시작
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            map.put(String.valueOf((char) ('A' + i)), i + 1);
        }
        
        // 사전에 새롭게 추가될 문자열의 인덱스
        int index = 27;
        
        // 출력되는 인덱스의 개수를 미리 알 수 없으므로 List 사용
        List<Integer> result = new ArrayList<>();
        
        int i = 0;
        
        while (i < msg.length()) {
            // 현재 위치의 한 글자는 무조건 사전에 존재
            String current = String.valueOf(msg.charAt(i));
            int j = i + 1;
            
            // 현재 문자열에 다음 글자를 붙였을 때도 사전에 존재한다면
            while (j < msg.length() && map.containsKey(current + msg.charAt(j))) {
                // 사전에 존재하는 가장 긴 문자열까지 계속 확장
                current += msg.charAt(j);
                j++;
            }
            
            // 사전에 존재하는 가장 긴 문자열의 색인을 출력
            result.add(map.get(current));
            
            // 아직 다음 글자가 남아있다면
            if (j < msg.length()) {
                // 현재 문자열 + 다음 글자를 새로운 문자열로 사전에 추가
                map.put(current + msg.charAt(j), index++);
            }
            
            // 처리한 문자열 다음 위치부터 다시 탐색
            i = j;
        }
        
        // List를 int[]로 변환
        int[] answer = new int[result.size()];
        for (int k = 0; k < result.size(); k++) {
            answer[k] = result.get(k);
        }
        
        return answer;
    }
}