import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_2477_차량정비소_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	/*
	 * 고객만족도 설문지에 고객이 이용했던 접수 창구번호와 정비 창구번호가 있음
	 * 이 정보를 이용하여 전화로 확인할 고객을 정하려고 함
	 * 차량 정비소에는 N개의 접수 창구와 M개의 정비 창구가 있음
	 * 
	 * 접수 창구 번호 : 1 ~ N
	 * 정비 창구 번호 : 1 ~ M
	 * 
	 * 차량 정비하는 단계
	 * 	1. 접수 창구에서 고장을 접수
	 * 	2. 정비 창구에서 차량을 접수 
	 * 
	 * 각 창구의 우선 순위
	 * 접수 창구의 우선순위
	 * 	1. 여러 고객이 기다리고 있는 경우 고객번호가 낮은 순서대로 우선 접수
	 * 	2. 빈 창구가 여러 곳인 경우 접수 창구번호가 작은 곳으로 감
	 * 
	 * 정비 창구의 우선순위
	 * 	1. 먼저 기다리는 고객을 우선시 함
	 * 	2. 두 명 이상의 고객들이 접수 창구에서 동시에 접수를 완료하고 정비 창구로 이동한 경우, 이용했던 접수 창구번호가 작은 고객을 우선
	 * 	3. 빈 창구가 여러 곳인 경우 정비 창구번호가 작은 곳으로 감 
	 * 
	 * [입력]
	 * 테스트 케이스 입력
	 * 	- 접수 창구의 개수 N, 정비 창구의 개수 M, 차량 정비소를 방문한 고객의 수 K, 
	 * 	  지갑을 두고 간 고객이 이용한 접수 창구번호 A와 정비창구번호 B
	 * 	- 각 접수 창구마다 일을 처리하는 시간들이 N개 주어짐
	 * 	- 각 정비 창구마다 일을 처리하는 시간들이 M개 주어짐
	 * 	- 고객의 도착 시간 주어짐 
	 *  
	 * [출력]
	 * 지갑을 분실한 고객과 같은 접수 창구와 같은 정비 창구를 이용한 고객의 고객번호들을 찾아 그 합을 출력
	 * 그런 고객이 없을 경우 -1 출력
	 */

	static int N, M, K, A, B;
	static int[] receptionDesk;
	static int[] repairDesk;
	static int[] visitTime;

	static int result;

	public static void simulation() {
		// while문을 통해 모든 사람을 다 돌때까지 파악하기

		// 진행 시간
		int time = 0;
		// 나간 사람의 수
		int outCount = 0;
		// 현재 도착하는 인원
		int visitIndex = 1;

		// 접수창구 기다리는 큐
		Queue<Integer> waitReceptionDesk = new LinkedList<>();
		// 접수창구에서 일을하는 큐
		int[] receptionTime = new int[N + 1];
		// 사용중인 창구에서 누가 사용했는지 저장
		int[] receptionCustomer = new int[N + 1];
		int[] usedReceptionDesk = new int[K + 1]; // 고객번호별 사용 접수창구

		// 정비창구 기다리는 큐
		Queue<Integer> waitRepairDesk = new LinkedList<>();
		// 정비창구에서 일을하는 큐
		int[] repairTime = new int[M + 1];
		int[] repairCustomer = new int[M + 1]; // 정비창구에 있는 고객번호

		while (true) {

			// 현재 시간과 도착 시간 파악해서 도착했으면 접수창구 대기 큐에 넣기
			while (visitIndex <= K && visitTime[visitIndex] <= time) {
				waitReceptionDesk.add(visitIndex++);
			}

			// 접수창구에서 나갈 시간이 되었으면 정비창구 기다리는 곳으로 보내버리고
			for (int i = 1; i <= N; i++) {
				if (receptionTime[i] == time) {
					// 고객이 접수를 하고 있었을 경우만
					if (receptionCustomer[i] != 0) {
						waitRepairDesk.add(receptionCustomer[i]);
						usedReceptionDesk[receptionCustomer[i]] = i; // 고객번호별 사용 접수창구 기록
						receptionCustomer[i] = 0;
					}
					// 접수 창구 종료 시간 초기화
					receptionTime[i] = 0;
				}
			}

			// 접수 창구 비어있으면 접수 창구에 넣기
			for (int i = 1; i <= N; i++) {
				if (waitReceptionDesk.isEmpty())
					break;
				if (receptionTime[i] == 0) {
					int customer = waitReceptionDesk.poll();
					receptionCustomer[i] = customer; // 접수 창구에 배치
					receptionTime[i] = time + receptionDesk[i]; // 접수 종료 시간 계산
				}
			}

			// 정비가 끝났으면 내보내기
			for (int i = 1; i <= M; i++) {
				if (repairTime[i] == time && repairCustomer[i] != 0) {
					repairTime[i] = 0;
					repairCustomer[i] = 0;
					outCount++;
				}
			}

			// 정비 창구 비어있으면 정비 창구에 넣기
			for (int i = 1; i <= M; i++) {
				if (waitRepairDesk.isEmpty())	break;
				if (repairTime[i] == 0) {
					int customer = waitRepairDesk.poll();
					repairCustomer[i] = customer; // 정비 창구에 배치
					repairTime[i] = time + repairDesk[i]; // 정비 종료 시간 계산

					// 지갑 분실 고객 조건 체크
					if (usedReceptionDesk[customer] == A && i == B) {
						result += customer;
					}
				}
			}

			// 나간사람 수가 방문한 인원수와 동일하면 종료
			if (outCount == K)	break;

			// 다음 이벤트 시간으로 이동
			int nextTime = Integer.MAX_VALUE;
			for (int i = 1; i <= N; i++) {
				if (receptionTime[i] != 0) {
					nextTime = Math.min(nextTime, receptionTime[i]);
				}
			}
			for (int i = 1; i <= M; i++) {
				if (repairTime[i] != 0) {
					nextTime = Math.min(nextTime, repairTime[i]);
				}
			}
			if (visitIndex <= K) {
				nextTime = Math.min(nextTime, visitTime[visitIndex]);
			}
			time = nextTime;
		}

	}

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			// 접수 창구 처리 시간 입력
			receptionDesk = new int[N + 1];
			st = new StringTokenizer(br.readLine().trim());
			for (int i = 1; i <= N; i++) {
				receptionDesk[i] = Integer.parseInt(st.nextToken());
			}

			// 정비 창구 처리 시간 입력
			repairDesk = new int[M + 1];
			st = new StringTokenizer(br.readLine().trim());
			for (int i = 1; i <= M; i++) {
				repairDesk[i] = Integer.parseInt(st.nextToken());
			}

			// 고객 도착 시간 입력
			visitTime = new int[K + 1];
			st = new StringTokenizer(br.readLine().trim());
			for (int i = 1; i <= K; i++) {
				visitTime[i] = Integer.parseInt(st.nextToken());
			}

			result = 0;
			simulation();

			// 결과값이 0이면 이것은 일치하는 값이 없었다는 것
			if (result == 0)	result = -1;

			sb.append('#').append(test_case).append(' ').append(result).append('\n');
		}
		System.out.println(sb);
	}
}
