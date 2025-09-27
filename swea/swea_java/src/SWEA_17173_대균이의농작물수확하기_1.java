import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_17173_대균이의농작물수확하기_1 {
    /*
    로봇은 바라보고 있는 방향을 기준으로 오른쪽, 앞, 왼쪽, 뒤 순서로 확인하면서 이동
    1일 동안 아래와 같은 작업을 수행
    - 오전
      오후에 이동할 수 있는 영역이 있다면, 2가지 중 1가지 수행
      1. 씨앗을 심거나
      2. 다 자란 농작물을 수확하기
      - 이동할 수 없으면 그 자리에 그대로 있음(4면에 다 씨앗이 있고, 수확할 수 있는 농작물이 없는 경우일듯)

    - 오후
      오전에서 판단한 내용을 토대로 이동할 수 있는 영역으로 이동

    씨앗은 심은 후 다음 날 싹이 트고, K+3일이 지나 수확할 수 있다. K는 씨앗을 심는 회차를 의미


    입력
      첫째 줄에 테스트 케이스 개수가 주어짐
      각 테스트 케이스마다
        첫째줄에 영억의 크기 N, 로봇이 일하는 기간 D 주어짐
        그 다음에 영역의 정보가 주어짐 (산:1, 농작물 수확할 수 있는 영역:0)

    출력
      일하는 기간 동안 최대로 수확할 수 있는 농작물의 개수 출력하기
    */

    // 테스트 케이스
    static int testCase;
    // 영역의 크기 : N, 로봇이 일하는 기간 : D
    static int N, D;
    // 로봇이 동작할 영역 6~10, 즉, 최대 10
    static int[][] arr = new int[10][10];
    
    // 파종 정보를 기록할 배열 추가
    static int[][] plantDay = new int[10][10];
    // 정답 코드의 plantRound와 같은 역할
    static int[][] plantK = new int[10][10]; 

    // 방향 지정하기
    // 우(0) 상(1) 좌(2) 하(3)
    static int[] deltaRow = {0, -1, 0, 1}; 
    static int[] deltaColumn = {1, 0, -1, 0};

    // C++ pair를 대체하기 위한 간단한 위치 클래스
    static class Position {
        int row, col;
        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    // 현재 상황을 기준으로 simulation 진행
    static int simulation(int row, int column, int dir) {
        int currentCount = 0;
        int currentRow = row;
        int currentColumn = column;
        int currentDirection = dir;

        // 시뮬레이션마다 사용할 로컬 배열을 만들어 상태를 관리
        int[][] plantDay = new int[N][N];
        int[][] plantK = new int[N][N];

        // D일동안 작업을 수행
        for (int day = 1; day <= D; day++) {
            boolean canMove = false;
            int nextMoveRow = -1, nextMoveCol = -1, nextMoveDir = -1;
            
            // 로봇의 방향 기준 (오른쪽, 앞, 왼쪽, 뒤) 우선순위
            // (0:우, 1:상, 2:좌, 3:하) 기준 상대 방향 오프셋
            int[] priority = {3, 0, 1, 2}; 

            for (int p : priority) {
                int ndir = (currentDirection + p) % 4;
                int nr = currentRow + deltaRow[ndir];
                int nc = currentColumn + deltaColumn[ndir];

                if (nr < 0 || nr >= N || nc < 0 || nc >= N || arr[nr][nc] != 0) continue;
                
                // 성장 기간 K+4일 적용(심고 다날이 싹이 트기 때문ㅇ)
                boolean isBlocked = plantDay[nr][nc] > 0 && (day - plantDay[nr][nc] < plantK[nr][nc] + 4);

                if (!isBlocked) {
                    canMove = true;
                    nextMoveRow = nr;
                    nextMoveCol = nc;
                    nextMoveDir = ndir;
                    break;
                }
            }

            // 오전 행동: 이동할 수 있을 때만 행동
            if (canMove) {
                // 성장 기간 K+4일 적용
                // today >= plantDay + 1 + K + 3  ->  today - plantDay >= K + 4
                boolean isHarvestable = plantDay[currentRow][currentColumn] > 0 && (day - plantDay[currentRow][currentColumn] >= plantK[currentRow][currentColumn] + 4);
                
                if (isHarvestable) {
                    currentCount++;
                    plantDay[currentRow][currentColumn] = 0;
//                    plantK[currentRow][currentColumn] = 0;
                } else if (plantDay[currentRow][currentColumn] == 0) { 
                    // 해당 칸의 K를 1 증가시키고 기록
                    plantK[currentRow][currentColumn]++;
                    plantDay[currentRow][currentColumn] = day;
                }
            }
            
            // 오후 이동
            if (canMove) {
                currentRow = nextMoveRow;
                currentColumn = nextMoveCol;
                currentDirection = nextMoveDir;
            }
        }
        return currentCount;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        testCase = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= testCase; ++test_case) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            Queue<Position> startPosition = new ArrayDeque<>();
            for (int row = 0; row < N; row++) {
                st = new StringTokenizer(br.readLine());
                for (int column = 0; column < N; column++) {
                    arr[row][column] = Integer.parseInt(st.nextToken());
                    if (arr[row][column] == 0) startPosition.add(new Position(row, column));
                }
            }

            int maxCount = 0;
            // 모든 시작점에서 모든 방향으로 시뮬레이션 실행 (완전 탐색)
            while (!startPosition.isEmpty()) {
                Position pos = startPosition.poll();
                for (int startDirection = 0; startDirection < 4; startDirection++) {
                    maxCount = Math.max(maxCount, simulation(pos.row, pos.col, startDirection));
                }
            }
            sb.append('#').append(test_case).append(' ').append(maxCount).append('\n');
        }
        System.out.print(sb);
    }
}