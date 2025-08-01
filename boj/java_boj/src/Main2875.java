// 2875. 대회 or 인턴 -> 브론즈 3

import java.util.Scanner;

public class Main2875 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 여학생의 수  
		int M = sc.nextInt(); // 남학생의 수  
		int K = sc.nextInt(); // 반드시 인턴쉽에 참여해야하는 수
		
		// 2명의 여학생과 1명의 남학생 팀 결성이 원칙
		// 인턴쉽 참여하면 대회 참가 불가  
		int answer = 0;
		for (int goIntern = 0; goIntern <= K; goIntern++) {
			int girl = N-goIntern; // 인턴쉽간 인원 제외 대회 참가 가능 여학생 
			int boy = M-(K-goIntern); // 인턴쉽간 인원 제외 대회 참가 가능 남학생  
			
			if (girl <= 0 || boy <= 0) {
				continue; // 여학생 또는 남학생이 존재하지 않거나 음수면 계산 불가 
			}
			
			// 그렇지 않으면 계산, 여학생과 남학생 2:1 비율  
			int curTeam = Math.min(girl/2, boy); // 여학생의 수 /2 랑 남학생의 수 중에 작은 것이 만들 수 있는 팀의 수 
			answer = Math.max(answer, curTeam); // 많이 만들 수 있는 지 확인 
		}
		System.out.println(answer);
	}
}
