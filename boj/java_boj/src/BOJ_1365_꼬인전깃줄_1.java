import java.io.*;
import java.util.*;

public class BOJ_1365_꼬인전깃줄_1 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
/*
 * [문제설명]
 * 전봇대가 2개 있음. 왼쪽과 오른쪽은 하나의 전선으로 연결되어 있음.
 * 어떤 전봇대도 두 개 이상의 다른 전봇대와 연결되어 있지 않음.
 * 
 * 전깃줄이 매우 꼬여있다는 문제가 있음.
 * 최소 개수로 전선을 잘라서 꼬인 것을 풀어라잉
 * 
 * [입력]
 * 첫째 줄에 전봇대의 개수 N 주어짐
 * 둘째 줄부터 N줄에 거쳐 전봇대 연결상태주어짐
 * 이는 i는 왼쪽 전봇대 번호를 의미하고, i번째에 입력되는 것은 i와 연결된 오른쪽 전봇대를 의미함
 * 
 * [출력]
 * 전선이 꼬이지 않으려면 최소 몇개의 전선을 잘라 내야하는 지를 첫째 줄에 출력 
 * 
 * [어캄]
 * 뒤에가는 숫자가 갑자기 앞쪽으로 가버리면 꼬여버리는 것이기 때문에
 * 우선 최장증가부분수열의 길이를 구해야함. 그리고 이 전체 전선의 개수에서 그 길이를 빼면 잘라야하는 최소 전선의 개수가 될 것임
 * N이 10만이다. 근데 제한시간이 1초다.. dp로 하면 N^2이 되어버려서 터져버릴 가능성이 있음.
 * 
 * 그래서 이분탐색을 이용하여 길이를 구해본다.
 */
	
	// 전봇대의 개수 N;
	static int N;
	// 전봇대 연결 상태
	static int[] polls;

	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 전봇대의 개수 N을 입력받음
		N = Integer.parseInt(br.readLine().trim());
		
		// 전봇대 연결된 것의 상태를 입력받음 1~N이지만  0~N-1에 대응
		polls = new int[N];
		st = new StringTokenizer(br.readLine().trim());
		for (int i = 0; i < N; i++) {
			polls[i] = Integer.parseInt(st.nextToken())-1;
		}
		
		// 최장증가부분수열을 담을 리스트, 하지만 이는 최장길이를 구하기 위한 리스트일뿐 LIS를 구성하는 값이 아닐 수도 있음!
		ArrayList<Integer> sequence = new ArrayList<>();
		// 초기값으로 최초의 값을 넣어주기
		sequence.add(polls[0]);
		
		// 이분탐색으로 진행할 것임
		// i번째 수를 고려 -> 1번째부터 시작하기.. 첫번째값은 들어가있으니깐
		for (int i = 1; i < N; i++) {
			// i번째 수가 리스트에 들어있는 끝값보다 큰 지 확인하기
			int iNum = polls[i];
			if (iNum > sequence.get(sequence.size()-1)) {
				// 크면 뒤에 추가하기
				sequence.add(iNum);
			}
			// 크지 않다면
			else {
				// 이분탐색으로 iNum이 있는 지 확인
				int index = Collections.binarySearch(sequence, iNum);
				
				// iNum이 있으면 iNum의 위치를 반환하고
				// 없다면 iNum보다 큰 최초의 위치를 찾고, 그 인덱스의 -1을곱하고 1을 뺀 값을 반환함
				// 즉, 음수가 나온다면..
				if (index < 0) {
					// 1을 더하고, -1을 곱해주면 된다. 
					index = -(index+1);
				}
				
				// 그 위치에 iNum을 세팅
				sequence.set(index, iNum);
			}
		}
		
		// 탐색을 완료했으면 최장증가부분수열의 길이를 알 수 있음. 
        // 이를 전체 전봇대의 개수를 빼주면, 그것이 최소로 자를 개수 
		int ans = N - sequence.size();
		sb.append(ans);
		System.out.println(sb);
	}
}
