import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        // 파일명은 HEAD, NUMBER, TAIL 세 부분으로 나눌 수 있음
        // HEAD : 숫자가 나오기 전까지의 문자열
        // NUMBER : 처음 등장한 연속된 숫자, 최대 5자리
        // TAIL : 그 이후의 문자열
        
        // 정렬 기준을 정리해보면
        // 1. HEAD를 대소문자 구분 없이 비교
        // 2. HEAD가 같으면 NUMBER를 숫자로 비교 (012 == 12)
        // 3. 둘 다 같으면 입력된 순서를 유지
        
        // 정렬을 기준에 따라 할수 있도록 진행
        Arrays.sort(files, (file1, file2) -> {
            // 각 파일에서 HEAD가 끝나는 위치 찾기
            int numberStart1 = findNumberStart(file1);
            int numberStart2 = findNumberStart(file2);
            
            String head1 = file1.substring(0, numberStart1);
            String head2 = file2.substring(0, numberStart2);
            
            // HEAD는 대소문자를 구분하지 않고 비교
            // 문자열을 대소문자 구분 없이 사전순으로 비교하는 메서드를 사용
            int headCompare = head1.compareToIgnoreCase(head2);
            
            // 반환값은
            // 음수: 앞 문자열이 사전순으로 더 앞
            // 0: 두 문자열이 같음
            // 양수: 앞 문자열이 사전순으로 더 뒤
            // 따라서 같을 경우를 제외한 경우를 반환하면 정렬이 가능 
            if (headCompare != 0) {
                return headCompare;
            }
            
            // HEAD가 같으면 NUMBER 비교
            int numberEnd1 = findNumberEnd(file1, numberStart1);
            int numberEnd2 = findNumberEnd(file2, numberStart2);
            
            int number1 = Integer.parseInt(file1.substring(numberStart1, numberEnd1));
            int number2 = Integer.parseInt(file2.substring(numberStart2, numberEnd2));
            
            return Integer.compare(number1, number2);
        });
        
        return files;
    }
    
    // 파일명에서 처음 숫자가 등장하는 위치 찾기
    private int findNumberStart(String file) {
        for (int i = 0; i < file.length(); i++) {
            if (Character.isDigit(file.charAt(i))) {
                return i;
            }
        }
        
        return file.length();
    }
    
    // NUMBER가 끝나는 위치 찾기
    private int findNumberEnd(String file, int start) {
        int end = start;
        
        // NUMBER는 최대 5자리까지 가능
        while (end < file.length() && end - start < 5 && Character.isDigit(file.charAt(end))) {
            end++;
        }
        
        return end;
    }
}