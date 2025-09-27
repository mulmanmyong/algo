import java.io.*;
import java.util.*;

public class SWEA_5653_줄기세포배양_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * 줄기세포 배양 시뮬레이션 프로그램 만들기
 * * 배양 용기에 도포한 후 일정 시간 동안 배양을 시킨 후 줄기 세포의 개수가 몇 개가 되는지 계산하는 시뮬레이션 프로그램을 만들어야 함
 * 하나의 줄기 세포는 가로, 세로 크기가 1인 정사각형 형태로 존재하며 배양 용기는 격자 그리드로 구성
 * 하나의 그리드 셀은 1x1 정사각형
 * * 각 줄기 세포는 생명력이라는 수치를 가지고 있음
 * 초기 상태에서 줄기 세포들은 비활성화 상태, 
 * 생명력 수치가 X인 줄기 세포의 경우 X시간 동안 비활성 상태이고 X시간이 지나는 순간 활성 상태가 됨 
 * 줄기 세포가 활성 상태가 되면 X시간 동안 살아있을 수 있으며 X시간이 지나면 세포는 사망
 * 세초가 죽으면 소멸되는 것이 아니라 그 상태로 그리드 셀에 알박기 함
 * * 활성화된 줄기 세포 첫 1시간 동안 상, 하, 좌, 우 네 방향으로 동시에 번식
 * 번식된 줄기 세포는 비활성화 상태
 * 이미 줄기세포가 존재하는 경우 해당 방향으로 추가적 번식 안함
 * 두 개 이상의 줄기 세포가 하나의 그리드 셀에 동시 번식하려고 하는 경우 생명력 수치가 높은 줄기 세포가 차지함 
 * * 줄기 세포의 초기 상태 정보와 배양 시간 K시간이 주어질 때, 
 * K시간 후 살아있는 줄기 세포(비활성 상태 + 활성 상태)의 총 개수를 구하는 프로그램을 작성
 * * [입력]
 * 입력의 가장 첫 줄에는 총 테스트 케이스의 개수 T 주어짐
 * 각 테스트케이스의 첫째 줄에는 초기 상태에서 줄기 세포가 분포된 세로 크기 N, 가로 크기 M, 배양 시간 K 순서대로 주어짐
 * 그리드 상태 정보 주어짐 
 * 1~10은 해당 그리드 셀에 위치한 줄기 세포의 생명력을 의미
 * 0인 경우 줄기 세포가 존재하지 않는 그리드 
 * * [출력]
 * 배양을 K시간 시킨 후 배양 용기에 있는 살아있는 줄기 세포(비활성상태 + 활성 상태)의 개수를 출력
 * * []
 * 한칸씩 확장하는 거니깐 50, 50 부터 시작하여 최대 300시간 배양하면 ,, 50, 50 배열에 상 하 좌 우로 300씩 패딩
 * 그러면 초기 전체 보드의 크기는 650*650 정도 되려나 padding, padding이 좌측 상단이 될 수 있도록 하기 
 * 거기를 기점으로 입력 받기 
 * * 그리고 확장 시켜보기?
 */
	
	// 셀의 정보를 저장할 클래스
	static class Cell {
		// 좌표, 비활성이나 활성상태동안 있었던 시간, 활성상태인지 비활성화 상태인지, 생명력
		int row, col, time, flag, life;
		public Cell(int row, int col, int time, int flag, int life) {
			super();
			this.row = row;
			this.col = col;
			this.time = time;
			this.flag = flag;
			this.life = life;
		}
	}
	
	// 세로 크기 N, 가로 크기 M, 배양 시간 K
	static int N;
	static int M;
	static int K;
	// 전체 그리드
	static int[][] map;
	// 배양할 큐
	static Queue<Cell> q;
	
	// 방향 배열 (상 하 좌 우)
	static int[] deltaRow = {-1, 1, 0, 0};
	static int[] deltaCol = {0, 0, -1, 1};

	public static void simulation() {
		// K 시간 동안 시뮬레이션을 진행
		for (int time = 0; time < K; time++) {
			
			// 다음 시간 단계의 세포들을 저장할 큐
			Queue<Cell> next = new LinkedList<>();
			
			// 새로 생성될 세포들의 정보를 저장할 임시 맵
			Map<String, Integer> newCells = new HashMap<>();
	
			// 현재 큐에 있는 모든 세포들 확인 후 업데이트
			while(!q.isEmpty()) {
				Cell cur = q.poll();
	
				// 비활성화 상태인 경우 : 0
				if (cur.flag == 0) {
					// 비활성화 상태인 시간이 생명력에 도달하면 활성 상태로 전환
					if (cur.time + 1 == cur.life) {
						next.add(new Cell(cur.row, cur.col, 0, 1, cur.life));
					} 
					// 아직 비활성화 상태인 경우
					else {
						next.add(new Cell(cur.row, cur.col, cur.time + 1, 0, cur.life));
					}
				}
				// 활성화 상태인 경우 : 1
				else if (cur.flag == 1) {
					// 처음 활성화 되었을 때
					if (cur.time == 0) {
						for (int dir = 0; dir < 4; dir++) {
							int newRow = cur.row + deltaRow[dir];
							int newCol = cur.col + deltaCol[dir];
							String key = newRow + "_" + newCol;

							// 기존 맵에 세포가 없어야 함
							if (map[newRow][newCol] == 0) {
								// 이미 번식 후보가 있다면 생명력 비교하여 더 큰 값으로 갱신
								newCells.put(key, Math.max(newCells.getOrDefault(key, 0), cur.life));
							}
						}
					}
					// 생명력만큼 버텼는지 확인
					if (cur.time + 1 < cur.life) {
						// 활성 상태 유지
						next.add(new Cell(cur.row, cur.col, cur.time + 1, 1, cur.life));
					} 
				}
				// 사망 상태인 경우
				// 사망한 세포는 카운트 하지 않음
			}
			
			// 확산 후보들 최종 맵에 반영 및 큐에 추가
			for (String key : newCells.keySet()) {
				String[] parts = key.split("_");
				int newRow = Integer.parseInt(parts[0]);
				int newCol = Integer.parseInt(parts[1]);
				int life = newCells.get(key);

				// 최종 확정된 세포만 맵에 추가
				map[newRow][newCol] = life;
				// 새로 생성된 세포는 비활성 상태로 시작
				next.add(new Cell(newRow, newCol, 0, 0, life));
			}
			
			// 다음 시간 단계의 큐를 현재 큐로 교체
			q = next;
		}
	}
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {

			// 세로 크기 N, 가로 크기 M, 배양 시간 K 입력
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			// 패딩을 고려하여 맵 크기 설정
			// K시간 동안 최대 K만큼 확장 가능하므로 2*K의 패딩 필요
			map = new int[2*K + N][2*K + M];
			// 초기 그리드 상태 입력
			q = new LinkedList<>();
			
			for (int row = K; row < K + N; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for (int col = K; col < K + M; col++) {
					map[row][col] = Integer.parseInt(st.nextToken());
					
					// 빈공간이 아니면 초기 상태로 저장
					// 0 : 비활성화, 1 : 활성화, 2 : 사망
					if (map[row][col] > 0) {
						q.add(new Cell(row, col, 0, 0, map[row][col]));
					}
				}
			}
			
			simulation();
			// 시뮬레이션 종료 후 큐에 남아있는 세포의 개수가 살아있는 세포의 총 개수
			int ans = q.size();
			sb.append('#').append(test_case).append(' ').append(ans).append('\n');
		}
		System.out.println(sb);
	}
}