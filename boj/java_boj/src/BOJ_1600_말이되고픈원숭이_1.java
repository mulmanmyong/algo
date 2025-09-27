import java.io.*;
import java.util.*;

public class BOJ_1600_말이되고픈원숭이_1 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
	
    static class Point {
        int row;
        int column;
        int moveLikeHorseCount;

        Point(int row, int column, int moveLikeHorseCount) {
            this.row = row;
            this.column = column;
            this.moveLikeHorseCount = moveLikeHorseCount;
        }
    }
    
    static int K;
    static int W, H;
    static int[][] board;
    static int[][][] visited;
    static int answer;
    
    // 맨 왼쪽 위에서 맨 오른쪽 아래까지 가려고 함
    // 말(나이트)처럼 이동 가능
    static int[] moveLikeHorseRow = {-1, 1, -2, 2, -2, 2, -1, 1};
    static int[] moveLikeHorseColumn = {-2, -2, -1, -1, 1, 1, 2, 2};
    
    // 그 외에는 인접 상하좌우
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaColumn = {0, 0, -1, 1};
    
    public static void simulation() {
        // 0은 아무것도 없는 평지, 1은 장애물
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, 0)); // 출발은 왼쪽 맨 위
        
        // 방문 했니? 그리고 말처럼 이동했니?, 말이동경우랑 안이동 경우 2가지를 모두 queue에 담으며 방문
        visited = new int[H][W][K + 1];
        visited[0][0][0] = 1;

        answer = -1; // while문을 탈출할때 까지 값이 변경안되면, 목적지에 도달할 수 없었던 것
        while (!q.isEmpty()) {
            Point current = q.poll();
            int row = current.row;
            int column = current.column;
            int moveLikeHorseCount = current.moveLikeHorseCount;

            // 현재의 row, column, moveLikeHorseCount중에서
            // row와 column이 목적지 좌표라면, 누군가 먼저 도달한 것
            if (row == H - 1 && column == W - 1) {
                answer = visited[row][column][moveLikeHorseCount] - 1; // visited의 시작이 1이었기 때문
                break;
            }

            // 인접 4방향 이동의 경우
            for (int dir = 0; dir < 4; dir++) {
                int newRow = row + deltaRow[dir];
                int newColumn = column + deltaColumn[dir];

                // 배열의 범위를 벗어날 경우
                if (newRow < 0 || newRow >= H || newColumn < 0 || newColumn >= W) continue;
                // 이동 위치에 장애물이 있거나 이미 방문했을 경우
                if (board[newRow][newColumn] == 1 || visited[newRow][newColumn][moveLikeHorseCount] != 0) continue;

                // 그 외에는 이동 가능
                visited[newRow][newColumn][moveLikeHorseCount] = visited[row][column][moveLikeHorseCount] + 1;
                q.add(new Point(newRow, newColumn, moveLikeHorseCount));
            }

            // 말(나이트)처럼 이동의 경우, 이동가능 횟수 안에서만
            if (moveLikeHorseCount < K) {
                for (int dir = 0; dir < 8; dir++) {
                    int newRow = row + moveLikeHorseRow[dir];
                    int newColumn = column + moveLikeHorseColumn[dir];

                    // 배열의 범위를 벗어날 경우
                    if (newRow < 0 || newRow >= H || newColumn < 0 || newColumn >= W) continue;
                    // 이동 위치에 장애물이 있거나 이미 방문했을 경우
                    if (board[newRow][newColumn] == 1 || visited[newRow][newColumn][moveLikeHorseCount + 1] != 0) continue;

                    // 그 외에는 이동 가능
                    visited[newRow][newColumn][moveLikeHorseCount + 1] = visited[row][column][moveLikeHorseCount] + 1;
                    q.add(new Point(newRow, newColumn, moveLikeHorseCount + 1));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();


        // 근데 해당 이동횟수는 k번 가능
        K = Integer.parseInt(br.readLine());
        
        // 격자판 크기, 가로 W, 세로 H
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 격자판 입력
        board = new int[H][W];
        for (int row = 0; row < H; row++) {
            st = new StringTokenizer(br.readLine());
            for (int column = 0; column < W; column++) {
                board[row][column] = Integer.parseInt(st.nextToken());
            }
        }
        
        simulation();
        
        sb.append(answer);
        System.out.println(sb);
    }
}