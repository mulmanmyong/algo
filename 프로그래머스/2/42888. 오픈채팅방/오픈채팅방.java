import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        // 오픈채팅방
        // Enter : 채팅방 입장
        // Leave : 채팅방 퇴장
        // Change : 닉네임 변경
        
        // 같은 uid의 닉네임이 변경되면
        // 이전 입장/퇴장 메시지의 닉네임도 전부 변경되어야 함
        
        // 따라서 record를 읽으면서 바로 메시지를 완성하지 않고
        // uid별 최종 닉네임을 먼저 저장한 뒤 메시지를 만들어주면 됨
        
        Map<String, String> nicknameMap = new HashMap<>();
        
        // 최종 닉네임 저장
        for (String r : record) {
            String[] info = r.split(" ");
            
            String command = info[0];
            String uid = info[1];
            
            // Enter와 Change에는 닉네임 정보가 존재
            if (command.equals("Enter") || command.equals("Change")) {
                String nickname = info[2];
                nicknameMap.put(uid, nickname);
            }
        }
        
        List<String> messages = new ArrayList<>();
        
        // 최종 닉네임을 기준으로 입장/퇴장 메시지 생성
        for (String r : record) {
            String[] info = r.split(" ");
            
            String command = info[0];
            String uid = info[1];
            
            // 들어올 때
            if (command.equals("Enter")) {
                messages.add(nicknameMap.get(uid) + "님이 들어왔습니다.");
            }
            // 나갈 때
            else if (command.equals("Leave")) {
                messages.add(nicknameMap.get(uid) + "님이 나갔습니다.");
            }
            // Change는 출력되는 메시지가 없음
        }
        
        return messages.toArray(new String[0]);
    }
}