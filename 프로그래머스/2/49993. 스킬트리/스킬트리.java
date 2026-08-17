class Solution {
    public int solution(String skill, String[] skill_trees) {
        // 흠 문자열 문제..
        // skill trees에 있는 스킬트리를 보며 skill에 포함하는 내용이 있으면 그것들만 추출해서 
        // 스킬과 동일한 문자열인지 비교
        // 동일한 스킬은 주어지지 않는다고 했으니 가능한 아이디어
        int answer = 0;

        // 각각의 스킬트리를 확인
        for (String skillTree : skill_trees) {
            // skill에 포함되어 있는 스킬만 저장할 문자열
            StringBuilder sb = new StringBuilder();

            // 현재 스킬트리를 하나씩 확인
            for (char c : skillTree.toCharArray()) {
                // 현재 스킬이 skill에 포함되어 있다면
                if (skill.indexOf(c) != -1) {
                    // 해당 스킬을 추출해서 저장
                    sb.append(c);
                }
            }

            // 추출한 스킬들이 skill의 순서와 맞는지 확인
            // 예를 들어 skill이 "CBD"라면 "", "C", "CB", "CBD"는 모두 가능
            if (skill.startsWith(sb.toString())) {
                answer++;
            }
        }

        return answer;
    }
}