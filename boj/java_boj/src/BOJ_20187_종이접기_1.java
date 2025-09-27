import java.io.*;
import java.util.*;

public class BOJ_20187_종이접기_1 {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

/*
 * [설명]
 * 종이접기임
 * D 아래로 접기
 * U 위로 접기
 * R 오른쪽으로 접기
 * L 왼쪽으로 접기
 * 
 * 각 위치의 순서임
 * 0 1
 * 2 3
 * 저기 4개중 뚫고 역순으로 펼쳤을 때
 * 구멍난 위치를 행 별로 출력하기
 * 
 * [입력]
 * 첫째 줄에 종이의 크기 k 입력, 한변의 길이는 2^k 임
 * 둘째 줄에 접는 순서 2k 개 입력
 * 셋째 줄에 구멍을 뚫는 위치
 * 
 * [출력]
 * 저기 4개중 뚫고 역순으로 펼쳤을 때
 * 구멍난 위치를 행 별로 출력하기
 * 
 * []
 * 재귀로 구현해야함
 * 2차원 덱으로 구현할 수 있나?
 * 2차원 덱으로 할 수 있을 거 같은데 시도 해보자
 */

    // k
    static int k;
    // 명령어
    static char[] cmd;
    // 처음에 뚫을 구멍
    static int hole;
    // 최종 상태를 저장할 2차원 덱
    static Deque<Deque<Integer>> paper;

    public static void simulation() {
        paper = new LinkedList<>();

        Deque<Integer> initDQ = new LinkedList<>();
        initDQ.add(hole);
        paper.add(initDQ);

        // 명령어를 역순으로 진행
        for (int i = 2 * k - 1; i >= 0; i--) {
            switch (cmd[i]) {
                // 위로 펼치기 (앞에 추가)
                case 'D':
                    // 임시 리스트에 반전된 행을 담아둠
                    List<Deque<Integer>> newRowD = new ArrayList<>();
                    for (Deque<Integer> row : paper) {
                        Deque<Integer> reflectRow = new LinkedList<>();
                        for (int h : row) {
                            reflectRow.add(h ^ 2);
                        }
                        newRowD.add(reflectRow);
                    }
                    
                    // 한 번에 앞에 추가
                    for (Deque<Integer> r : newRowD) {
                        paper.addFirst(r);
                    }
                    break;
                // 아래로 펼치기 (뒤에 추가)
                case 'U':
                    List<Deque<Integer>> newRowU = new ArrayList<>();
                    // 기존 행들을 순회하며 변환된 행들을 newRowU에 저장
                    for (Deque<Integer> row : paper) {
                        Deque<Integer> reflectRow = new LinkedList<>();
                        for (int h : row) {
                            reflectRow.add(h ^ 2);
                        }
                        newRowU.add(reflectRow);
                    }
                    
                    // 역순으로 순회하며 뒤에 추가
                    for (int j = newRowU.size() - 1; j >= 0; j--) {
                        paper.addLast(newRowU.get(j));
                    }
                    break;
                // 왼쪽으로 펼치기 (앞에 추가)
                case 'R':
                    for (Deque<Integer> row : paper) {
                        List<Integer> newVals = new ArrayList<>();
                        for (int num : row) {
                            newVals.add(num ^ 1);
                        }
                        for (int v : newVals) {
                            row.addFirst(v);
                        }
                    }
                    break;
                // 오른쪽으로 펼치기 (뒤에 추가)
                case 'L':
                    for (Deque<Integer> row : paper) {
                        List<Integer> newVals = new ArrayList<>();
                        // 역순으로 순회하기
                        Iterator<Integer> it = row.descendingIterator();
                        while(it.hasNext()){
                            newVals.add(it.next() ^ 1);
                        }
                        for (int v : newVals) {
                            row.addLast(v);
                        }
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        k = Integer.parseInt(br.readLine().trim());

        cmd = new char[2 * k];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < 2 * k; i++) {
            cmd[i] = st.nextToken().charAt(0);
        }

        hole = Integer.parseInt(br.readLine().trim());

        // simulation 진행
        simulation();

        // 다 진행함 출력
        for (Deque<Integer> row : paper) {
            for (int num : row) {
                sb.append(num).append(' ');
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
