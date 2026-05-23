import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main20920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> hm = new HashMap<>();
        for (int i = 0; i < N; i++){
            String s = br.readLine();
            if (s.length() < M){
                continue;
            }
            hm.put(s, hm.getOrDefault(s, 0) + 1);
        }

        List<String> list;
        list = hm.keySet().stream().collect(Collectors.toList());

        list.sort(((o1, o2) -> {
            int cnt1 = hm.get(o1);
            int cnt2 = hm.get(o2);
            //각각의 value를 받아서 빈도수 저장

            if (cnt1 == cnt2){
                if (o1.length() == o2.length()){
                    return o1.compareTo(o2); //사전순 먼저일 수록 앞에
                }
                return  o2.length()-o1.length(); //단어의 길이가 길수록 앞에
            }
            return cnt2-cnt1; //자주 나올수록 앞에
        }));

        for (String a : list){
            sb.append(a).append('\n');
        }
        System.out.println(sb);
    }
}
