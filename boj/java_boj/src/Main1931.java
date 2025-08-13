import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main1931 {
	
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int meetingSize;
	public static int[][] meetingTimeTable;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		meetingSize = Integer.parseInt(br.readLine().trim());
		
		
		meetingTimeTable = new int[meetingSize][2];
		for (int meetingIndex = 0; meetingIndex < meetingSize; meetingIndex++) {
			st = new StringTokenizer(br.readLine().trim());
			meetingTimeTable[meetingIndex][0] = Integer.parseInt(st.nextToken());
			meetingTimeTable[meetingIndex][1] = Integer.parseInt(st.nextToken());
		}
		
		// 정렬을 함, 
		Arrays.sort(meetingTimeTable, (o1, o2) -> {
		    if (o1[1] == o2[1]) {
		        return o1[0] - o2[0];
		    }
		    return o1[1] - o2[1];
		});
		
		// 그리디하게 품, 최초시작을 기준으로 끝나는 시점을 시작시점으로 업데이트 
		int count = 0;
		int prevEndTime = 0;
		
		for (int meetingIndex = 0; meetingIndex < meetingSize; meetingIndex++) {
			// 현재 회의의 시작 시간이 이전 회의의 종료 시간보다 크거나 같으면
			if (meetingTimeTable[meetingIndex][0] >= prevEndTime) {
				// 회의를 선택하고
				count++;
				// 이전 종료 시간을 현재 회의의 종료 시간으로 업데이트
				prevEndTime = meetingTimeTable[meetingIndex][1];
			}
		}

		sb.append(count);
		System.out.println(sb);
	}
}
