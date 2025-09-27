import java.io.*;
import java.util.*;

public class SWEA_2383_점심식사시간_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [설명]
 * NxN 크기의 정사각형 모양의 방에 사람들이 앉아 있음
 * 점심먹기 위해 아래층을 내려가야하는데 최대한 빠르게 내려가야함
 * 
 * 방 안의 사람들은 P로, 계단 입구를 S라고 표시
 * 
 * 이동 완료 시간은 모든 사람들이 계단을 내려가 아래 층으로 이동을 완료한 시간
 * 사람들이 아래층으로 이동하는 시간은 계단 입구까지 이동 시간과 계단을 내려가는 시간이 포함
 * 
 * 1. 계단 입구까지 이동시간
 *  - 사람이 현재 위치에서 계단의 입구까지 이동하는데 걸리는 시간으로 다음과 같이 계산
 *  - 이동시간(분) = |PR - SR| + |PC - SC|
 *  (PR PC : 사람 P의 세로 가로, SR SC : 계단 입구 S의 세로 가로)
 *  
 * 2. 계단을 내려가는 시간
 *  - 계단을 내려가는 시간은 계단 입구에 도착한 후부터 계단을 완전히 내려갈 떄의 시간
 *  - 도착하면 1분 후 아래칸으로 내려갈 수 있음
 *  - 3명 내려가고 있으몌ㄴ, 그 중 한명 완전히 내려갈 때까지 입구에서 대기
 *  - 계단마다 길이 K가 주어지며, 계단에 올라간 후 완전히 내려가는데 K분 걸림
 *  
 * 근데 계단은 반드시 2개임 
 *  
 * [입력]
 * 첫 줄에 총 테스트 케이스의 개수 T가 주어짐
 * 	각 테스트 케이스의 첫째 줄에 한벼의 길이 N이 주어짐
 * 	그다음 줄에 지도 주어짐 (1이 사람 2 이상은 계단 입구, 그리고 이 값이 계단의 길이임)
 * 
 * [출력]
 * 이동 완료되는 최소 시간을 출력 
 * 
 * [아이디어]
 * 우선 각 사람의 위치를 저장해
 * 계산의 위치도 저장해
 * 가까운 애들부터 진입을 시작해
 * 내려가는 동안 사람들의 이동시간을 시뮬레이션
 */

	// 좌표 클래스를 이용해 사람과 계단의 위치를 저장
	static class Point {
		int row, col;
		public Point(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	}
	
	// 방 한 변의 길이 N
	static int N;
	// 초기 지도 
	static int[][] initMap;
	
	// 사람의 위치들
	static ArrayList<Point> person;
	// 계단의 위치들
	static ArrayList<Point> stair;

	// 계단 길이 저장
	static int[] stairLength;

	public static int simulation() {
	    // 모든 경우의 수 중에서 최소 시간을 찾아야 하므로 초기값을 최대로 설정
	    int minTime = Integer.MAX_VALUE;
	    int totalPersons = person.size();
	    
	    // 비트마스크를 이용해 가능한 모든 사람 분배 조합을 시도
	    // 0: 계단 1, 1: 계단 2
	    for (int mask = 0; mask < (1 << totalPersons); mask++) {
	        ArrayList<Integer> stairTime1 = new ArrayList<>(); // 계단 1로 갈 사람들의 도착 시간
	        ArrayList<Integer> stairTime2 = new ArrayList<>(); // 계단 2로 갈 사람들의 도착 시간

	        // 각 사람을 계단 1 또는 계단 2로 분배
	        for (int i = 0; i < totalPersons; i++) {
	            // 해당 사람이 어느 계단으로 갈지 mask와의 비트 연산을 통해 확인
	            
	            // mask의 i번째 비트가 0이면 계단 1로
	            if ((mask & (1 << i)) == 0) {
	                int time = Math.abs(person.get(i).row - stair.get(0).row) + Math.abs(person.get(i).col - stair.get(0).col);
	                stairTime1.add(time);
	            }
	            // mask의 i번째 비트가 1이면 계단 2로
	            else {
	                int time = Math.abs(person.get(i).row - stair.get(1).row) + Math.abs(person.get(i).col - stair.get(1).col);
	                stairTime2.add(time);
	            }
	        }

	        // 각 계단에 대해 내려가는 시간 계산
	        int time1 = calculateTime(stairTime1, stairLength[0]); // 계단 1에 대한 시간
	        int time2 = calculateTime(stairTime2, stairLength[1]); // 계단 2에 대한 시간
	        
	        // 두 계단 중 늦게 끝나는 시간을 해당 조합의 완료 시간으로 설정
	        int totalTime = Math.max(time1, time2);
	        
	        // 모든 조합 중 가장 짧은 시간을 찾아 갱신
	        minTime = Math.min(minTime, totalTime);
	    }

	    return minTime;
	}
	
	public static int calculateTime(ArrayList<Integer> arrivalTimes, int stairLength) {
	    // 계단을 내려가기 시작한 사람들의 완료 시간을 저장
	    ArrayList<Integer> stairOutTime = new ArrayList<>();
	    // 계단 입구에서 대기 중인 사람들의 도착 시간을 저장
	    Collections.sort(arrivalTimes);
	    Queue<Integer> waitingQueue = new LinkedList<>(arrivalTimes);

	    int time = 0; // 현재 시간
	    int finishedPeople = 0; // 계단을 다 내려간 사람 수
	    int totalPersons = arrivalTimes.size();

	    // 모든 사람이 계단을 다 내려갈 때까지
	    while (finishedPeople < totalPersons) {
	        time++;

	        // 계단에서 내려가고 있는 사람들 처리
	        // 계단 완료 시간을 기준으로 현재 시간을 확인
	        for (int i = 0; i < stairOutTime.size(); ) {
	            if (stairOutTime.get(i) <= time) {
	                stairOutTime.remove(i);
	                finishedPeople++;
	            } 
	            else {
	                i++;
	            }
	        }
	        
	        // 대기 중인 사람들 처리
	        // 계단에 3명 미만이고, 대기열에 사람이 있다면 계단에 진입
	        while (stairOutTime.size() < 3 && !waitingQueue.isEmpty()) {
	            int arrival = waitingQueue.peek();

	            // 사람이 계단 입구에 도착했고, 1분 대기 후 계단에 진입 가능
	            if (arrival + 1 <= time) {
	                waitingQueue.poll();
	                // 계단을 완전히 내려가는 시간 = 현재 시간 + 계단 길이
	                stairOutTime.add(time + stairLength);
	            } 
	            else {
	                // 아직 도착하지 않았거나 대기 시간이 안 되었으면 다음 사람으로 넘어가지 않음
	                break;
	            }
	        }
	    }
	    return time;
	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {

			// 방 한 변의 길이 N 입력
			N = Integer.parseInt(br.readLine().trim());
			
			// 방 초기 입력
			person = new ArrayList<>();
			stair = new ArrayList<>();
			initMap = new int[N][N];
			stairLength = new int[2]; // 계단 길이 배열 (최대 두 개의 계단)
			for (int row = 0; row < N; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = 0; col < N; col++) {
					initMap[row][col] = Integer.parseInt(st.nextToken());
					
					// 사람인강 1
					if (initMap[row][col] == 1) {
						person.add(new Point(row, col));
					}
					
					// 계단인강 2이상
					if (initMap[row][col] >= 2) {
						stair.add(new Point(row, col));
						stairLength[stair.size() - 1] = initMap[row][col]; // 계단 길이 저장
					}
				}
			}
			
			// 계산 시작
			int ans = simulation();
			sb.append('#').append(test_case).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}
}
