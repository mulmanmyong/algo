import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15961_회전초밥_1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

/*
 * 회전 초밥 음식점에는 회전하는 벨트 위에 여러 가지 종류의 초밥이 접시에 담겨 있고,
 * 손님은 이 중에서 자기가 좋아하는 초밥을 골라서 먹음
 * 초밥의 종류를 번호로 표현
 * 
 * 2가지 행사를 통해 매상 올리고자 함
 * 	1. 원래 회전 초밥은 손님이 마음대로 초밥을 고르고, 
 *     먹은 초밥만큼 식대를 계산, 
 *     벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 제공
 * 	2. 각 고객에게 초밥의 종류 하나가 쓰인 쿠폰을 발행하고
 *     1번 행사에 참가할 경우 이 쿠폰에 적혀진 종류의 초밥 하나를 추가로 무료로 제공
 *     만약 이 번호에 적혀진 초밥이 현재 벨트 위에 없을 경우
 *     요리사가 새로 만들어 새로 제공 
 *     
 *     
 * [입력]
 * 첫번째 줄에 회전 초밥 벨트에 놓인 접시의 수 N, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰번호 c가 하나의 빈칸을 사이에 두고 주어짐
 * N개의 줄에 거쳐 벨트의 한 위취부터 시작하여 회전 방향을 따라갈 때 초밥의 종류를 나타내는 1이상 d이하의 정수가 각 줄마다 하나씩 주어짐 
 * 
 * [출력]
 * 주어진 회전 초밥 벨트에서 먹을 수 있는 초밥의 가짓수의 최댁밧을 하나의 정수로 출력
 * 
 */
	
	static int N, d, k, c;
	static int[] sushi;
	
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		sushi = new int[N];
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine().trim());
		}
		
		// 초밥을 먹을 수 있는 최대 개수를 구한다.
		// 일단은 k개를 연속으로 먹는 것이 우선시 된다. 그래야 쿠폰을 받으니깐.
		// 그리하여 연속으로 먹는 경우를 담는데, 중복이 되면 또 스킵하기.
		// 그리고 쿠폰을 포함하는 지 안하는 지 확인하기 
		
		// 각 초밥이 들어있는 지 확인하는 배열
		int[] count = new int[d+1];
		// 각 초밥종류가 들어있는 개수 
		int unique = 0;
		
		// 슬라이딩 윈도우 초기화
		for (int i = 0; i < k; i++) {
			if (count[sushi[i]] == 0)	unique++;
			count[sushi[i]]++;
		}
		// c 포함하는 지 확인
		if (count[c] == 0)	unique++;
		
		// 최대값 
		int max = unique;
		// k-1+N까지 돌기
		for (int i = 0; i < N; i++) {
			
			// 쿠폰으로 받은 것은 지우기 
			if (count[c] == 0)	unique--;
			
			// 앞에껄 지우기 
			int remove = sushi[i];
			count[remove]--;
			if (count[remove] == 0)	unique--;
			
			// 뒤에껄 넣기
			int add = sushi[(i+k)%N];
			if (count[add] == 0) unique++;
			count[add]++;
			
			// 쿠폰 여부 확인하기
			if (count[c] == 0)	unique++;
			
			// 갱신하기 
			max = Math.max(max, unique);			
		}
		
		sb.append(max);
		System.out.println(sb);
	}
}
