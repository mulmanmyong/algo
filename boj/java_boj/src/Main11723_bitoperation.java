import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main11723_bitoperation {
	
	public static int thisIsSet; // 비트 연산을 이용할 것이기 때문 
	public static String command; // 연산 수행을 위한 명령어 
	public static int x; // 집합에서 진행할 수 
	public static int commandCount; // 연산의 수 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		// 연산의 수를 입력 받음 
		commandCount = Integer.parseInt(br.readLine());
		
		// 집합 처음에는 공집합 : 0임
		thisIsSet = 0;
		// 연산을 입력받아서 진행 
		for (int testCase = 0; testCase < commandCount; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			command = st.nextToken();
			switch (command) {
				// 추가하는 경우 : x에 해당하는 비트를 1로 만든다.
				case "add" :
					x = Integer.parseInt(st.nextToken());
					// << 연산자로 해당위치를 표시할 비트를 만들고
					// 그 위치에 더하는 것이기 때문에 or연산자인 |를 사용해줌 
					thisIsSet |= (1 << (x-1)); // 1이면 왼쪽으로 비트 이동할 필요 없으므로 0이어야 된다. 그래서 x-1만큼 해줘야 함 
					break;
				// 삭제하는 경우 : x에 해당하는 비트를 0으로 만든다.
				case "remove" :
					x = Integer.parseInt(st.nextToken());
					// 지우는 것은 해당 위치만 & 연산자로 해당 위치만 0으로 해서 0으로 바꿔줘야 함 
					thisIsSet &= ~(1 << (x-1));
					break;
				// 확인하는 경우 : x가 있으면 1 없으면 0 
				case "check" :
					x = Integer.parseInt(st.nextToken());
					// check일 때는 출력
					// 집합에 x가 있으면 1 없으면 0 ,  해당 위치와 집합과 & 연산을 했을 때 있으면 0이 아니고 0이면 없는 것이다. (& 연산자로 인해 모두가 0이 되기 때문)
					if ((thisIsSet & (1 << (x-1))) == 0) {
						sb.append(0).append("\n");
					}
					else {
						sb.append(1).append("\n");
					}
					break;
				// 교환하는 경우 : 해당 위치가 1이면 0, 0이면 1로 바꾸기 
				case "toggle" :
					x = Integer.parseInt(st.nextToken());
					// XOR 연산으로 반전시키기. 즉, 1인 위치에 1이면 같으므로 0으로 되고, 0인 위치에 1이면 다르므로 1이 된다. 
					thisIsSet ^= (1 << (x-1));
					break;
				// 모든 비트를 1로 만들기 
				case "all" :
					// 해당 집합은 최대 20, 1에서 20번 밀면 21번째만 1이고 나머지 0 여기서 1을 빼면 20개의 비트가 모두 1로 된다.
					thisIsSet = (1 << 20) - 1;
					break;
				// 공집합으로 만든다. 모든 비트를 0으로 만들기 
				case "empty" :
					thisIsSet = 0;
					break;
			}
		}
		
		// 모든 연산을 수행 출력 
		System.out.println(sb);
	}
}
