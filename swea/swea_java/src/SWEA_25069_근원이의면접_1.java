import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 그리디 풀이 
public class SWEA_25069_근원이의면접_1 {
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int N; // 문제의 수
	static int M; // 정답 문제의 수
	static int K; // 보너스 점수를 위한 연속 정답 문제 수
	static int minimumScore; // 면접에서 받을 수 있는 점수 중에서 가장 낮은 점수 
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		/*
		 * 연속으로 맞춰야 하면 초반에 연속으로 맞추는 것이 좋음
		 * 다 틀릴 수 있다면 k-1개 맞고, 하나 틀리는 것이 베스트
		 * 그렇지 않다면 초반에 연속 정답 후 k-1개 맞고 하나틀리기 
		 */
		int testCase = Integer.parseInt(br.readLine().trim());
		for (int test_case = 1; test_case <= testCase; ++test_case) {
			st = new StringTokenizer(br.readLine().trim());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			minimumScore = 0;
			
			/*
			 * 틀린 문제(N-M개)를 사용해 보너스를 안받을 수 있는 정답 수를 계산한다. -> noBonusCount
			 * 맞춰야 할 정답(M개)이 noBonusCount보다 많으면 넘는 것에 대한 보너스 개수를 계산 -> bonusCount
			 * 보너스는 초반에 몰아서 받는 것이 유리하기에 미리 받고 나머지 정답 점수를 더하는 방식으로 계산 
			 */

			// 보너스를 안받고 맞출 수 있는 개수
			int noBonusCount = (N - M) * (K - 1);
			
			if (M <= noBonusCount) {
				// 보너스를 안받고 모든 정답을 맞출 수 있음
				minimumScore = M;
			} else {
				// 보너스를 받아야 하는 경우
				// noBonusCount가 보너스를 안받고 맞출 수 있는 개수이기 때문에
				// 연속 정답의 개수 : 맞춰야 하는 정답의 개수 - 보너스를 안받고 맞추는 문제수 
				int continuousCount = M - noBonusCount;
				// 연속 정답 중에서 나오는 점수 2배 타임의 개수 : 연속정답의 개수 / K
				int  bonusCount = continuousCount / K;
				
				// 최소 점수 초기화 
				minimumScore = 0;
				
				// 보너스 점수를 받아야 하면, 초반에 다 받기 
				for (int i = 0; i < bonusCount; i++) {
					// 연속정답 후
					minimumScore += K;
					// 2배 하기 
					minimumScore *= 2;
				}
				
				// 보너스 없이 맞출 수 있는 정답의 수 : 남은 정답의 수 - 보너스를 받고 맞춘 정답의 수
				// 남은 정답의 수 를 최소 점수에 더함
				minimumScore += (M - (bonusCount * K));
			}
			
			sb.append('#').append(test_case).append(' ').append(minimumScore).append('\n');
		}
		System.out.println(sb);
	}
}
