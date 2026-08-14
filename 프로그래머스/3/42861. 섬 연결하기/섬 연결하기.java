import java.io.*;
import java.util.*;

class Solution {

    // 부모 찾기
    private int find(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }
        
        return parent[x] = find(parent, parent[x]);
    }
    
    // 두 섬 연결하기
    private void union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        
        if (a != b) {
            parent[b] = a;
        }
    }
    
    public int solution(int n, int[][] costs) {
        // 다리를 여러 번 건너더라도 도달할 수만 있으면 통행 가능
        // costs[i][0] costs[i][1]이 연결되어있는 섬 번호
        // costs[i][2]가 건너는 비용
        
        // 모든 섬을 연결할 때의 비용
        // 근데 가장 적은 비용
        // 그러면 비용이 적은 순부터 섬을 선택하며 연결하기
        
        // 크루스칼 알고리즘 적용
        // 비용이 적은 간선부터 선택하면서 모든 섬을 연결
        // 유니온 파인드로 두 섬이 이미 연결되어 있는지 확인
        
        // 우선 비용이 적은 순으로 오름차순 정렬하기
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);
        
        // 유니온 파인드를 위한 부모 배열
        int[] parent = new int[n];
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        int answer = 0;
        
        for (int[] cost : costs) {
            int island1 = cost[0];
            int island2 = cost[1];
            int bridgeCost = cost[2];
            
            // 두 섬이 이미 연결되어 있는지 확인
            // 연결되어 있지 않다면 다리를 건설
            if (find(parent, island1) != find(parent, island2)) {
                union(parent, island1, island2);
                answer += bridgeCost;
            }
        }
        
        return answer;
    }
}