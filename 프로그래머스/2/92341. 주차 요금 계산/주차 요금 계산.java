import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        // 자동차별 주차요금 계산
        // 분으로 계산을 하며, 최종 결과는 차량번호가 작은 순서대로 반환
        
        // 차량별 입차 시간 저장
        Map<String, Integer> inTime = new HashMap<>();
        
        // 차량별 누적 주차 시간 저장
        Map<String, Integer> totalTime = new HashMap<>();
        
        for (String record : records) {
            // "05:34 5961 IN" 형태이므로 공백 기준으로 분리
            String[] split = record.split(" ");
            String time = split[0];
            String carNumber = split[1];
            String status = split[2];
            
            // 시간을 분으로 변환
            String[] timeSplit = time.split(":");
            int hour = Integer.parseInt(timeSplit[0]);
            int minute = Integer.parseInt(timeSplit[1]);
            int currentTime = hour * 60 + minute;
            
            // 입차
            if (status.equals("IN")) {
                inTime.put(carNumber, currentTime);
            }
            // 출차
            else {
                // 입차 시간부터 출차 시간까지 계산
                int parkingTime = currentTime - inTime.get(carNumber);
                
                // 기존 누적시간에 이번 주차시간을 더함
                totalTime.put(carNumber, totalTime.getOrDefault(carNumber, 0) + parkingTime);
                
                // 출차했으므로 입차 기록 제거
                inTime.remove(carNumber);
            }
        }
        
        // 모두 분석했는데 출차 기록이 없는 차량은 23:59에 출차한 것으로 계산
        int endTime = 23 * 60 + 59;
        for (String carNumber : inTime.keySet()) {
            int parkingTime = endTime - inTime.get(carNumber);
            
            // 누적시간에 더함
            totalTime.put(carNumber, totalTime.getOrDefault(carNumber, 0) + parkingTime);
        }
        
        // 차량번호가 작은 순서대로 정렬
        List<String> carNumbers = new ArrayList<>(totalTime.keySet());
        Collections.sort(carNumbers);
        
        int[] answer = new int[carNumbers.size()];
        
        // 요금표는 기본시간, 기본요금, 단위시간, 단위요금 순
        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        
        for (int i = 0; i < carNumbers.size(); i++) {
            String carNumber = carNumbers.get(i);
            int parkingTime = totalTime.get(carNumber);
            
            int fee = basicFee;
            
            // 기본 시간을 초과한 경우 추가요금 계산
            if (parkingTime > basicTime) {
                int extraTime = parkingTime - basicTime;
                
                // 단위시간으로 나누어 떨어지지 않으면 올림
                fee += (int) Math.ceil((double) extraTime / unitTime) * unitFee;
            }
            
            answer[i] = fee;
        }
        
        return answer;
    }
}