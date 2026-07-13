import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        // c++ 시간초과나서 java로 그대로 변경
        // 질문하기를 보아하니 c++이 백준 섭종 이후 이러한 현상 많이 발생
        
        // 흰색 부분은 갈 수 있는 길, 검은색 부분은 막힌 길
        // 동 서 남 북 방향으로 한 칸씩 이동, 맵 벗어날 수 없음
        // 상대팀 진형 최단으로 도달하는 방안, 도달못하면 -1

        // 완탐은 테토 bfs로 진행
        int row = maps.length;
        int col = maps[0].length;

        int[] deltaRow = {-1, 0, 1, 0};
        int[] deltaCol = {0, -1, 0, 1};

        // bfs 처리 deque
        Deque<int[]> dq = new ArrayDeque<>();

        int answer = -1;

        // 시작은 좌측 상단, 종료는 우측 하단
        dq.offerLast(new int[] {0, 0});

        // q가 빌때까지 탐색, 다 빌때까지 answer를 갱신못하면 -1그대로 return 목적지 도달 실패
        while (!dq.isEmpty()) {
            int[] current = dq.pollFirst();

            int currentRow = current[0];
            int currentCol = current[1];
            int currentDistance = maps[currentRow][currentCol];

            // 먼저 목적지 도달하면 그것이 곧 최단 거리
            if (currentRow == row - 1 && currentCol == col - 1) {
                answer = currentDistance;
                break;
            }

            // 아직 도달 못했으면 상 하 좌 우 탐색
            for (int dir = 0; dir < 4; dir++) {
                int nextRow = currentRow + deltaRow[dir];
                int nextCol = currentCol + deltaCol[dir];

                // 범위 벗어났는지 확인, 벗어나면 패스
                if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col) {
                    continue;
                }

                // 벽이거나 이미 방문한 곳인지 확인, 벽이거나 방문했으면 패스
                if (maps[nextRow][nextCol] != 1) {
                    continue;
                }

                // 갈 수 있으면 다음으로 이동
                maps[nextRow][nextCol] = currentDistance + 1;
                dq.offerLast(new int[] {nextRow, nextCol});
            }
        }

        return answer;
    }
}