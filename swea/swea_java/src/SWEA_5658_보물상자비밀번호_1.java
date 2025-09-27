import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class SWEA_5658_보물상자비밀번호_1 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int testCase;
    static int N, K;
    static LinkedList<Character> q;
    static Set<Integer> uniqueNumbers; // 중복을 제거하기 위한 Set
    static List<Integer> numList;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        testCase = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1; test_case <= testCase; ++test_case) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            String str = br.readLine().trim();
            q = new LinkedList<>();
            for (int index = 0; index < N; index++) {
                q.add(str.charAt(index));
            }

            // 중복을 처리하기 위해 Set 사용
            uniqueNumbers = new HashSet<>();
            numList = new ArrayList<>();

            // 회전 작업
            for (int rotateCount = 0; rotateCount < N / 4; rotateCount++) { // 한 번 회전 시 N/4만큼 숫자가 나타난다
                for (int count = 0; count < 4; count++) { // 4개의 변에 대한 숫자 추출
                    StringBuilder hexaNum = new StringBuilder();
                    // 각 변에서 N/4 길이만큼 16진수 값 추출
                    for (int numIndex = 0; numIndex < N / 4; numIndex++) {
                        hexaNum.append(q.get(count * (N / 4) + numIndex));
                    }
                    
                    // 해당 숫자 10진수로 변환
                    int deci = Integer.parseInt(hexaNum.toString(), 16);

                    // 중복이 아닌 경우에만 추가
                    if (!uniqueNumbers.contains(deci)) {
                        uniqueNumbers.add(deci);
                    }
                }

                // 큐 회전
                q.addFirst(q.pollLast());
            }

            // 중복 처리 후 리스트로 변환하여 정렬
            numList.addAll(uniqueNumbers);
            numList.sort((a, b) -> Integer.compare(b, a)); // 내림차순 정렬

            // K번째로 큰 값 출력
            sb.append('#').append(test_case).append(' ').append(numList.get(K - 1)).append('\n');
        }
        System.out.println(sb);
    }
}
