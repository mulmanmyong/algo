import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1210_Ladder1_1 {

    public static final int SIZE = 100;
    public static int[][] ladder;
    public static int findArriveX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
       for (int test_case = 1; test_case <= 10; test_case++) {
    	   // 테스트케이스는 10개
    	   // 테스트케이스 앞에 테스트 케이스 번호가 나옴
    	   int T = Integer.parseInt(br.readLine());
    	   
    	   // 사다리 구조 입력  
    	   ladder = new int[SIZE][SIZE];
    	   for (int row = 0; row < SIZE; row++) {
    		   StringTokenizer st = new StringTokenizer(br.readLine());
    		   for (int column = 0; column < SIZE; column++) {
    			   ladder[row][column] = Integer.parseInt(st.nextToken());
    		   
    			   // 도착지점 2 좌표 찾기
    			   if (ladder[row][column] == 2) {
    				   findArriveX = column;
    			   }
    		   }
    	   }
    	   
    	   System.out.println("#"+T+" "+findStartX(findArriveX, SIZE - 1));
       }
    }

    public static int findStartX(int x, int y) {
        // 기저 조건: y가 0 (맨 위)에 도달하면 x를 반환
        if (y == 0) {
            return x;
        }

        // 왼쪽으로 이동 가능한지 확인
        if (x > 0 && ladder[y][x - 1] == 1) {
            int nextX = x;
            
            // 왼쪽으로 갈 수 있는만큼 이동  
            while (nextX > 0 && ladder[y][nextX - 1] == 1) {
                nextX--;
            }
            return findStartX(nextX, y-1); // 왼쪽으로 갈만큼 갔으니, 이제 위로 이동 
        }
        // 오른쪽으로 이동 가능한지 확인
        else if (x < SIZE - 1 && ladder[y][x + 1] == 1) {
            int nextX = x;
            
            // 오른쪽으로 갈 수 있는만큼 이동 
            while (nextX < SIZE - 1 && ladder[y][nextX + 1] == 1) {
                nextX++;
            }
            return findStartX(nextX, y-1); // 오른쪽으로 갈만큼 갔으니, 이제 위로 이동  
        }
        // 위로 이동 
        else {
            return findStartX(x, y - 1);
        }
    }
}