'''
nxn의 크기의 배열이 주어짐 이때 (i, j)가 연결되어있으면 computers[i][j]가 1, 아니면 0
자기자신인 (i, i)는 항상 1이고,,,
그러면 이웃해있는 것들이 1인것들 한묶음,, 그리고 또 없으면 다음 1을 찾고, 이웃해있는 것들 한묶음해서 다 탐색할ㄹ때까지 진행
bfs네 이러면
'''
from collections import deque

def solution(n, computers):
    answer = 0
    
    # 방문한 컴퓨터인지 0 ~ n-1까지
    visited = [False] * n
    
    for i in range(n):
        # 이미 확인한 컴퓨터면 패스
        if visited[i]:
            continue
            
        # 새로운 네트워크
        answer += 1
        queue = deque([i])
        visited[i] = True
        
        # queue가 빌 때까지
        while queue:
            current = queue.popleft() # FIFO니깐 제일 앞에꺼 빼기
            
            # 연결되어있는지 ((current, next)가 1인지 확인하기), next와 연결성을 확인한적 있는지
            for next in range(n):
                if computers[current][next] == 1 and not visited[next]:
                    visited[next] = True
                    queue.append(next)
            
    return answer