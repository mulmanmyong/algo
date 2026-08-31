import java.util.*;

class Solution {
    public int solution(String[] maps) {
        // 5개의 문자들로 이루어짐
        // S : 시작 지점, E : 출구, L : 레버, O : 통로, X : 벽
        // 통로로 된 칸으로만 이동 가능
        
        // S, E, L은 각각 한번씩만 존재하고, 왔던 길 다시 갈 수 있음
        // 근데 S -> L 그리고 L -> E 이런식으로 진행해야함
        
        // 모든 이동 시간이 1로 동일하기 때문에
        // BFS를 이용하면 각 지점까지의 최단거리를 구할 수 있음
        // 즉, BFS를 2번 돌리면 된다
        
        int startRow = 0;
        int startCol = 0;
        int leverRow = 0;
        int leverCol = 0;
        int exitRow = 0;
        int exitCol = 0;
        
        // 시작 지점, 레버, 출구 위치 찾기
        for (int row = 0; row < maps.length; row++) {
            for (int col = 0; col < maps[row].length(); col++) {
                char current = maps[row].charAt(col);
                
                if (current == 'S') {
                    startRow = row;
                    startCol = col;
                }
                else if (current == 'L') {
                    leverRow = row;
                    leverCol = col;
                }
                else if (current == 'E') {
                    exitRow = row;
                    exitCol = col;
                }
            }
        }
        
        // 시작 지점에서 레버까지의 최단거리
        int startToLever = bfs(maps, startRow, startCol, leverRow, leverCol);
        
        // 레버에 도달할 수 없다면 탈출 불가능
        if (startToLever == -1) {
            return -1;
        }
        
        // 레버에서 출구까지의 최단거리
        int leverToExit = bfs(maps, leverRow, leverCol, exitRow, exitCol);
        
        // 출구에 도달할 수 없다면 탈출 불가능
        if (leverToExit == -1) {
            return -1;
        }
        
        int answer = startToLever + leverToExit;
        return answer;
    }
    
    public int bfs(String[] maps, int startRow, int startCol, int targetRow, int targetCol) {
        // 상하좌우 이동
        int[] deltaRow = {-1, 1, 0, 0};
        int[] deltaCol = {0, 0, -1, 1};
        
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        
        // queue에는 row, col, 이동거리를 저장
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startRow, startCol, 0});
        
        visited[startRow][startCol] = true;
        
        while (!q.isEmpty()) {
            int currentRow = q.peek()[0];
            int currentCol = q.peek()[1];
            int currentDistance = q.peek()[2];
            q.poll();
            
            // 목표 지점에 도착했다면 현재까지 이동거리 반환
            if (currentRow == targetRow && currentCol == targetCol) {
                return currentDistance;
            }
            
            // 상하좌우 탐색
            for (int dir = 0; dir < 4; dir++) {
                int nextRow = currentRow + deltaRow[dir];
                int nextCol = currentCol + deltaCol[dir];
                
                // 맵의 범위를 벗어나는 경우
                if (nextRow < 0 || nextRow >= maps.length ||
                    nextCol < 0 || nextCol >= maps[nextRow].length()) {
                    continue;
                }
                
                // 벽이거나 이미 방문한 경우
                if (maps[nextRow].charAt(nextCol) == 'X' || visited[nextRow][nextCol]) {
                    continue;
                }
                
                visited[nextRow][nextCol] = true;
                q.offer(new int[]{nextRow, nextCol, currentDistance + 1});
            }
        }
        
        // 목표 지점까지 갈 수 없는 경우
        return -1;
    }
}