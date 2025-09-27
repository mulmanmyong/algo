import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_2819_격자판의숫자이어붙이기_1 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;
    
    // 테스트 케이스 입력
    static int testCase;
    // 격자판 크기 N
    static final int N = 4;
    // 격자판 정보
    static int[][] grid;
    // 중복확인을 위한 set
    static Set<String> chk;
    
    // 상하좌우 이동 배열
    static int[] deltaRow = {-1, 1, 0, 0};
    static int[] deltaCol = {0, 0, -1, 1};
    
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        testCase = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1; test_case <= testCase; ++test_case) {
            // 격자판 정보 입력
            grid = new int[N][N];
            for (int row = 0; row < N; row++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int col = 0; col < N; col++) {
                    grid[row][col] = Integer.parseInt(st.nextToken());
                }
            }
            
            chk = new HashSet<>();
            
            // 모든 칸에서 DFS 시작
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                	// 숫자를 문자열로 담기 
                    dfs(row, col, 1, String.valueOf(grid[row][col]));
                }
            }
            
            sb.append('#').append(test_case).append(' ').append(chk.size()).append('\n');
        }
        System.out.println(sb);
    }
    
    // DFS 탐색
    static void dfs(int row, int col, int depth, String number) {
        // 7개의 숫자를 만들었으면 chk에 추가 (HashSet의 성질 이용)
        if (depth == 7) {
            chk.add(number);
            return;
        }
        
        for (int dir = 0; dir < 4; dir++) {
            int newRow = row + deltaRow[dir];
            int newCol = col + deltaCol[dir];
            
            // 범위 벗어나는지?
            if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) 	continue;
            
            // 벗어나지 않는다면, 현재의 숫자 더해서 넘기기 
            dfs(newRow, newCol, depth + 1, number + grid[newRow][newCol]); 
        }
    }
}
