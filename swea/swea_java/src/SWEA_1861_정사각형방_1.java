import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1861_정사각형방_1 {
    
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int roomCount;
    public static int[][] rooms;
    
    // 상, 하, 좌, 우 
    public static int[] deltaRow = {-1, 1, 0, 0};
    public static int[] deltaColumn = {0, 0, -1, 1};
    
    public static void main(String[] args) throws Exception {
    	br = new BufferedReader(new InputStreamReader(System.in));
    	sb = new StringBuilder();
    	
    	// 테스트 케이스 입력 받기  
        int testCase = Integer.parseInt(br.readLine().trim());
        for(int test_case = 1; test_case <= testCase; ++test_case) {
            // 방의 개수 입력 받기  
        	roomCount = Integer.parseInt(br.readLine().trim());
            
        	// 방 상태 입력 받기 
            rooms = new int[roomCount][roomCount];
            for(int rowIndex = 0; rowIndex < roomCount; rowIndex++) {
            	st = new StringTokenizer(br.readLine().trim());
                for(int columnIndex = 0; columnIndex < roomCount; columnIndex++) {
                    rooms[rowIndex][columnIndex] = Integer.parseInt(st.nextToken());
                }
            }
            
            // 처음 출발해야하는 방과 그 때의 최대 이동할 수 있는 횟수 
            int roomNumber = -1;
            int maximumCount = -1;
            
            // 모든 시작 지점을 탐색
            int currentCount;
            for (int rowIndex = 0; rowIndex < roomCount; rowIndex++) {
            	for (int columnIndex = 0; columnIndex < roomCount; columnIndex++) {
            		// 현재의 좌표 출발했을 때의 이동횟수 
            		currentCount = findMoveRoomCount(rowIndex, columnIndex);
            		
            		// 현재가 최대면 
            		if (currentCount > maximumCount) {
            			maximumCount = currentCount; // 횟수 갱신  
            			roomNumber = rooms[rowIndex][columnIndex]; // 방번호 갱신 
            		}
            		// 방문 최대 횟수가 동일하다면, 방번호가 작은 수를 저장 
            		else if (currentCount == maximumCount) { // 최대 횟수가 동일하고 
            			if (roomNumber > rooms[rowIndex][columnIndex]) { // 방번호가 지금이 더 작다면 
            				// 현재의 방번호로 갱신 
            				roomNumber = rooms[rowIndex][columnIndex];
            			}
            		}
            	}
            }
            
            // 결과 저장 #테케 방번호 방문횟수  
            sb.append('#').append(test_case).append(' ').append(roomNumber).append(' ').append(maximumCount).append('\n');
        }
        
        // 결과 출력 
        System.out.println(sb);
    }
    
    
    public static int findMoveRoomCount(int row, int column) {        
        int moveCount = 1; // 시작은 나만 
    	
    	for(int direction = 0; direction < 4; direction++) {
            int newRow = row + deltaRow[direction];
            int newColumn = column + deltaColumn[direction];
            
            // 범위를 벗어나면 continue 
            if (newRow < 0 || newRow >= roomCount || newColumn < 0 || newColumn >= roomCount) continue;
            // 현재의 위치의 방번호보다 다음방번호가 1크면 이동 
            if (rooms[newRow][newColumn] == rooms[row][column] + 1) {
            	moveCount += findMoveRoomCount(newRow, newColumn);
            	break; // 이동할 방을 찾았으니 다른 방향 탐색은 필요 x 
            }
        }
    	
    	// 모든 방 탐색 후 결과 반환 
    	return moveCount;
    }
}